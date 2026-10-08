package com.LOL.dreamshops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LOL.dreamshops.model.CartItem;



public interface CartItemRepository extends JpaRepository<CartItem, Long>
{

  public void deleteAllByCartId(Long id);

}
