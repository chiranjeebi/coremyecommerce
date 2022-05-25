package com.myecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.myecommerce.converter.ProductConverter;
import com.myecommerce.dto.CategoryDTO;
import com.myecommerce.dto.ProductDTO;
import com.myecommerce.entity.ProductEntity;
import com.myecommerce.exception.BusinessException;
import com.myecommerce.repository.ProductRepository;
import com.myecommerce.repository.impl.ProductRepositoryImpl;
import com.myecommerce.service.ProductService;
import com.myecommerce.service.impl.ProductServiceImpl;

/**
 * 
 * @author myecommerce pvt ltd class name:ProductServiceImpl description:this
 *         class helps to provide functionality like adding new product to the
 *         system,searching product, getting product details,updating,deleting
 *         product.
 *
 */
public class ProductServiceImpl implements ProductService {
	
	private List<ProductDTO> productList = new ArrayList<>();
	private ProductRepository productRepository;
	 
	public ProductServiceImpl() {
		 this.productRepository  = new ProductRepositoryImpl();
	 }

	@Override
	public ProductDTO addProduct(ProductDTO productDTO) throws BusinessException {
		/* Below line adds a new product the existing product list in inventory */
		// productList.add(productDTO);
		for (ProductDTO dto : productList) {
			if (dto.getProductName().equalsIgnoreCase(productDTO.getProductName())) {
				BusinessException be = new BusinessException();
				be.setErrorCode("DUP_PRD_001");
				be.setErrorMsg("Product Already Exist");
				throw be;
			}
		}
		ProductEntity pe = ProductConverter.convertProductDTOtoProductEntity(productDTO);
		pe = this.productRepository.addProduct(pe);
		productDTO = ProductConverter.convertProductEntitytoProductDTO(pe);
		//DataSerializer.serializeProduct(productDTO);
		//productList.add(productDTO);
		return productDTO;
	}

	@Override
	public List<ProductDTO> seachProductByName(String name) {
		List<ProductDTO> foundProduct = new ArrayList<>();
		for (ProductDTO dto : this.productList) {
			if (dto.getProductName().contains(name)) {
				foundProduct.add(dto);
			}
		}
		return foundProduct;
	}

	@Override
	public List<ProductDTO> updateProductPrice(Long productId, Double newPrice) {
		ProductDTO dto = this.getProduct(productId);
		if (null != dto) {
			dto.setPricePerQty(newPrice);
			for (Integer i = 0; i < productList.size(); i++) {
				if (productList.get(i).getProductId().equals(productId)) {
					productList.set(i, dto);
					break;
				}
			}
		}
		return null;
	}

	@Override
	public ProductDTO deleteProductById(Long productId) {
		ProductDTO productTobeDeleted = null;
		List<ProductDTO> newListOfProducts = new ArrayList<>();
		for (ProductDTO dto : productList) {
			if (dto.getProductId().equals(productId)) {
				productTobeDeleted = dto;
			} else {
				newListOfProducts.add(dto);
			}
		}
		productList.clear();// reset the old product list
		productList.addAll(newListOfProducts);// add all other products except one to be deleted
		return productTobeDeleted;
	}

	@Override
	public ProductDTO getProduct(Long productId) {
		for (ProductDTO dto : productList) {
			if (dto.getProductId().equals(productId)) {
				return dto;
			}
		}
		return null;
	}

	@Override
	public List<ProductDTO> getAllproduct() {
		// TODO Auto-generated method stub
		return this.productList;
	}

}
