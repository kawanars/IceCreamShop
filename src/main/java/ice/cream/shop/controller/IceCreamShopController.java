package ice.cream.shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ice.cream.shop.controller.model.IceCreamShopData;
import ice.cream.shop.controller.model.IceCreamShopData.IceCreamShopCustomer;
import ice.cream.shop.controller.model.IceCreamShopData.IceCreamShopEmployee;
import ice.cream.shop.controller.model.IceCreamShopData.IceCreamShopFlavor;
import ice.cream.shop.service.IceCreamShopService;


@RestController
@RequestMapping("/ice_cream_shop")
@Slf4j
public class IceCreamShopController {
	@Autowired
	private IceCreamShopService iceCreamShopService; 
	
	
	//Endpoint to insert a new ice cream shop
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public IceCreamShopData insertIceCreamShop(@RequestBody IceCreamShopData iceCreamShopData) {
		log.info("creating ice cream shop {}", iceCreamShopData);
		return iceCreamShopService.saveIceCreamShop(iceCreamShopData);
	}
	//Endpoint to update an existing ice cream shop
	@PutMapping("/{iceCreamShopId}")
	public IceCreamShopData updateIceCreamShop(@PathVariable Long iceCreamShopId, 
			@RequestBody IceCreamShopData iceCreamShopData) {
		iceCreamShopData.setIceCreamShopId(iceCreamShopId); 
		log.info("Updating ice cream shop {}", iceCreamShopData);
		return iceCreamShopService.saveIceCreamShop(iceCreamShopData);
	}
	
	//Endpoint to add an employee to an ice cream shop
	@PostMapping("/{iceCreamShopId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public IceCreamShopEmployee addEmployeeToIceCreamShop(@PathVariable Long iceCreamShopId,
			@RequestBody IceCreamShopEmployee iceCreamShopEmployee) {
		log.info("Adding employee () to ice cream shop: with ID=()", iceCreamShopEmployee, iceCreamShopId);
		return iceCreamShopService.saveEmployee(iceCreamShopId, iceCreamShopEmployee);
	}

	//Endpoint to  add an customer to an ice cream shop
	@PostMapping("/{iceCreamShopId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public IceCreamShopCustomer addCustomerToIceCreamShop(@PathVariable Long iceCreamShopId,
			@RequestBody IceCreamShopCustomer iceCreamShopCustomer) {
		log.info("Adding customer to ice cream shop: with ID ()", iceCreamShopCustomer, iceCreamShopId);
		return iceCreamShopService.saveCustomer(iceCreamShopId, iceCreamShopCustomer);
	}
	
	//Endpoint to  add an flavor to an ice cream shop
	@PostMapping("/{iceCreamShopId}/flavor")
	@ResponseStatus(code = HttpStatus.CREATED)
	public IceCreamShopFlavor addFlavorToIceCreamShop(@PathVariable Long iceCreamShopId,
			@RequestBody IceCreamShopFlavor iceCreamShopFlavor) {
		log.info("Adding flavor to ice cream shop: with ID ()", iceCreamShopFlavor, iceCreamShopId);
		return iceCreamShopService.saveFlavor(iceCreamShopId, iceCreamShopFlavor);
	}

	//Endpoint to retrieve all ice cream shops
	@GetMapping
	public List<IceCreamShopData> getAllIceCreamShops() {
		log.info("Retrieving all ice cream shops");
		return iceCreamShopService.retrieveAllIceCreamShops();
	}

	//Endpoint to retrieve an ice cream shop by ID
	@GetMapping("/{iceCreamShopId}")
	public IceCreamShopData getIceCreamShopById(@PathVariable Long iceCreamShopId) {
		log.info("Retrieving ice cream shop with ID=()", iceCreamShopId);
		return iceCreamShopService.retrieveIceCreamShopById(iceCreamShopId);
	}

	//Endpoint to delete an ice cream shop by ID
	@DeleteMapping("/{iceCreamShopId}")
	public Map<String, String> deleteIceCreamShopById(@PathVariable Long iceCreamShopId) {
		log.info("Deleting ice cream shop with ID=()" + iceCreamShopId);
		iceCreamShopService.deleteIceCreamShopById(iceCreamShopId);
		return Map.of("message", "Ice cream shop with ID=" + iceCreamShopId + "deleted.");
		
	}

}