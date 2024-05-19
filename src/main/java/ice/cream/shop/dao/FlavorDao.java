package ice.cream.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ice.cream.shop.entity.Flavor;

public interface FlavorDao extends JpaRepository<Flavor, Long> {
}


