package com.baidu.ueditor.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myplay.mapper.DynamicCommentMapper;
import com.myplay.model.Category;
import com.myplay.model.DyCommentBYUser;
import com.myplay.model.DynamicComment;
import com.myplay.model.MyDynamicComment;
import com.myplay.model.User;

@RestController
@RequestMapping("/comm")
public class commController {
	
	@Autowired
	private DynamicCommentMapper dynamicCommentMapper;
	@GetMapping("/comments")
	public List<DyCommentBYUser> getComments(Integer did) {
		List<DyCommentBYUser> dcus = dynamicCommentMapper.selectDyCommentAndUser(did);
		return dcus;
	}
	@PostMapping("comment")
	public void insertComment(HttpSession session,Integer did,String content){
		User u = (User)session.getAttribute("user");
		DynamicComment dycomm = new DynamicComment();
		dycomm.setDid(did);
		dycomm.setContent(content);
		dycomm.setFromUid(u.getId());
		dynamicCommentMapper.insert(dycomm);
	}
}
