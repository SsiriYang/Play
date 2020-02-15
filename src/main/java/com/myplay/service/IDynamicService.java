package com.myplay.service;

import java.util.List;

import com.myplay.model.Dynamic;
import com.myplay.model.dynamicAndUser;

public interface IDynamicService {

	void insert(Dynamic dynamic);

	List<dynamicAndUser> selectdynamibycuser(Integer id);

}
