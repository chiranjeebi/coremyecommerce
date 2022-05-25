package com.myecommerce.repository.impl;

import java.util.List;

import com.myecommerce.configuration.DBConfiguration;
import com.myecommerce.entity.ProductEntity;
import com.myecommerce.repository.ProductRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductRepositoryImpl  implements ProductRepository{

	@Override
	public ProductEntity addProduct(ProductEntity productEntity) {
		Connection connection= DBConfiguration.getDBconnection();
		String sql = "INSERT INTO Users (product_name, descripition, price_per_qty, available_qty,category_id_fk) VALUES (?, ?, ?, ?,?)";
		try {
			  PreparedStatement statement = connection.prepareStatement(sql);
			  statement.setString(1, productEntity.getProductName());
	            statement.setString(2, productEntity.getDescription());
	            statement.setDouble(3,productEntity.getPricePerQty());
	            statement.setInt(4, productEntity.getAvailableQty());
	            statement.setLong(5, productEntity.getCategoryEntity().getCategoryId());
	            
	            int rowInserted =statement.executeUpdate();
	            if(rowInserted>0) {
					System.out.println("A new product was inserted successfully!");
	            	   }
		} catch ( SQLException e) {
			e.printStackTrace();
		}
		return productEntity;
	}

	@Override
	public List<ProductEntity> getAllproduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductEntity> updateProductPrice(Long productId, Double newPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductEntity> seachProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductEntity deleteProductById(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductEntity getProduct(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}


}
