package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Product;
import com.example.demo.Service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {
	@Autowired
	ProductService service;
	
	
	@GetMapping("/view")
	public List<Product> getAllProducts()
	{
		return service.getAllProducts();
	}
	
	@PostMapping("/add")
	public String addProduct(@Valid @RequestBody Product pr)
	{
		return service.addProduct(pr);
	}
	
	@GetMapping("/get/{id}")
	public Product getProductById(@PathVariable int id)
	{
		return service.getProductById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id)
	{
		return service.deleteProduct(id);
	}
	
	@PutMapping("/update/{id}")
	public Product updateProduct(@PathVariable int id,@Valid @RequestBody Product p)
	{
		return service.updateProduct(id, p);
	}
	
	@GetMapping("/prodcategory/{category}")
	public List<Product> productByCategory(@PathVariable String category)
	{
		return service.productByCategory(category);
	}
	
	@GetMapping("/avaliable/{id}")
	public String totalAvaliable(@PathVariable int id)
	{
		return service.totalAvaliable(id);
	}
	
	@GetMapping("/lowstock/{threshold}")
	public List<Product> lowStockProducts(@PathVariable int threshold)
	{
		return service.lowStockProducts(threshold);
	}
	
	@GetMapping("/outofstock")
	public List<Product> getOutOfStockProducts()
	{
		return service.getOutOfStockProducts();
	}
	
	@GetMapping("/count")
	public String count()
	{
		return service.count();
	}
	

}
