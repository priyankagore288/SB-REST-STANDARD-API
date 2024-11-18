package com.example.app.service;

import java.util.List;

import com.example.app.model.Product;

public interface ProductService {

	List<Product> getAllProduct();

	Product getProduct(int id);

	void deleteProduct(int id);

	Product addProduct(Product product);

	Product updateProduct(int id, Product product);

}
