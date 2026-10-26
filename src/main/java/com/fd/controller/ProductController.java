package com.fd.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fd.model.Product;
import com.fd.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	ProductService productService ;
	  @GetMapping("/hello") // http://localhost:8080/hello
	    String productMessage() {
	        return "Hello Spring Rest to build my backend!";
	    }
	  @GetMapping("/products")
	  //@GetMapping(path = "/products", produces = {MediaType.APPLICATION_XML_VALUE})
//	  List<Product> showProduct(){
//		  return productService.findAllProducts();
//	  }
	  public ResponseEntity<List<Product>> showProduct(){
		  List<Product> products = productService.findAllProducts();
		  return ResponseEntity.ok(products);
//		  return productService.findAllProducts();
	  }

	  @PostMapping("/products")
	  public String addProducts(@RequestParam int id,
			  @RequestParam String pname,
			  @RequestParam double price) {
		  Product product = new Product();
		  product.setId(id);
		  product.setPname(pname);
		  product.setPrice(price);
		  
		  productService.addProducts(product);
		  return "redirect : /home.html";
	  }
	  public Product addProduct(@RequestBody Product product) {
		  return productService.saveProduct(product);
	  }
	  @PutMapping("/products/{id}")
	    public Product updateProduct(
	            @PathVariable int id,
	            @RequestBody Product product) {

	        product.setId(id);
	        return productService.updateProduct(product);
	    }
	  @GetMapping("/products/search")
	    public List<Product> searchProducts(@RequestParam String name) {

	        List<Product> result = new ArrayList<>();
	        for (Product product : productService.findAllProducts()) {
	            if (product.getPname().equalsIgnoreCase(name)) {
	                result.add(product);
	            }
	        }
	        return result;
	    }
	  @DeleteMapping("/products/{id}")
	  public String deleteProduct(@PathVariable int id) {
	      productService.deleteProduct(id);
	      return "Product deleted successfully";
	  }

}
