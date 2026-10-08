package com.LOL.dreamshops.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder(
{

	"id",
	"fileName",
	"downloadUrl"

})
public class ImageDto 
{

	private Long id;
	private String fileName;
	private String downloadUrl;

}
