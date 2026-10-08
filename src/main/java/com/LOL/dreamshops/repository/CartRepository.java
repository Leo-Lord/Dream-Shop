package com.LOL.dreamshops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LOL.dreamshops.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>
{

  Cart findByUserId(Long userId);

}
