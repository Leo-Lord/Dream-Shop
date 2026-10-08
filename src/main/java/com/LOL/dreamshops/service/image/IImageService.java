package com.LOL.dreamshops.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.LOL.dreamshops.dto.ImageDto;
import com.LOL.dreamshops.model.Image;

public interface IImageService 
{

	Image getImageById(Long id);

	void deleteImageById(Long id);

	List<ImageDto> saveImages(List<MultipartFile> files, Long productId);

	void updateImage(MultipartFile files, Long imageId);

}
