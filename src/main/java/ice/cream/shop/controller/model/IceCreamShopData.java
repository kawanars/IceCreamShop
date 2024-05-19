package ice.cream.shop.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import ice.cream.shop.entity.Customer;
import ice.cream.shop.entity.Employee;
import ice.cream.shop.entity.Flavor;
import ice.cream.shop.entity.IceCreamShop;



@Data
@NoArgsConstructor
public class IceCreamShopData {
	
	private Long iceCreamShopId;
    private String iceCreamShopName;
    private String iceCreamShopAddress;
    private String iceCreamShopCity;
    private String iceCreamShopTime;
    private String iceCreamShopPhone;

	  private Set<IceCreamShopCustomer> customers = new HashSet<>();
	  private Set<IceCreamShopEmployee> employees = new HashSet<>();
	  private Set<IceCreamShopFlavor> flavors = new HashSet<>();  


	  @Data
	  @NoArgsConstructor
	  public static class IceCreamShopCustomer {
		    private Long customerId;
		    private String customerFirstName;
		    private String customerLastName;
		    private String customerEmail;
		    private String customerPhone;

		    public IceCreamShopCustomer(Customer customer) {
		      this.customerId = customer.getCustomerId();
		      this.customerFirstName = customer.getCustomerFirstName();
		      this.customerLastName = customer.getCustomerLastName();
		      this.customerEmail = customer.getCustomerEmail();
		      this.customerPhone = customer.getCustomerPhone();
		    }
	  }
	  

	  @Data
	  @NoArgsConstructor
	  public static class IceCreamShopEmployee {
		  private Long employeeId;
		    private String employeeFirstName;
		    private String employeeLastName;
		    private String employeePhone;
		    private String employeeJobTitle;

		    public IceCreamShopEmployee(Employee employee) {
		      this.employeeId = employee.getEmployeeId();
		      this.employeeFirstName = employee.getEmployeeFirstName();
		      this.employeeLastName = employee.getEmployeeLastName();
		      this.employeePhone = employee.getEmployeePhone();
		      this.employeeJobTitle = employee.getEmployeeJobTitle();
		    }
	  }
	  
	  @Data
	  @NoArgsConstructor
	  public static class IceCreamShopFlavor {
		  private Long flavorId;
		    private String flavorName;

		    public IceCreamShopFlavor(Flavor flavor) {
		      this.flavorId = flavor.getFlavorId();
		      this.flavorName = flavor.getFlavorName();
	  }
	  }
	  
	  public IceCreamShopData(IceCreamShop iceCreamShop) {
		    this.iceCreamShopId = iceCreamShop.getIceCreamShopId();
		    this.iceCreamShopName = iceCreamShop.getIceCreamShopName();
		    this.iceCreamShopAddress = iceCreamShop.getIceCreamShopAddress();
		    this.iceCreamShopCity = iceCreamShop.getIceCreamShopCity();
		    this.iceCreamShopTime = iceCreamShop.getIceCreamShopTime();
		    this.iceCreamShopPhone = iceCreamShop.getIceCreamShopPhone();

		    for (Employee employee : iceCreamShop.getEmployees()) {
		      this.employees.add(new IceCreamShopEmployee(employee));
		    }

		    for (Customer customer : iceCreamShop.getCustomers()) {
		      this.customers.add(new IceCreamShopCustomer(customer));
		    }
		    
		    for (Flavor flavor : iceCreamShop.getFlavors()) {
			      this.flavors.add(new IceCreamShopFlavor(flavor));
		    }
	  }
	  
	  
	  
	  }

	  
	 
	  
	  
