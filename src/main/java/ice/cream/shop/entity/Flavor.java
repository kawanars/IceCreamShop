package ice.cream.shop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Flavor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long flavorId;
	    private String flavorName;
	    
	    @EqualsAndHashCode.Exclude
		@ToString.Exclude
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "ice_cream_shop_id")
	    private IceCreamShop iceCreamShop;



}
