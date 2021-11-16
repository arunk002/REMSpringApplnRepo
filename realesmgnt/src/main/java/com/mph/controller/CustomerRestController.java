package com.mph.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.mph.entity.Buyer;
import com.mph.entity.Seller;
import com.mph.service.CustomerService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/home")
@EnableSwagger2
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowCredentials = "true", allowedHeaders = "*")
public class CustomerRestController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/getseller")
	public ResponseEntity<List<Seller>> getSeller() {
		List<Seller> sellerlist = customerService.getSellerList();
		return ResponseEntity.ok().body(sellerlist);
	}

	@PutMapping("/updateseller")
	public void updateSeller(@RequestBody Seller seller) {
		String report = customerService.updateSeller(seller);
		System.out.println(report);
	}

	@PostMapping("/createseller")
	public void createSeller(@RequestBody Seller seller) {
		String report = customerService.createSeller(seller);
		System.out.println(report);
	}

	@GetMapping("/sellerbyid/{sellerid}")
	public ResponseEntity<?> SearchSellerById(@PathVariable("sellerid") int sellerId) {
		System.out.println("seller by id");
		Seller seller = customerService.getSellerById(sellerId);
		return ResponseEntity.ok().body(seller);
	}

	@DeleteMapping("/deleteseller/{sellerid}")
	public void deleteSeller(@PathVariable("sellerid") int sellerId) {
		String report = customerService.deleteSeller(sellerId);
		System.out.println(report);
	}

	// ------------------------------BUYER PART-------------------------------

	@GetMapping("allcustomer")
	public ResponseEntity<List<Buyer>> showBuyer() {
		List<Buyer> blist = customerService.getBuyerList();
		return new ResponseEntity<List<Buyer>>(blist, HttpStatus.OK);
	}

	@PostMapping("/registerBuyer")
	public void createBuyer(@RequestBody Buyer buyer) {
		String report = customerService.createBuyer(buyer);
		System.out.println("from register buyrer " + report);
	}

	@PutMapping("/updateBuyer")
	public ResponseEntity<Buyer> updateBuyer(@RequestBody Buyer buyer) {
		System.out.println("Update Rest () " + buyer);
		customerService.updateBuyer(buyer);
		return new ResponseEntity<Buyer>(buyer, HttpStatus.OK);
	}

	@DeleteMapping("/deleteBuyer/{id}")
	public ResponseEntity<?> deleteBuyer(@PathVariable("id") int bid) {
		String report = customerService.deleteBuyer(bid);
		return new ResponseEntity<String>(report, HttpStatus.OK);
	}

	@GetMapping("/buyerbyid/{buyerid}")
	public ResponseEntity<?> SearchBuyerById(@PathVariable("buyerid") int buyerId) {
		System.out.println("Buyer by id");
		Buyer buyer = customerService.getBuyerById(buyerId);
		return ResponseEntity.ok().body(buyer);
	}

}
