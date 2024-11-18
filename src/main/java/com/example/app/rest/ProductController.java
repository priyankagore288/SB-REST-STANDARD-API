package com.example.app.rest;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;

import com.example.app.Application;
import com.example.app.model.Product;
import com.example.app.service.ProductService;

@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {
	
	
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(value = "/products",
		produces =  {"application/json"})
	
	public ResponseEntity<List<Product>> getAllProducts()
	{
	   List<Product>prlist = productService.getAllProduct();
	   
	   return new ResponseEntity<List<Product>>(prlist,HttpStatus.OK);
	}
	
	@GetMapping(value = "/products/{id}",
			produces = {"application/xml","application/json"})
	public ResponseEntity<Product> getProduct(@PathVariable int id)
	{
	  Product product = productService.getProduct(id);
	  if(product!=null) {
	  return new ResponseEntity<Product>(product,HttpStatus.OK);
	  }
	  return new  ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value="/products/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable int id)
	{
		 productService.deleteProduct(id);
		 return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/products",
			consumes = {"application/xml","application/json"},
	        produces = {"application/xml","application/json"}
			)
	public ResponseEntity<Product> addProduct(@RequestBody Product product)
	{
	 Product product1 = productService.addProduct(product);
	 return new ResponseEntity<Product>(product1, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id,@RequestBody Product product)
	{
	    Product prod = productService.updateProduct(id,product);
	    if(prod!=null)
	    {
	    	return new ResponseEntity<Product>(prod,HttpStatus.OK);
	    			
	    }
	    return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}
}

