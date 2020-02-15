package com.myplay.service;

import com.myplay.model.User;

public interface IUserService {

	public User login(User user);
	
	int insert(User record);
	
	int phoneExit(User record);
}
