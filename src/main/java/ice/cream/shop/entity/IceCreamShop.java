package ice.cream.shop.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class IceCreamShop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iceCreamShopId;
    private String iceCreamShopName;
    private String iceCreamShopAddress;
    private String iceCreamShopCity;
    private String iceCreamShopTime;
    private String iceCreamShopPhone;
    
  //Many to one
    @EqualsAndHashCode.Exclude
 	@ToString.Exclude
     @OneToMany(mappedBy = "iceCreamShop", cascade = CascadeType.ALL, orphanRemoval = true)
     private Set<Employee> employees = new HashSet<>();
    
  //Many to one
    @EqualsAndHashCode.Exclude
  	@ToString.Exclude
      @OneToMany(mappedBy = "iceCreamShop", cascade = CascadeType.ALL, orphanRemoval = true)
      private Set<Flavor> flavors = new HashSet<>();
    
  //Many to Many
    @EqualsAndHashCode.Exclude
	@ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "ice_cream_shop_customer", 
    joinColumns = @JoinColumn(name = "ice_cream_shop_id"), 
    inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private Set<Customer> customers = new HashSet<>();


}
