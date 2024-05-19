package ice.cream.shop.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ice.cream.shop.controller.model.IceCreamShopData;
import ice.cream.shop.controller.model.IceCreamShopData.IceCreamShopCustomer;
import ice.cream.shop.controller.model.IceCreamShopData.IceCreamShopEmployee;
import ice.cream.shop.controller.model.IceCreamShopData.IceCreamShopFlavor;
import ice.cream.shop.dao.IceCreamShopDao;
import ice.cream.shop.entity.Customer;
import ice.cream.shop.entity.Employee;
import ice.cream.shop.entity.Flavor;
import ice.cream.shop.entity.IceCreamShop;
import ice.cream.shop.dao.EmployeeDao;
import ice.cream.shop.dao.FlavorDao;
import ice.cream.shop.dao.CustomerDao;

@Service
public class IceCreamShopService {

	@Autowired
	private IceCreamShopDao iceCreamShopDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private FlavorDao flavorDao;

	//method to save or update ice cream shop data
	@Transactional(readOnly = false)
	public IceCreamShopData saveIceCreamShop(IceCreamShopData iceCreamShopData) {
		Long iceCreamShopId = iceCreamShopData.getIceCreamShopId();
		IceCreamShop iceCreamShop = findOrCreateIceCreamShop(iceCreamShopId);
		
		copyIceCreamShopFields(iceCreamShop, iceCreamShopData);
		return new IceCreamShopData(iceCreamShopDao.save(iceCreamShop));
	}

	
	//to copy ice cream shop from DTO to entity
	private void copyIceCreamShopFields(IceCreamShop iceCreamShop, IceCreamShopData iceCreamShopData) {
		iceCreamShop.setIceCreamShopName(iceCreamShopData.getIceCreamShopName());
		iceCreamShop.setIceCreamShopAddress(iceCreamShopData.getIceCreamShopAddress());
		iceCreamShop.setIceCreamShopCity(iceCreamShopData.getIceCreamShopCity());
		iceCreamShop.setIceCreamShopTime(iceCreamShopData.getIceCreamShopTime());
		iceCreamShop.setIceCreamShopPhone(iceCreamShopData.getIceCreamShopPhone());
	}

	//find or create shop based on ID
	private IceCreamShop findOrCreateIceCreamShop(Long iceCreamShopId) {
		if (Objects.isNull(iceCreamShopId)) {
			return new IceCreamShop();
		} else {
			return findIceCreamShopByID(iceCreamShopId);
		}
	}

	//find ice cream shop by ID
	private IceCreamShop findIceCreamShopByID(Long iceCreamShopId) {
		return iceCreamShopDao.findById(iceCreamShopId).orElseThrow(() -> new NoSuchElementException
				("Ice cream shop with ID=" + iceCreamShopId + " was not found."));
	}

	
    
    private void copyEmployeeFields(Employee employee, IceCreamShopEmployee employeeData) {
        employee.setEmployeeFirstName(employeeData.getEmployeeFirstName());
        employee.setEmployeeLastName(employeeData.getEmployeeLastName());
        employee.setEmployeePhone(employeeData.getEmployeePhone());
        employee.setEmployeeJobTitle(employeeData.getEmployeeJobTitle());
        
    }
    

     private void copyCustomerFields(Customer customer, IceCreamShopCustomer customerData) {
    	customer.setCustomerId(customerData.getCustomerId());
        customer.setCustomerFirstName(customerData.getCustomerFirstName());
        customer.setCustomerLastName(customerData.getCustomerLastName());
        customer.setCustomerEmail(customerData.getCustomerEmail());
    	}
     
     private void copyFlavorFields(Flavor flavor, IceCreamShopFlavor flavorData) {
    	flavor.setFlavorId(flavorData.getFlavorId());
        flavor.setFlavorName(flavorData.getFlavorName());
    	}
     

	 private Employee findOrCreateEmployee(Long iceCreamShopId, Long employeeId) {
	        if (Objects.isNull(employeeId)) {
	            return new Employee();
	        } else {
	            return findEmployeeById(iceCreamShopId, employeeId);
	            
	        }
	    }
     

		private Customer findOrCreateCustomer(Long iceCreamShopId, Long customerId) {
	        if (Objects.isNull(customerId)) {
	            return new Customer();
	        } else {
	            return findCustomerById(iceCreamShopId, customerId);
	        }
		}
		
		private Flavor findOrCreateFlavor(Long iceCreamShopId, Long flavorId) {
	        if (Objects.isNull(flavorId)) {
	            return new Flavor();
	        } else {
	            return findFlavorById(iceCreamShopId, flavorId);
	        }
		}



		@Transactional(readOnly = false)
		private Employee findEmployeeById(Long iceCreamShopId, Long employeeId) {
	        Employee employee = employeeDao.findById(employeeId)
	        		.orElseThrow(() -> new NoSuchElementException("Employee with ID=" + employeeId + " was not found."));
	        if (employee.getIceCreamShop().getIceCreamShopId() != iceCreamShopId) {
	            throw new IllegalArgumentException("The employee with ID=" + 
	        employeeId + " is not employee by the ice cream shop with ID=" + iceCreamShopId + ".");
	        }

		    return employee;
		}
     
		 @Transactional(readOnly = false)
			private Customer findCustomerById(Long iceCreamShopId, Long customerId) {
		        Customer customer = customerDao.findById(customerId)
		                .orElseThrow(() -> new NoSuchElementException ("Customer with ID=" + customerId + " was not found."));
		        boolean found = false;
		        for (IceCreamShop iceCreamShop : customer.getIceCreamShop()) {
		            if (iceCreamShop.getIceCreamShopId().equals(iceCreamShopId)) {
		                found = true;
		                break;
		            }
		        }

		        if (!found) {
		            throw new IllegalArgumentException("The customer with ID=" + 
		        customerId + " is not a member of the ice cream shop with ID=" + iceCreamShopId + ".");
		        }
		        return customer;
			}
		 @Transactional(readOnly = false)
			private Flavor findFlavorById(Long iceCreamShopId, Long flavorId) {
		        Flavor flavor = flavorDao.findById(flavorId).orElseThrow(() -> new NoSuchElementException ("Flavor with ID=" 
			+ flavorId + " was not found."));
			if (flavor.getIceCreamShop().getIceCreamShopId() !=iceCreamShopId) {
				throw new IllegalArgumentException("The flavor with ID=" +
			flavorId + " is not flavor by the ice cream shop with ID=" + iceCreamShopId
			+ ".");
			}
			return flavor;
			}

		 
		 //methods to save or update
		 @Transactional(readOnly = false)
		 public IceCreamShopEmployee saveEmployee(Long iceCreamShopId, IceCreamShopEmployee iceCreamShopEmployee) {
			 IceCreamShop iceCreamShop = findIceCreamShopByID(iceCreamShopId);
			 Long employeeId = iceCreamShopEmployee.getEmployeeId();
			 Employee employee = findOrCreateEmployee(iceCreamShopId, employeeId);
			 copyEmployeeFields(employee, iceCreamShopEmployee);
			 employee.setIceCreamShop(iceCreamShop);
			 iceCreamShop.getEmployees().add(employee);
			 Employee dbEmployee = employeeDao.save(employee);
	  
			 return new IceCreamShopEmployee(dbEmployee);
    }
   
   
	
		 @Transactional(readOnly = false)
		 public IceCreamShopCustomer saveCustomer(Long iceCreamShopId, IceCreamShopCustomer iceCreamShopCustomer) {
			 IceCreamShop iceCreamShop = findIceCreamShopByID(iceCreamShopId);
			 Customer customer = findOrCreateCustomer(iceCreamShopId, iceCreamShopCustomer.getCustomerId());
			 copyCustomerFields(customer, iceCreamShopCustomer);
			 customer.getIceCreamShop().add(iceCreamShop);
			 iceCreamShop.getCustomers().add(customer);
			 Customer dbCustomer = customerDao.save(customer);
			 return new IceCreamShopCustomer(dbCustomer); 
    }

		 @Transactional(readOnly = false)
		 public IceCreamShopFlavor saveFlavor(Long iceCreamShopId, IceCreamShopFlavor iceCreamShopFlavor) {
			 IceCreamShop iceCreamShop = findIceCreamShopByID(iceCreamShopId);
			 Flavor flavor = findOrCreateFlavor(iceCreamShopId, iceCreamShopFlavor.getFlavorId());
			 copyFlavorFields(flavor, iceCreamShopFlavor);
			 flavor.setIceCreamShop(iceCreamShop);
			 iceCreamShop.getFlavors().add(flavor);
			 Flavor dbFlavor = flavorDao.save(flavor);
			 return new IceCreamShopFlavor(dbFlavor); 
    }

	


   
    
		 @Transactional(readOnly = true) 
		 public List<IceCreamShopData> retrieveAllIceCreamShops() {
			 List<IceCreamShop> iceCreamShops = iceCreamShopDao.findAll();
			 List<IceCreamShopData> result = new LinkedList<>();
			 for(IceCreamShop iceCreamShop : iceCreamShops) {
				 IceCreamShopData psd = new IceCreamShopData(iceCreamShop);
        	
				 psd.getCustomers().clear();
				 psd.getEmployees().clear();
				 psd.getFlavors().clear();
        	
				 result.add(psd);
        }
			 return result;
	}

		 @Transactional(readOnly = true)
		 public IceCreamShopData retrieveIceCreamShopById(Long iceCreamShopId) {
			 return new IceCreamShopData(findIceCreamShopByID(iceCreamShopId)); 
	}
		 @Transactional(readOnly = true)
		 public void deleteIceCreamShopById(Long iceCreamShopId) {
			 IceCreamShop iceCreamShop = findIceCreamShopByID(iceCreamShopId); 
			 iceCreamShopDao.delete(iceCreamShop); 
		}
    
}