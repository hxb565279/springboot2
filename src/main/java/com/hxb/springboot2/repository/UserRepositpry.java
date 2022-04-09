package com.hxb.springboot2.repository;

import com.hxb.springboot2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositpry extends JpaRepository<User,Integer> {
}
