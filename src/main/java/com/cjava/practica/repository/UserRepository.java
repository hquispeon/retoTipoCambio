package com.cjava.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjava.practica.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
