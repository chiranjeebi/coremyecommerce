package com.myecommerce.repository;

import java.util.List;

import com.myecommerce.dto.ProductDTO;
import com.myecommerce.entity.ProductEntity;

public interface ProductRepository {
	
	public ProductEntity addProduct(ProductEntity productEntity);	
	public List<ProductEntity> updateProductPrice(Long productId, Double newPrice);
	public ProductEntity deleteProductById(Long productId);
	public List<ProductEntity> seachProductByName(String name);
	public List<ProductEntity> getAllproduct();
	public ProductEntity getProduct(Long productId);
	
}