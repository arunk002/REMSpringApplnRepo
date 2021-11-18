package com.mph.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mph.entity.Property;
import com.mph.entity.PurchaseOrder;
import com.mph.entity.Seller;
import com.mph.service.PropertyService;
import com.mph.service.PurchaseOrderService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/purchaseorder")
@EnableSwagger2
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowCredentials = "true", allowedHeaders = "*")

public class PurchaseOrderRestController {

	@Autowired
	PurchaseOrderService purchaseOrderService;
	@Autowired
	PropertyService propertyService;

	@GetMapping("/getpurchaseOrder")
	public ResponseEntity<List<PurchaseOrder>> getProperty() {
		List<PurchaseOrder> purchaseOrderList = purchaseOrderService.getPurchaseOrderList();
		return ResponseEntity.ok().body(purchaseOrderList);
	}

	@PutMapping("/updatepurchaseOrder")
	public ResponseEntity<?> updatePurchaseOrder(@RequestBody PurchaseOrder purchaseorder) {
		String report = purchaseOrderService.updatePurchaseOrder(purchaseorder);
		return ResponseEntity.ok().body(report);
	}

	@PostMapping("/createpurchaseOrder")
	public PurchaseOrderService createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) throws Exception {
		Property property = propertyService.getPropertyById(purchaseOrder.getProperty().getProperty_Id());
		if (!property.isStatus()) {
			throw new Exception("property is not available");
		}
		purchaseOrderService.createPurchaseOrder(purchaseOrder);
		return purchaseOrderService;
	}

	@DeleteMapping("/deletepurchaseOrder/{sellerId}")
	public ResponseEntity<?> deletePurchaseOrder(@PathVariable("sellerid") int sellerId) {
		String report = purchaseOrderService.deletePurchaseOrder(sellerId);
		return ResponseEntity.ok().body(report);
	}

}
