package it.polito.latazza.data;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
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
	
	private int nextEmployeeId;
	private int nextBeverageId;
    
	public DataImpl() {
		loadData();
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
		Consumption c = new Consumption(e, b, fromAccount, numberOfCapsules);
		b.decreaseAvailableCapsules(numberOfCapsules);
		if (fromAccount)
			e.decreaseBalance(b.getCapsulesPrice() * numberOfCapsules);
		else
			laTazzaAccount.increaseBalance(b.getCapsulesPrice() * numberOfCapsules);
		transactions.add(c);
		System.out.println("Transaction completed: " + c);
		
		/* store */
		storeObject(c, FILENAME_TRANSACTIONS, true);
		storeObjects(beverages.values(), FILENAME_BEVERAGES);
		if (fromAccount)
			storeObjects(employees.values(), FILENAME_EMPLOYEES);
		else
			storeObject(laTazzaAccount, FILENAME_LA_TAZZA_ACCOUNT, false);
		
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
		Consumption c = new Consumption(b, numberOfCapsules);
		b.decreaseAvailableCapsules(numberOfCapsules);
		laTazzaAccount.increaseBalance(b.getCapsulesPrice() * numberOfCapsules);
		transactions.add(c);
		System.out.println("Transaction completed: " + c);
		
		/* store */
		storeObject(c, FILENAME_TRANSACTIONS, true);
		storeObjects(beverages.values(), FILENAME_BEVERAGES);
		storeObject(laTazzaAccount, FILENAME_LA_TAZZA_ACCOUNT, false);

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
		
		/* store */
		storeObject(r, FILENAME_TRANSACTIONS, true);
		storeObjects(employees.values(), FILENAME_EMPLOYEES);
		storeObject(laTazzaAccount, FILENAME_LA_TAZZA_ACCOUNT, false);
		
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
		
		/* store */
		storeObject(bp, FILENAME_TRANSACTIONS, true);
		storeObjects(beverages.values(), FILENAME_BEVERAGES);
		storeObject(laTazzaAccount, FILENAME_LA_TAZZA_ACCOUNT, false);
	}

	@Override
	public List<String> getEmployeeReport(Integer employeeId, Date startDate, Date endDate)
			throws EmployeeException, DateException { 
		if (startDate==null || endDate==null || startDate.after(endDate))
			throw new DateException();
		if(!employees.containsKey(employeeId))
			throw new EmployeeException();
		return transactions.stream().filter(l ->    (((l instanceof Consumption)
                                                    && (((Consumption)l).getEmployee() != null)
													&& (((Consumption)l).getEmployee().getId() == employeeId))
													|| ((l instanceof Recharge)
													&& (((Recharge)l).getEmployee().getId() == employeeId)))
													&& (l.getDate().after(startDate) || l.getDate().equals(startDate)) 
													&& (l.getDate().before(setTimeToMidnight(endDate)) || l.getDate().equals(setTimeToMidnight(endDate))))
									.map(l -> l.toString())
									.collect(Collectors.toList());
	}

	@Override
	public List<String> getReport(Date startDate, Date endDate) throws DateException {
		
		if (startDate==null || endDate==null || startDate.after(endDate))
			throw new DateException();
		
        return transactions.stream().filter(l -> ((l.getDate().after(startDate) || l.getDate().equals(startDate)) && (l.getDate().before(setTimeToMidnight(endDate)) || l.getDate().equals(setTimeToMidnight(endDate)))))
        							.map(l -> l.toString()).collect(Collectors.toList());
	}

	@Override
	public Integer createBeverage(String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException {
		/* check if already present */
		if (beverages.values().stream().anyMatch(b -> b.getName().equals(name)))
			throw new BeverageException();
		
		/* create */
		Beverage b = new Beverage(nextBeverageId, name, boxPrice, capsulesPerBox);
		beverages.put(nextBeverageId, b);
		System.out.println(b + " created");
		
		/* store */
		storeObject(b, FILENAME_BEVERAGES, true);
		
		return nextBeverageId++;
	}

	@Override
	public void updateBeverage(Integer id, String name, Integer capsulesPerBox, Integer boxPrice)
			throws BeverageException {
		/* retrieve */
		Beverage b = beverages.get(id);
		if (b == null)
			throw new BeverageException();
		
		/* keep for restore */
		int bp = b.getBoxPrice();
		int cpb = b.getCapsulesPerBox();
		String n = b.getName();
		
		/* update */
		try {
			b.setBoxPrice(boxPrice);
			b.setCapsulesPerBox(capsulesPerBox);
			b.setName(name);
			System.out.println(b + " updated");
		} catch (Exception e) {
			b.setBoxPrice(bp);
			b.setCapsulesPerBox(cpb);
			b.setName(n);
			throw e;
		}
		
		/* save */
		storeObjects(beverages.values(), FILENAME_BEVERAGES);

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
		Employee e = new Employee(nextEmployeeId, name, surname);
		employees.put(nextEmployeeId, e);
		System.out.println(e + " created");
		
		/* save */
		storeObject(e, FILENAME_EMPLOYEES, true);
		
		return nextEmployeeId++;
	}

	@Override
	public void updateEmployee(Integer id, String name, String surname) throws EmployeeException {
		/* retrieve */
		Employee e = employees.get(id);
		if (e == null)
			throw new EmployeeException();
		
		/* keep for restore */
		String n = e.getName();
		String s = e.getSurname();
		
		/* update */
		try {
			e.setName(name);
			e.setSurname(surname);
		} catch (Exception er) {
			e.setName(n);
			e.setSurname(s);
			throw er;
		}
		
		/* update */
		
		System.out.println(e + " updated");
		
		/* save */
		storeObjects(employees.values(), FILENAME_EMPLOYEES);
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
		File directory = new File(PATHNAME);
		
		if (directory.exists()) {
			for (File f : directory.listFiles())
			      f.delete();
			directory.delete();
			this.loadData();
		}
		
		System.out.println("Reset done");
	}
	
	
	/* Data persistency */
	
	private Object loadObject(String filename) throws IOException, ClassNotFoundException {
		try (ObjectInputStream deserializer = new ObjectInputStream(new FileInputStream(filename))) {
			return deserializer.readObject();
		}
	}
	
	private Collection<?> loadObjects(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
		Collection<Object> c = new LinkedList<>();
		try (FileInputStream fis = new FileInputStream(filename)) {
			while (fis.available() > 0) {
				ObjectInputStream deserializer = new ObjectInputStream(fis);
				c.add(deserializer.readObject());
			}
		}
		return c;
	}
	
	private void storeObject(Object object, String filename, boolean append) {
		try (ObjectOutputStream serializer = new ObjectOutputStream(new FileOutputStream(filename, append))) {
			serializer.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void storeObjects(Collection<?> collection, String filename) {
		try (FileOutputStream fos = new FileOutputStream(filename)) {
			for (Object o : collection) {
				ObjectOutputStream serializer = new ObjectOutputStream(fos);
				serializer.writeObject(o);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void loadData() {
		/* create directory */
		File directory = new File(PATHNAME);
		if (!directory.exists()) {
			if (directory.mkdir()) {
				System.out.println("Directory " + PATHNAME + " created");
			} else {
				System.err.println("Error creating the directory " + PATHNAME);
				System.exit(-1);
			}
		}
		
		/* load/create transactions */
		try {
			transactions = ((Collection<Transaction>) loadObjects(FILENAME_TRANSACTIONS)).stream().collect(Collectors.toCollection(LinkedList::new));
			System.out.println("Transactions loaded");
		} catch (Exception e) {
			transactions = new LinkedList<>();
			System.err.println("Error reading " + FILENAME_TRANSACTIONS + " (" + e.getClass() + ")... new transaction list used");
		}
		
		/* load/create employees */
		try {
			employees = ((Collection<Employee>) loadObjects(FILENAME_EMPLOYEES)).stream().collect(Collectors.toMap(Employee::getId, e -> e, (e1, e2) -> e1, HashMap::new));
			nextEmployeeId = employees.keySet().stream().max(Comparator.naturalOrder()).orElse(-1) + 1;
			System.out.println("Employees loaded");
		} catch (Exception e) {
			employees = new HashMap<>();
			nextEmployeeId = 0;
			System.err.println("Error reading " + FILENAME_EMPLOYEES + " (" + e.getClass() + ")... new employee map used");
		}
		
		/* load/create beverages */
		try {
			beverages = ((Collection<Beverage>) loadObjects(FILENAME_BEVERAGES)).stream().collect(Collectors.toMap(Beverage::getId, b -> b, (b1, b2) -> b1, HashMap::new));
			nextBeverageId = beverages.keySet().stream().max(Comparator.naturalOrder()).orElse(-1) + 1;
			System.out.println("Beverages loaded");
		} catch (Exception e) {
			beverages = new HashMap<>();
			nextBeverageId = 0;
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
	
	public static Date setTimeToMidnight(Date date) {    
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.set(Calendar.HOUR_OF_DAY, 23);  
        cal.set(Calendar.MINUTE, 59);  
        cal.set(Calendar.SECOND, 59);  
        cal.set(Calendar.MILLISECOND, 0);  
        return cal.getTime(); 
    }
}
