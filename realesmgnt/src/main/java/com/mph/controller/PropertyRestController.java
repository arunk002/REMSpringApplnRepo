package com.mph.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mph.entity.Property;
import com.mph.service.PropertyService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/property")
@EnableSwagger2
@CrossOrigin(origins = "*",
methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE},
allowCredentials = "true",allowedHeaders = "*")

public class PropertyRestController {
	
	@Autowired
	PropertyService propertyService;
	
	@GetMapping("/getproperty")
	public ResponseEntity<List<Property>> getProperty(){
		List<Property> propertylist = propertyService.getAllProperty();
		return ResponseEntity.ok().body(propertylist);
	}
	
	@PutMapping("/updateproperty")
	public void  updateProperty(@RequestBody Property property){
		String p = propertyService.updateProperty(property);
	}
	
	@PostMapping("/createproperty")
	public Property addProperty(@RequestBody Property property){
		System.out.println("from prop");
		System.out.println(property);
		propertyService.addProperty(property);
		return property;
	}
	
	@DeleteMapping("/deleteproperty/{property_Id}")
	public void deleteProperty(@PathVariable("property_Id") int property_Id){
		String p = propertyService.deleteProperty(property_Id);
	}
	
	@GetMapping("/getpropertybyid/{propertyid}")
	public ResponseEntity<Property> getPropertyById(@PathVariable("propertyid") int propertyId){
		System.out.println(propertyId);
		Property property = propertyService.getPropertyById(propertyId);
		return ResponseEntity.ok().body(property);
	}
	
	
	
}
