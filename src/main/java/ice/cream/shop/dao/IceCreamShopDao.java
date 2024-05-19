package ice.cream.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ice.cream.shop.entity.IceCreamShop;

public interface IceCreamShopDao extends JpaRepository<IceCreamShop, Long> {
	
	
}