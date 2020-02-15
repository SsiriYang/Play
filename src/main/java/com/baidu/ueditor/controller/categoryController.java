package com.baidu.ueditor.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myplay.model.Category;
import com.myplay.model.Dynamic;
import com.myplay.model.User;
import com.myplay.model.Video;
import com.myplay.service.CategoryServiceImpl;

@RestController
@RequestMapping("/cate")
public class categoryController {
//	GET    用来获取资源，
//	POST  用来新建资源（也可以用于更新资源），
//	PUT    用来更新资源，
//	DELETE  用来删除资源
	@Autowired
	private CategoryServiceImpl  categoryServiceImpl;
	@GetMapping("/cates")
	public List<Category> content() {
		List<Category> cgs = categoryServiceImpl.selectAll();
		return cgs;
	}
	@GetMapping("/videos")
	public List<Video> vodeos(Integer cid) {
		List<Video> videos = categoryServiceImpl.selectVideosByType(cid);		
		return videos;
	}
	@GetMapping("/all")
	public List<Video> selectAllVideo(){
		List<Video> videos = categoryServiceImpl.selectAllVideo();
		return videos;	
	}
}
