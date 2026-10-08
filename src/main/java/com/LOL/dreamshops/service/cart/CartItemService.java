package com.LOL.dreamshops.service.cart;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.LOL.dreamshops.exceptions.ResourceNotFoundException;
import com.LOL.dreamshops.model.Cart;
import com.LOL.dreamshops.model.CartItem;
import com.LOL.dreamshops.model.Product;
import com.LOL.dreamshops.repository.CartItemRepository;
import com.LOL.dreamshops.repository.CartRepository;
import com.LOL.dreamshops.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class CartItemService implements ICartItemService
{

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final IProductService productService;
	private final ICartService cartService;

	@Override
	public void addItemToCart(Long cartId, Long productId, int quantity) 
	{
		
		Cart cart = cartService.getCart(cartId);
		Product product = productService.getProductById(productId);
		CartItem cartItem = cart.getItems()
			.stream()
			.filter(item -> item.getProduct().getId().equals(productId))
			.findFirst().orElse(new CartItem());

		if (cartItem.getId() == null)
		{

			cartItem.setCart(cart);
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cartItem.setUnitPrice(product.getPrice());

		}
		else
		{
		
			cartItem.setQuantity(cartItem.getQuantity());
		
		}

		cartItem.setTotalPrice();
		cart.addItem(cartItem);

		cartItemRepository.save(cartItem);
		cartRepository.save(cart);
		
	}

	@Override
	public void removeItemFromCart(Long cartId, Long productId) 
	{

		Cart cart = cartService.getCart(cartId);
		CartItem itemToRemove = getCartItem(cartId, productId);
		
		cart.removeItem(itemToRemove);

		cartRepository.save(cart);
		
	}

	@Override
	public void updateItemQuantity(Long cartId, Long productId, int quantity) 
	{
		
		Cart cart = cartService.getCart(cartId);

		cart.getItems()
			.stream()
			.filter(item -> item.getProduct().getId().equals(productId))
			.findFirst()
			.ifPresent(item -> 
			{

				item.setQuantity(quantity);
				item.setUnitPrice(item.getProduct().getPrice());
				item.setTotalPrice();

			});

		BigDecimal totalAmount = cart.getItems().stream()
						.map(CartItem::getTotalPrice)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
		cart.setTotalAmount(totalAmount);

		cartRepository.save(cart);
		
	}

	@Override
	public CartItem getCartItem(Long cartId, Long productId)
	{

		Cart cart = cartService.getCart(cartId);

		return cart.getItems()
						.stream().filter(item -> item.getProduct().getId().equals(productId))
						.findFirst().orElseThrow((() -> new ResourceNotFoundException("Item not found")));

	}

}
