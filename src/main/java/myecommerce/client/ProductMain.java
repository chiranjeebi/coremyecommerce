package com.myecommerce.client;

import java.util.List;
import java.util.Scanner;

import com.myecommerce.dto.CategoryDTO;
import com.myecommerce.dto.ProductDTO;
import com.myecommerce.exception.BusinessException;
import com.myecommerce.service.ProductService;
import com.myecommerce.service.impl.ProductServiceImpl;

public class ProductMain {
	public static void main(String[] args) {

		ProductService productService = new ProductServiceImpl();
		while (true) {
			createMenu();
			Scanner scanner = new Scanner(System.in);
			Integer choice = scanner.nextInt();

			if (choice.equals(1)) {
				addProduct(productService);
			} else if (choice.equals(2)) {
				getAllProduct(productService);
			} else if (choice.equals(3)) {
				getProduct(productService);
			} else if (choice.equals(4)) {
				updateProductPrice(productService);
			} else if (choice.equals(5)) {
				searchProduct(productService);
			} else if (choice.equals(6)) {
				deleteProduct(productService);
			} else if (choice.equals(100)) {
				System.out.println("Exiting Application");
				System.exit(0);

			}
			/*
			 * Cannot make a static reference to the non-static method
			 * addProduct(ProductService) from the type ProductMain
			 */
		}
	}

	private static void createMenu() {
		System.out.println("************************");
		System.out.println("Enter 1 for Add Product");
		System.out.println("Enter 2 for Get All Product");
		System.out.println("Enter 3 to Get a Product Detail");
		System.out.println("Enter 4 to Update a product Price ");
		System.out.println("Enter 5 to Search a product by Name");//Implement Search Product By Product Name
		System.out.println("Enter 6 to Delete a product by Id");//Implement delete Product By Product id
		System.out.println("Enter 100 to Stop The Application ");
		System.out.println("************************");

	}

	private static void searchProduct(ProductService productService) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the product for search ");
		String name = sc.nextLine();
		List<ProductDTO> foundList = productService.seachProductByName(name);
		if (null != foundList) {
			for (ProductDTO dto : foundList) {
				System.out.println("Product Id: " + dto.getProductId());
				System.out.println("Product Name: " + dto.getProductName());
				System.out.println("updated Product Price: " + dto.getPricePerQty());
				System.out.println("Category :" + dto.getCategoryDTO().getCategoryName());
				System.out.println("********");
			}
		} else {
			System.out.println("no product found " + name);

		}
	}

	private static void deleteProduct(ProductService productService) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the product id for delete");
		Long productId = sc.nextLong();
		ProductDTO dto = productService.deleteProductById(productId);
		if (null != dto) {
			System.out.println("**Below product has been Deleted***");
			System.out.println("Product Id: " + dto.getProductId());
			System.out.println("Product Name: " + dto.getProductName());
			System.out.println("Product Price: " + dto.getPricePerQty());
			System.out.println("Category Price :" + dto.getCategoryDTO().getCategoryName());
		} else {
			System.out.println("no product found");
		}
	}

	public static void updateProductPrice(ProductService productService) {
		getAllProduct(productService);
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the product id for update");
		Long productId = sc.nextLong();
		System.out.println("enter the new price");
		Double newPrice = sc.nextDouble();
		ProductDTO dto = productService.updateProductPrice(productId, newPrice);
	
		if(null != dto) {
			System.out.println("****Product found with Id "+productId+" ****");
			System.out.println("Product Id: " + dto.getProductId());
			System.out.println("Product Name : " + dto.getProductName());
			System.out.println("Updated Product Price : " + dto.getPricePerQty());
			System.out.println("Category : " + dto.getCategoryDTO().getCategoryName());
		}else {
			System.out.println("****Product NOT found with Id "+productId+" ****");
		}
	}

	private static void getProduct(ProductService productService) {
		getAllProduct(productService);
		Scanner sc = new Scanner(System.in);

		System.out.println("enter the product id to see the detials");
		Long productId = sc.nextLong();
		ProductDTO dto = productService.getProduct(productId);
		
		if (null != dto) {
			System.out.println("**product found with the Id" + productId + "***");
			System.out.println("Product Id: " + dto.getProductId());
			System.out.println("Product Name: " + dto.getProductName());
			System.out.println("Product Price: " + dto.getPricePerQty());
			System.out.println("Category Price :" + dto.getCategoryDTO().getCategoryName());
		}else {
			System.out.println("****Product NOT found with Id "+productId+" ****");
		}
	}

	private static void getAllProduct(ProductService productService) {

		List<ProductDTO> products = productService.getAllproduct();

		/*
		 * try { for (Integer count = 0; count < products.size(); count++) {
		 * 
		 * System.out.println("Product Name: " + products.get(count).getProductName());
		 * System.out.println("Product Price: " + products.get(count).getPricePerQty());
		 * System.out.println("Product Description: " +
		 * products.get(count).getDescription()); } } catch (Exception ex) {
		 * System.out.println("some error occure");
		 * 
		 * }
		 */
		System.out.println("****Displaying All Products*****");
		if (null != products) {
			for (ProductDTO productDTO : products) {
				System.out.println("Product Id: " + productDTO.getProductId());
				System.out.println("Product Name : " + productDTO.getProductName());
				System.out.println("Product Price : " + productDTO.getPricePerQty());
				System.out.println("Available Qty : " + productDTO.getAvailableQty());
				System.out.println("Category name : " + productDTO.getCategoryDTO().getCategoryName());
			}
		}

	}

	private static void addProduct(ProductService productService) {

		Scanner sc = new Scanner(System.in);

		ProductDTO dto = new ProductDTO();
		dto.setProductId(System.currentTimeMillis());
		System.out.println("Enter product name: ");
		dto.setProductName(sc.nextLine());
		System.out.println("Enter product description: ");
		dto.setDescription(sc.nextLine());
		System.out.println("Enter product price: ");
		dto.setPricePerQty(sc.nextDouble());
		System.out.println("Enter product qty: ");
		dto.setAvailableQty(sc.nextInt());
		System.out.println("Enter category id: ");
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(sc.nextLong());
		sc.nextLine();//flushes the stream
		dto.setCategoryDTO(categoryDTO);
		
		ProductDTO productDTO = null;
		try {
			productDTO = productService.addProduct(dto);
		} catch (BusinessException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getErrorMsg());
		}
		if (null != productDTO) {
			System.out.println("Product was added successfully!");
		}
	}
}