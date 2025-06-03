package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.example.dbmodels.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
