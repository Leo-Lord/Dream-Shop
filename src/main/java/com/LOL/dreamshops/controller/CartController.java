package com.LOL.dreamshops.controller;

import com.LOL.dreamshops.exceptions.ResourceNotFoundException;
import com.LOL.dreamshops.model.Cart;
import com.LOL.dreamshops.response.ApiResponse;
import com.LOL.dreamshops.service.cart.CartService;
import com.LOL.dreamshops.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/carts")
public class CartController
{

  private final ICartService cartService;

  @GetMapping("/{cartId}/my-cart")
  public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId)
  {

    try
    {

      Cart cart = cartService.getCart(cartId);

      return ResponseEntity.ok(new ApiResponse("Success", cart));

    } catch (ResourceNotFoundException e)
    {

      return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

    }

  }

  @DeleteMapping("/{cartId}/clear")
  public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId)
  {

    try
    {

      cartService.clearCart(cartId);
      return ResponseEntity.ok(new ApiResponse("Clear cart success!", null));

    } catch (ResourceNotFoundException e)
    {

      return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

    }

  }

  @GetMapping("/{cartId}/cart/total-amount")
  public ResponseEntity<ApiResponse> getTotalAmount(@PathVariable Long cartId)
  {

    try
    {

      BigDecimal totalPrice = cartService.getTotalPrice(cartId);
      return ResponseEntity.ok(new ApiResponse("Total Price", totalPrice));

    } catch (ResourceNotFoundException e)
    {

      return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

    }

  }

}
