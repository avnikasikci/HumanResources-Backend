package com.example.humanresources.business.abstracts;


import com.example.humanresources.core.entities.User;
import com.example.humanresources.core.utilities.results.DataResult;
import com.example.humanresources.core.utilities.results.Result;
import com.example.humanresources.entities.dtos.UserDto;
import com.example.humanresources.entities.dtos.UserLoginDto;
import com.example.humanresources.entities.dtos.UserLoginReturnDto;

import java.util.List;

public interface UserService {
	Result add(User user);
	Result update(User user);
	DataResult<User> findByEmail(String email);
	DataResult<UserLoginReturnDto> login(UserLoginDto userLoginDto);
	DataResult<List<User>> getAll();
	DataResult<List<UserDto>> getAllDto();
	DataResult<User> getByEmail(String email);
	DataResult<List<User>> getVerifyedUsers();
}
