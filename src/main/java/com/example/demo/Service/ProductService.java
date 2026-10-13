package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.ExceptionHandling.DuplicateProduct;
import com.example.demo.ExceptionHandling.ProductNotFound;
import com.example.demo.Model.Product;

@Service
public class ProductService {
	
	private List<Product> prod=new ArrayList<>();
	public ProductService()
	{
		prod.add(new Product(101,"Samsung m26","Mobile",20,15600));
		prod.add(new Product(102,"Macbook air","Laptop",5,150000));
		prod.add(new Product(103,"Oppo k15","Mobile",26,25000));
	}
	
	// method to get all products
	public List<Product> getAllProducts()
	{
		return prod;
	}
	
	// method to add product
	public String addProduct(Product pr)
	{
		for(Product p:prod)
		{
			if(p.getProductID()==pr.getProductID())
			{
				throw new DuplicateProduct("Another product already registered with this ID");
			}
		}
		prod.add(pr);
		return "Product added successfully";
	}
	
	// method to get product byID
	public Product getProductById(int id)
	{
		for(Product p:prod)
		{
			if(p.getProductID()==id)
			{
				return p;
			}
		}
		throw new ProductNotFound("No product found with this ID");
	}
	
	// method to delete product
	public String deleteProduct(int id)
	{
		Product p=getProductById(id);
		prod.remove(p);
		return "Product deleted successfully";
	}
	
	// method to update product
	public Product updateProduct(int id,Product p)
	{
		Product pt=getProductById(id);
		pt.setProductName(p.getProductName());
		pt.setCategory(p.getCategory());
		pt.setStock(p.getStock());
		pt.setPrice(p.getPrice());
		return pt;
	}
	
	// method to get all products in a category
	public List<Product> productByCategory(String category)
	{
		List<Product> li=new ArrayList<>();
		for(Product p:prod)
		{
			if(p.getCategory().equalsIgnoreCase(category))
			{
				li.add(p);
			}
		}
		if(li.isEmpty())
		{
			throw new ProductNotFound("No products found in this category");
		}
		return li;
	}
	
	// method to get total available stocks of a specific product
	public String totalAvaliable(int id)
	{
		Product pt=getProductById(id);
		if(pt.getStock()>0)
		{
			return "Total avaliable units for this product: "+pt.getStock();
		}
			
		return "This product is out of stock";
	}
	
	// method to get low stock products
	public List<Product> lowStockProducts(int threshold)
	{
		List<Product> result= prod.stream().filter(p -> p.getStock()<threshold).toList();
		if(result.isEmpty())
		{
			throw new ProductNotFound("No products found with stock below "+threshold);
		}
		return result;
		
	}
	
	// method to get out of stock products
	public List<Product> getOutOfStockProducts()
	{
		List<Product> result=prod.stream().filter(p->p.getStock()==0).toList();
		if(result.isEmpty())
		{
			throw new ProductNotFound("No out of stock products found");
		}
		return result;
	}
	
	// method to get total products
	public String count()
	{
		return "Total products: "+prod.size();
	}
	
	

}
