package ice.cream.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ice.cream.shop.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {
}
