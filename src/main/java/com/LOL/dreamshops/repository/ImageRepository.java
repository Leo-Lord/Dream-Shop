package com.LOL.dreamshops.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LOL.dreamshops.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> 
{

	List<Image> findByProductId(Long id);

}
