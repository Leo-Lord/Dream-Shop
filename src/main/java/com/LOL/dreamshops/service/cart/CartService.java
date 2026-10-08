package com.LOL.dreamshops.service.cart;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.LOL.dreamshops.model.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.LOL.dreamshops.exceptions.ResourceNotFoundException;
import com.LOL.dreamshops.model.Cart;
import com.LOL.dreamshops.repository.CartItemRepository;
import com.LOL.dreamshops.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class CartService implements ICartService
{

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final AtomicLong cartIdGenerator = new AtomicLong();

  @Transactional
  @Override
  public void clearCart(Long id)
  {

    Cart cart = getCart(id);

    cartItemRepository.deleteAllByCartId(id);

    cartRepository.delete(cart);

  }

  @Override
  public Cart getCart(Long id)
  {

    Cart cart = cartRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("cart not found"));

    BigDecimal totalAmount = cart.getTotalAmount();
    cart.setTotalAmount(totalAmount);
    return cartRepository.save(cart);

  }

  @Override
  public BigDecimal getTotalPrice(Long id)
  {

    Cart cart = getCart(id);

    return cart.getTotalAmount();

  }

  @Override
  public Cart initializeNewCart(User user)
  {

    return Optional.ofNullable(getCartByUserId(user.getId()))
            .orElseGet(() ->
            {

              Cart cart = new Cart();
              cart.setUser(user);
              return cartRepository.save(cart);

            });

  }

  @Override
  public Cart getCartByUserId(Long userId)
  {

    return cartRepository.findByUserId(userId);

  }

}
