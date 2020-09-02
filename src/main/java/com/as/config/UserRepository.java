package com.as.config;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByUsername(String username);

}
