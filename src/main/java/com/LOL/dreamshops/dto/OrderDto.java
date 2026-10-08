package com.LOL.dreamshops.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@JsonPropertyOrder(
{

  "id",
  "userId",
  "orderDate",
  "totalAmount",
  "status",
  "items"

})
public class OrderDto
{

  private Long id;
  private Long userId;
  private LocalDate orderDate;
  private BigDecimal totalAmount;
  private String status;
  private List<OrderItemDto> items;

}
