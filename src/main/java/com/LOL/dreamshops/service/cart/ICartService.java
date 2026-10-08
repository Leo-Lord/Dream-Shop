package com.LOL.dreamshops.service.cart;

import java.math.BigDecimal;

import com.LOL.dreamshops.model.Cart;
import com.LOL.dreamshops.model.User;

public interface ICartService
{

	Cart getCart(Long id);
	void clearCart(Long id);
	BigDecimal getTotalPrice(Long id);

  Cart initializeNewCart(User user);

  Cart getCartByUserId(Long userId);
}
