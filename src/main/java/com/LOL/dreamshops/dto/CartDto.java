package com.LOL.dreamshops.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@JsonPropertyOrder(
{

  "cartId",
  "items",
  "totalAmount"

})
public class CartDto
{

  private Long cartId;
  private Set<CartItemDto> items;
  private BigDecimal totalAmount;

}
