package com.example.humanresources.core.dataAccess;

import com.example.humanresources.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer>{
	User findByEmail(String email);
	List<User> findByMailVerifyTrue();

}
