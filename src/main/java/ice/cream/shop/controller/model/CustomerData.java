package ice.cream.shop.controller.model;

import ice.cream.shop.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerData {
    private Long customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerPhone;
    
    public CustomerData(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.customerFirstName = customer.getCustomerFirstName();
        this.customerLastName = customer.getCustomerLastName();
        this.customerEmail = customer.getCustomerEmail();
        this.customerPhone = customer.getCustomerPhone();
    }
}