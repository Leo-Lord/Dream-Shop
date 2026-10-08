package com.LOL.dreamshops.dto;

import com.LOL.dreamshops.model.Product;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonPropertyOrder(
{

  "itemId",
  "quantity",
  "unitPrice",
  "product"

})
public class CartItemDto
{

  private Long itemId;
  private Integer quantity;
  private BigDecimal unitPrice;
  private ProductDto product;

}
