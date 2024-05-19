package ice.cream.shop.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import ice.cream.shop.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
}

