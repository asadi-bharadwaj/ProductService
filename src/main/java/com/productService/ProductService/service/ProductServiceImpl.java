package com.productService.ProductService.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productService.ProductService.entity.Product;
import com.productService.ProductService.exception.ProductServiceCustomException;
import com.productService.ProductService.model.ProductRequest;
import com.productService.ProductService.model.ProductResponse;
import com.productService.ProductService.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public long addProduct(ProductRequest productRequest) {
		log.info("Adding Product...");
		Product product = Product.builder().productName(productRequest.getName()).quantity(productRequest.getQuantity())
				.price(productRequest.getPrice()).build();
		productRepository.save(product);

		log.info("Product created");
		return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(long productId) {
		log.info("inside getProductById method, getting details of product.....");
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new ProductServiceCustomException("product with given id not found", "PRODUCT_NOT_FOUND"));
		ProductResponse productResponse = new ProductResponse();
		BeanUtils.copyProperties(product, productResponse);
		return productResponse;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {
		log.info("reduce quantity {} for id : {}" + quantity, productId);
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new ProductServiceCustomException("product with given id not found", "PRODUCT_NOT_FOUND"));

		if (product.getQuantity() < quantity) {
			throw new ProductServiceCustomException("Product does not have sufficient quantity",
					"INSUFFICIENT_QUANTITY");
		}
		product.setQuantity(product.getQuantity() - quantity);
		productRepository.save(product);
		
		log.info("product quantity updated successfully");
	}

}
