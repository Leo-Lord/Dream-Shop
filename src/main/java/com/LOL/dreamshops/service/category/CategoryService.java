package com.LOL.dreamshops.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.LOL.dreamshops.exceptions.AlreadyExistsException;
import com.LOL.dreamshops.exceptions.ResourceNotFoundException;
import com.LOL.dreamshops.model.Category;
import com.LOL.dreamshops.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) 
	{

		return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
			.map(categoryRepository::save)
			.orElseThrow(() -> new AlreadyExistsException("Category with name " + category.getName() + " already exists."));
	}

	@Override
	public void deleteCategoryById(Long id) 
	{

		categoryRepository.findById(id)
			.ifPresentOrElse(categoryRepository::delete, () -> {
				throw new ResourceNotFoundException("Category not found with id: " + id);
			}); 

	}

	@Override
	public List<Category> getAllCategories() 
	{

		return categoryRepository.findAll();

	}

	@Override
	public Category getCategoryById(Long id) 
	{

		return categoryRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

	}

	@Override
	public Category getCategoryByName(String name) 
	{

		return categoryRepository.findByName(name);

	}

	@Override
	public Category updateCategory(Category category, Long id) 
	{

		return Optional.ofNullable(getCategoryById(id))
			.map(oldCategory -> {
				oldCategory.setName(category.getName());
				return categoryRepository.save(oldCategory );
			})
			.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

	}

}
