package com.LOL.dreamshops.dto;

import com.LOL.dreamshops.model.Cart;
import com.LOL.dreamshops.model.Order;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder(
{

  "id",
  "firstName",
  "lastName",
  "email",
  "orders",
  "cart"

})
public class UserDto
{

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private List<OrderDto> orders;
  private CartDto cart;

}
