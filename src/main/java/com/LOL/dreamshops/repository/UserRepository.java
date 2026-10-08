package com.LOL.dreamshops.repository;

import com.LOL.dreamshops.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{

  boolean existsByEmail(String email);

  User findByEmail(String email);
}
