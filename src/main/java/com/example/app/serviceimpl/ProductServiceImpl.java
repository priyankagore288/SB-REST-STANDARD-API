package com.example.app.serviceimpl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.model.Product;
import com.example.app.repository.ProductRepository;
import com.example.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProduct() {
		
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(int id) {
		if(productRepository.existsById(id))
		{
			return productRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public void deleteProduct(int id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public Product addProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(int id, Product product) {
		if(productRepository.existsById(id))
		{
			product.setProductId(id);
			return productRepository.save(product);
		}
		return null;
	}
	
	

}
