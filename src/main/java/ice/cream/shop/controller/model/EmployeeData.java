package ice.cream.shop.controller.model;

import ice.cream.shop.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeData {
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeePhone;
    private String employeeJobTitle;
    
    public EmployeeData(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.employeeFirstName = employee.getEmployeeFirstName();
        this.employeeLastName = employee.getEmployeeLastName();
        this.employeePhone = employee.getEmployeePhone();
        this.employeeJobTitle = employee.getEmployeeJobTitle();
    
    }
}