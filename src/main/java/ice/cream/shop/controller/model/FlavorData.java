package ice.cream.shop.controller.model;

import ice.cream.shop.entity.Flavor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlavorData {
    private Long flavorId;
    private String flavorName;
    
    public FlavorData(Flavor flavor) {
    	this.flavorId = flavor.getFlavorId();
    	this.flavorName = flavor.getFlavorName();
    	
    	
    }
}
