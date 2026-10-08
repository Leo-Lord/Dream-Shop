package com.LOL.dreamshops.service.product;

import java.util.List;

import com.LOL.dreamshops.dto.ProductDto;
import com.LOL.dreamshops.model.Product;
import com.LOL.dreamshops.request.AddProductRequest;
import com.LOL.dreamshops.request.ProductUpdateRequest;

public interface IProductService {

  Product addProduct(AddProductRequest product);

  Product getProductById(Long id);

  void deleteProductById(Long id);

  Product updateProduct(ProductUpdateRequest request, Long productId);

  List<Product> getAllProducts();

  List<Product> getProductsByCategory(String category);

  List<Product> getProductsByBrand(String brand);

  List<Product> getProductsByCategoryAndBrand(String category, String brand);

  List<Product> getProductsByName(String name);

  List<Product> getProductsByBrandAndName(String brand, String name);

  Long countProductsByBrandAndName(String brand, String name);

	ProductDto convertToDto(Product product);

	List<ProductDto> getConvertedProducts(List<Product> products);

}
