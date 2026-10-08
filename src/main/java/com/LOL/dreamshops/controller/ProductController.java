package com.LOL.dreamshops.controller;

import java.util.List;

import com.LOL.dreamshops.exceptions.AlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.LOL.dreamshops.dto.ProductDto;
import com.LOL.dreamshops.exceptions.ResourceNotFoundException;
import com.LOL.dreamshops.model.Product;
import com.LOL.dreamshops.request.AddProductRequest;
import com.LOL.dreamshops.request.ProductUpdateRequest;
import com.LOL.dreamshops.response.ApiResponse;
import com.LOL.dreamshops.service.product.IProductService;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController 
{

	private final IProductService productService;

	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllProducts()
	{
		
		List<Product> products = productService.getAllProducts();
		List<ProductDto> convertedProducts = productService.getConvertedProducts(products);

		return ResponseEntity.ok(new ApiResponse("success!", convertedProducts));

	}

	@GetMapping("/product/{productId}/product")
	public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId)
	{
		
		try 
		{
				
			Product product = productService.getProductById(productId);
			ProductDto productDto = productService.convertToDto(product);

			return ResponseEntity.ok(new ApiResponse("success!", productDto));

		} catch (ResourceNotFoundException e) 
		{

			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

		}

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product)
	{
		
		try 
		{
				
			Product theProduct = productService.addProduct(product);
			ProductDto productDto = productService.convertToDto(theProduct);

			return ResponseEntity.ok(new ApiResponse("Add product success!", productDto));

		} catch (AlreadyExistsException e)
		{

			return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));

		}

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/product/{productId}/update")
	public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId, @RequestBody ProductUpdateRequest request)
	{
		
		try 
		{
				
			Product theProduct = productService.updateProduct(request, productId);
			ProductDto productDto = productService.convertToDto(theProduct);

			return ResponseEntity.ok(new ApiResponse("Update product success!", productDto));

		} catch (ResourceNotFoundException e) 
		{

			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

		}

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/product/{productId}/delete")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId)
	{
		
		try 
		{
				
			productService.deleteProductById(productId);

			return ResponseEntity.ok(new ApiResponse("Delete product success!", productId));

		} catch (ResourceNotFoundException e) 
		{

			return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

		}

	}

	@GetMapping("/products/by/brand-and-name")
	public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName)
	{
		
		try 
		{
				
			List<Product> products = productService.getProductsByBrandAndName(brandName, brandName);
			
			if (products.isEmpty())
			{
			
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
			
			}

			List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("success!", convertedProducts));

		} catch (Exception e) 
		{

			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

		}

	}

	@GetMapping("/products/by/category-and-brand")
	public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String brand, @RequestParam String category)
	{
		
		try 
		{
				
			List<Product> products = productService.getProductsByBrandAndName(brand, category);
			
			if (products.isEmpty())
			{
			
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
			
			}

			List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("success!", convertedProducts));

		} catch (Exception e) 
		{

			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

		}

	}

	@GetMapping("/products/{name}/products")
	public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name)
	{
		
		try 
		{
				
			List<Product> products = productService.getProductsByName(name);
			
			if (products.isEmpty())
			{
			
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
			
			}

			List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("success!", convertedProducts));

		} catch (Exception e) 
		{

			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

		}

	}

	@GetMapping("/products/by-brand")
	public ResponseEntity<ApiResponse> findProductByBrand(@RequestParam String brand)
	{
		
		try 
		{
				
			List<Product> products = productService.getProductsByBrand(brand);

			if (products.isEmpty())
			{
			
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
			
			}

			List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("success!", convertedProducts));

		} catch (Exception e) 
		{

			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

		}

	}

	@GetMapping("/products/{category}/all/products")
	public ResponseEntity<ApiResponse> finedProductByCategory(@PathVariable String category)
	{
		
		try 
		{
				
			List<Product> products = productService.getProductsByCategory(category);
		
			if (products.isEmpty())
			{
			
				return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null));
			
			}

			List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("success!", convertedProducts));

		} catch (Exception e) 
		{

			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

		}

	}

	@GetMapping("/products/count/by-brand/and-name")
	public ResponseEntity<ApiResponse> countProductByBrandAndName(@RequestParam String brand, @RequestParam String Name)
	{
		
		try 
		{
				
			var productCount = productService.countProductsByBrandAndName(brand, Name);

			return ResponseEntity.ok(new ApiResponse("Product count!", productCount));

		} catch (Exception e) 
		{

			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));

		}

	}

}
