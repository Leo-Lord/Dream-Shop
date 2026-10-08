package com.LOL.dreamshops.dto;

import java.math.BigDecimal;
import java.util.List;

import com.LOL.dreamshops.model.Category;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@JsonPropertyOrder(
{

	"id",
	"name",
	"brand",
	"price",
	"inventory",
	"description",
	"category",
	"images"

})
public class ProductDto 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String brand;
	private BigDecimal price;
	private int inventory;
	private String description;
	private Category category;
	private List<ImageDto> images;

}
