package com.productService.ProductService.service;

import com.productService.ProductService.model.ProductRequest;
import com.productService.ProductService.model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest productRequest) ;

	ProductResponse getProductById(long productId);

	void reduceQuantity(long productId, long quantity);

}
