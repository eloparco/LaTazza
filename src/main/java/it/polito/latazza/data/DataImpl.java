package it.polito.latazza.data;

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

    private List<Transaction> transactions = new ArrayList<>();
	private Map<Integer, Employee> employees = new HashMap<>();
	private Map<Integer, Beverage> beverages = new HashMap<>();
	private LaTazzaAccount laTazzaAccount = new LaTazzaAccount();
    
	@Override
	public Integer sellCapsules(Integer employeeId, Integer beverageId, Integer numberOfCapsules, Boolean fromAccount)
			throws EmployeeException, BeverageException, NotEnoughCapsules {
		Employee e = employees.get(employeeId);
		if(e == null)
			throw new EmployeeException();
		
		Beverage b = beverages.get(beverageId);
		if(b == null)
			throw new BeverageException();
		
		b.decreaseAvailableCapsules(numberOfCapsules);
		if (fromAccount)
			e.decreaseBalance(b.getCapsulesPrice() * numberOfCapsules);
		else
			laTazzaAccount.increaseBalance(b.getCapsulesPrice() * numberOfCapsules);
		
		Consumption c = new Consumption(e, b, fromAccount, numberOfCapsules);
		transactions.add(c);
		
		//TODO: return updated employee balance??
		return e.getBalance();
	}

	@Override
	public void sellCapsulesToVisitor(Integer beverageId, Integer numberOfCapsules)
			throws BeverageException, NotEnoughCapsules {
		Beverage b = beverages.get(beverageId);
		if (b == null)
			throw new BeverageException();
		
		b.decreaseAvailableCapsules(numberOfCapsules);
		laTazzaAccount.increaseBalance(b.getCapsulesPrice() * numberOfCapsules);
		
		Consumption c = new Consumption(b, numberOfCapsules);
		transactions.add(c);
		
	}

	@Override
	public Integer rechargeAccount(Integer id, Integer amountInCents) throws EmployeeException {
		Employee e = employees.get(id);
		if (e == null)
			throw new EmployeeException();
		
		e.increaseBalance(amountInCents);
		laTazzaAccount.increaseBalance(amountInCents);
		
		Recharge r = new Recharge(e, amountInCents);
		transactions.add(r);
		
		//TODO: what to return? updated balance in cents?
		return e.getBalance();
	}

	@Override
	public void buyBoxes(Integer beverageId, Integer boxQuantity) throws BeverageException, NotEnoughBalance {
		Beverage b = beverages.get(beverageId);
		if (b == null)
			throw new BeverageException();
		
		b.increaseAvailableCapsules(boxQuantity);
		laTazzaAccount.decreaseBalance(b.getBoxPrice() * boxQuantity);
		
		BoxPurchase bp = new BoxPurchase(b, boxQuantity);
		transactions.add(bp);
		
	}

	@Override
	public List<String> getEmployeeReport(Integer employeeId, Date startDate, Date endDate)
			throws EmployeeException, DateException {
		//TODO: when to throw date exception???
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
        return transactions.stream().filter(l -> l.getDate().after(startDate) && l.getDate().before(endDate)).map(l -> l.toString()).collect(Collectors.toList());
	}

	@Override
	public Integer createBeverage(String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException {
		boolean found = beverages.values().stream().anyMatch(b -> b.getName().equals(name));
		if (found)
			throw new BeverageException();
		
		Integer key = beverages.keySet().stream().max(Integer::compareTo).orElse(-1) + 1;
		Beverage b = new Beverage(key, name, boxPrice, capsulesPerBox);
		beverages.put(key, b);
		return key;
	}

	@Override
	public void updateBeverage(Integer id, String name, Integer capsulesPerBox, Integer boxPrice)
			throws BeverageException {
		Beverage b = beverages.get(id);
		if (b == null)
			throw new BeverageException();
		
		b.setBoxPrice(boxPrice);
		b.setCapsulesPerBox(capsulesPerBox);
		b.setName(name);
		beverages.put(id, b);
		
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
		return beverages.values().stream().collect(Collectors.toMap(l -> l.getId(), l -> l.getName()));
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
		Integer key = employees.keySet().stream().max(Integer::compareTo).orElse(-1) + 1;
		Employee e = new Employee(key, name, surname, 0);
		employees.put(key, e);
		return key;
	}

	@Override
	public void updateEmployee(Integer id, String name, String surname) throws EmployeeException {
		Employee e = employees.get(id);
		if (e == null)
			throw new EmployeeException();
		e.setName(name);
		e.setSurname(surname);
		employees.put(id, e);
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
		return employees.values().stream().collect(Collectors.toMap(l -> l.getId(), l -> l.getName()));
	}

	@Override
	public Integer getBalance() {
		return laTazzaAccount.getBalance();
	}

	@Override
	public void reset() {
        laTazzaAccount = new LaTazzaAccount();
		employees.clear();
		beverages.clear();
		transactions.clear();
	}

}
