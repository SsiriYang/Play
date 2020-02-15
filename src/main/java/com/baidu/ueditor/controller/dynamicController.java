package com.baidu.ueditor.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myplay.mapper.UserMapper;
import com.myplay.model.Dynamic;
import com.myplay.model.User;
import com.myplay.model.dynamicAndUser;
import com.myplay.service.DynamicServiceImpl;


@RestController
@RequestMapping("/dy")
public class dynamicController {
	@Autowired
	private UserMapper usermapper;
	
	@Autowired
	DynamicServiceImpl dynamicServiceImpl;
	@PostMapping("/dynamic")
	public void insert(String content,HttpSession session) {	
		User u = (User)session.getAttribute("user");
	    Dynamic dynamic =	new Dynamic();
	    dynamic.setContent(content);
	    dynamic.setUid(u.getId());
	    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = s.format(new Date());
		dynamic.setCreatetime(strDate);
	    System.out.println(dynamic.getContent());
	    dynamicServiceImpl.insert(dynamic);	
	}
	@GetMapping("/dynamic")
	public List<dynamicAndUser> selectdynamibycuser(HttpSession session){
		User u = (User)session.getAttribute("user");
		Integer id=1;
		if(u!=null){
			id = u.getId();
		}
		return dynamicServiceImpl.selectdynamibycuser(id);
	}
	@GetMapping("dynamicuser")
	public User dynamicByUser(Integer uid) {
		return usermapper.selectByPrimaryKey(uid);
	}
}
