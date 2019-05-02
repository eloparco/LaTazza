package it.polito.latazza.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.DateException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;

public class DataImpl implements DataInterface {
	private static final String PATHNAME = "data/";
    private static final String FILENAME_TRANSACTIONS = PATHNAME + "transactions";
	private static final String FILENAME_EMPLOYEES = PATHNAME + "employees";
	private static final String FILENAME_BEVERAGES = PATHNAME + "beverages";
	private static final String FILENAME_LA_TAZZA_ACCOUNT = PATHNAME + "latazza_account";
	
	private List<Transaction> transactions;
	private Map<Integer, Employee> employees;
	private Map<Integer, Beverage> beverages;
	private LaTazzaAccount laTazzaAccount;
    
	@SuppressWarnings("unchecked")
	public DataImpl() {
		/* create directory */
		File directory = new File(PATHNAME);
		if (!directory.exists()) {
			if (directory.mkdir()) {
				System.out.println("Directory " + PATHNAME + "created");
			} else {
				System.err.println("Error creating the directory " + PATHNAME);
				System.exit(-1);
			}
		}
		
		/* load/create transactions */
		try {
			transactions = (List<Transaction>) loadObject(FILENAME_TRANSACTIONS);
			System.out.println("Transactions loaded");
		} catch (Exception e) {
			transactions = new ArrayList<>();
			System.err.println("Error reading " + FILENAME_TRANSACTIONS + " (" + e.getClass() + ")... new transaction list used");
		}
		
		/* load/create employees */
		try {
			employees = (Map<Integer,Employee>) loadObject(FILENAME_EMPLOYEES);
			System.out.println("Employees loaded");
		} catch (Exception e) {
			employees = new HashMap<>();
			System.err.println("Error reading " + FILENAME_EMPLOYEES + " (" + e.getClass() + ")... new employee map used");
		}
		
		/* load/create beverages */
		try {
			beverages = (Map<Integer,Beverage>) loadObject(FILENAME_BEVERAGES);
			System.out.println("Beverages loaded");
		} catch (Exception e) {
			beverages = new HashMap<>();
			System.err.println("Error reading " + FILENAME_BEVERAGES + " (" + e.getClass() + ")... new beverage map used");
		}
		
		/* load/create LaTazza account */
		try {
			laTazzaAccount = (LaTazzaAccount) loadObject(FILENAME_LA_TAZZA_ACCOUNT);
			System.out.println("LaTazza account loaded");
		} catch (Exception e) {
			laTazzaAccount = new LaTazzaAccount();
			System.err.println("Error reading " + FILENAME_LA_TAZZA_ACCOUNT + " (" + e.getClass() + ")... new LaTazza account used");
		}
	}
	
	@Override
	public Integer sellCapsules(Integer employeeId, Integer beverageId, Integer numberOfCapsules, Boolean fromAccount)
			throws EmployeeException, BeverageException, NotEnoughCapsules {
		/* retrieve */
		Employee e = employees.get(employeeId);
		if(e == null)
			throw new EmployeeException();
		Beverage b = beverages.get(beverageId);
		if(b == null)
			throw new BeverageException();
		
		/* update */
		if (b.getAvailableCapsules() < numberOfCapsules)
			throw new NotEnoughCapsules();
		b.decreaseAvailableCapsules(numberOfCapsules);
		if (fromAccount)
			e.decreaseBalance(b.getCapsulesPrice() * numberOfCapsules);
		else
			laTazzaAccount.increaseBalance(b.getCapsulesPrice() * numberOfCapsules);
		Consumption c = new Consumption(e, b, fromAccount, numberOfCapsules);
		transactions.add(c);
		System.out.println("Transaction completed: " + c);
		
		/* save */
		saveObject(transactions, FILENAME_TRANSACTIONS);
		saveObject(beverages, FILENAME_BEVERAGES);
		if (fromAccount)
			saveObject(employees, FILENAME_EMPLOYEES);
		else
			saveObject(laTazzaAccount, FILENAME_LA_TAZZA_ACCOUNT);
		
		return e.getBalance();
	}

	@Override
	public void sellCapsulesToVisitor(Integer beverageId, Integer numberOfCapsules)
			throws BeverageException, NotEnoughCapsules {
		/* retrieve */
		Beverage b = beverages.get(beverageId);
		if (b == null)
			throw new BeverageException();
		
		/* update */
		b.decreaseAvailableCapsules(numberOfCapsules);
		laTazzaAccount.increaseBalance(b.getCapsulesPrice() * numberOfCapsules);
		Consumption c = new Consumption(b, numberOfCapsules);
		transactions.add(c);
		System.out.println("Transaction completed: " + c);
		
		/* save */
		saveObject(transactions, FILENAME_TRANSACTIONS);
		saveObject(beverages, FILENAME_BEVERAGES);
		saveObject(laTazzaAccount, FILENAME_LA_TAZZA_ACCOUNT);
	}

	@Override
	public Integer rechargeAccount(Integer id, Integer amountInCents) throws EmployeeException {
		/* retrieve */
		Employee e = employees.get(id);
		if (e == null)
			throw new EmployeeException();
		
		/* update */
		e.increaseBalance(amountInCents);
		laTazzaAccount.increaseBalance(amountInCents);
		Recharge r = new Recharge(e, amountInCents);
		transactions.add(r);
		System.out.println("Transaction completed: " + r);
		
		/* save */
		saveObject(transactions, FILENAME_TRANSACTIONS);
		saveObject(employees, FILENAME_EMPLOYEES);
		saveObject(laTazzaAccount, FILENAME_LA_TAZZA_ACCOUNT);
		
		return e.getBalance();
	}

	@Override
	public void buyBoxes(Integer beverageId, Integer boxQuantity) throws BeverageException, NotEnoughBalance {
		/* retrieve */
		Beverage b = beverages.get(beverageId);
		if (b == null)
			throw new BeverageException();
		
		/* update */
		laTazzaAccount.decreaseBalance(b.getBoxPrice() * boxQuantity);
		b.increaseAvailableCapsules(boxQuantity);
		BoxPurchase bp = new BoxPurchase(b, boxQuantity);
		transactions.add(bp);
		System.out.println("Transaction completed: " + bp);
		
		/* save */
		saveObject(transactions, FILENAME_TRANSACTIONS);
		saveObject(beverages, FILENAME_BEVERAGES);
		saveObject(laTazzaAccount, FILENAME_LA_TAZZA_ACCOUNT);
	}

	@Override
	public List<String> getEmployeeReport(Integer employeeId, Date startDate, Date endDate)
			throws EmployeeException, DateException {
		// TODO: add check for valid dates (e.g. 31 february) 
		if (startDate.after(endDate))
			throw new DateException();
		if(!employees.containsKey(employeeId))
			throw new EmployeeException();
		return transactions.stream().filter(l ->    (((l instanceof Consumption)
                                                    && (((Consumption)l).getEmployee() != null)
													&& (((Consumption)l).getEmployee().getId() == employeeId))
													|| ((l instanceof Recharge)
													&& (((Recharge)l).getEmployee().getId() == employeeId)))
													&& l.getDate().after(startDate)
													&& l.getDate().before(endDate))
									.map(l -> l.toString())
									.collect(Collectors.toList());
	}

	@Override
	public List<String> getReport(Date startDate, Date endDate) throws DateException {
		// TODO: add check for valid dates (e.g. 31 february)
		if (startDate.after(endDate))
			throw new DateException();
        return transactions.stream().filter(l -> l.getDate().after(startDate) && l.getDate().before(endDate)).map(l -> l.toString()).collect(Collectors.toList());
	}

	@Override
	public Integer createBeverage(String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException {
		/* check if already present */
		if (beverages.values().stream().anyMatch(b -> b.getName().equals(name)))
			throw new BeverageException();
		
		/* create */
		Integer key = beverages.keySet().stream().max(Integer::compareTo).orElse(-1) + 1;
		Beverage b = new Beverage(key, name, boxPrice, capsulesPerBox);
		beverages.put(key, b);
		System.out.println(b + " created");
		
		/* save */
		saveObject(beverages, FILENAME_BEVERAGES);
		
		return key;
	}

	@Override
	public void updateBeverage(Integer id, String name, Integer capsulesPerBox, Integer boxPrice)
			throws BeverageException {
		/* retrieve */
		Beverage b = beverages.get(id);
		if (b == null)
			throw new BeverageException();
		
		/* update */
		b.setBoxPrice(boxPrice);
		b.setCapsulesPerBox(capsulesPerBox);
		b.setName(name);
		beverages.put(id, b);
		System.out.println(b + " updated");
		
		/* save */
		saveObject(beverages, FILENAME_BEVERAGES);
	}

	@Override
	public String getBeverageName(Integer id) throws BeverageException {
		Beverage b = beverages.get(id);
		if (b == null)
			throw new BeverageException();
		return b.getName();
	}

	@Override
	public Integer getBeverageCapsulesPerBox(Integer id) throws BeverageException {
		Beverage b = beverages.get(id);
		if (b == null)
			throw new BeverageException();
		return b.getCapsulesPerBox();
	}

	@Override
	public Integer getBeverageBoxPrice(Integer id) throws BeverageException {
		Beverage b = beverages.get(id);
		if (b == null)
			throw new BeverageException();
		return b.getBoxPrice();
	}

	@Override
	public List<Integer> getBeveragesId() {
		return beverages.keySet().stream().collect(Collectors.toList());
	}

	@Override
	public Map<Integer, String> getBeverages() {
		return beverages.values().stream().collect(Collectors.toMap(l -> l.getId(), l -> l.toString()));
	}

	@Override
	public Integer getBeverageCapsules(Integer id) throws BeverageException {
		Beverage b = beverages.get(id);
		if (b == null)
			throw new BeverageException();
		return b.getAvailableCapsules();
	}

	@Override
	public Integer createEmployee(String name, String surname) throws EmployeeException {
		/* check if already present */
		if (employees.values().stream().anyMatch(e -> e.getName().equals(name) && e.getSurname().equals(surname)))
			throw new EmployeeException();
		
		/* create */
		Integer key = employees.keySet().stream().max(Integer::compareTo).orElse(-1) + 1;
		Employee e = new Employee(key, name, surname, 0);
		employees.put(key, e);
		System.out.println(e + " created");
		
		/* save */
		saveObject(employees, FILENAME_EMPLOYEES);
		
		return key;
	}

	@Override
	public void updateEmployee(Integer id, String name, String surname) throws EmployeeException {
		/* retrieve */
		Employee e = employees.get(id);
		if (e == null)
			throw new EmployeeException();
		
		/* update */
		e.setName(name);
		e.setSurname(surname);
		employees.put(id, e);
		System.out.println(e + " updated");
		
		/* save */
		saveObject(employees, FILENAME_EMPLOYEES);
	}

	@Override
	public String getEmployeeName(Integer id) throws EmployeeException {
		Employee e = employees.get(id);
		if (e == null)
			throw new EmployeeException();
		return e.getName();
	}

	@Override
	public String getEmployeeSurname(Integer id) throws EmployeeException {
		Employee e = employees.get(id);
		if (e == null)
			throw new EmployeeException();
		return e.getSurname();
	}

	@Override
	public Integer getEmployeeBalance(Integer id) throws EmployeeException {
		Employee e = employees.get(id);
		if (e == null)
			throw new EmployeeException();
		return e.getBalance();
	}

	@Override
	public List<Integer> getEmployeesId() {
		return employees.keySet().stream().collect(Collectors.toList());
	}

	@Override
	public Map<Integer, String> getEmployees() {
		return employees.values().stream().collect(Collectors.toMap(l -> l.getId(), l -> l.toString()));
	}

	@Override
	public Integer getBalance() {
		return laTazzaAccount.getBalance();
	}

	@Override
	public void reset() {
		/* main memory */
        laTazzaAccount = new LaTazzaAccount();
		employees.clear();
		beverages.clear();
		transactions.clear();
		
		/* disk */
		saveObject(transactions, FILENAME_TRANSACTIONS);
		saveObject(employees, FILENAME_EMPLOYEES);
		saveObject(beverages, FILENAME_BEVERAGES);
		saveObject(laTazzaAccount, FILENAME_LA_TAZZA_ACCOUNT);
		
		System.out.println("Reset done");
	}
	
	
	/* Data persistency */
	
	private Object loadObject(String filename) throws IOException, ClassNotFoundException {
		try (ObjectInputStream deserializer = new ObjectInputStream(new FileInputStream(filename))) {
			return deserializer.readObject();
		}
	}
	
	private void saveObject(Object object, String filename) {
		try (ObjectOutputStream serializer = new ObjectOutputStream(new FileOutputStream(filename))) {
			serializer.writeObject(object);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
