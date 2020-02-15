package com.myplay.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myplay.mapper.UserMapper;
import com.myplay.model.Collection;
import com.myplay.model.Dynamic;
import com.myplay.model.Follow;
import com.myplay.model.MyCollection;
import com.myplay.model.MyDynamicComment;
import com.myplay.model.User;
import com.myplay.model.Video;
import com.myplay.service.IPersonalCenterService;
import com.myplay.service.IVideoService;

@CrossOrigin
@RestController
@RequestMapping("/Personal")
public class PersonalCenterController {
	
	@Autowired
	private IPersonalCenterService iPersonalCenterService  ;
	@Autowired
	private IVideoService iVideoService;
	/**
	 * 根据id获得个人信息
	 * @param session
	 * @return
	 */
	@GetMapping("/getUser")
	public User getUser(HttpSession session){
		User user=(User) session.getAttribute("user");
		return iPersonalCenterService.selectByPrimaryKey(user.getId());
	}
	/**
	 * 更改个人信息
	 * @param files
	 * @param user
	 */
	@RequestMapping("/updateUser")
	public void updateUser(@RequestParam("file") MultipartFile file,User user,HttpSession session){
		User u=(User) session.getAttribute("user");
		user.setId(u.getId());
		if(file!=null){
			File path = new File("D:/upload");
			//判断文件夹是否存在，不存在则新建一个文件夹
			if(!path.exists()){
				path.mkdirs();
			}
			String name = file.getOriginalFilename();
			String type=name.substring(name.lastIndexOf("."));
			String realname=UUID.randomUUID().toString()+type;
			user.setPhotourl("http://localhost/"+realname);
			File newFile=new File(path,realname);
			try {
				file.transferTo(newFile);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			iPersonalCenterService.updateByPrimaryKey(user);
		}
		
	}
	/**
	 * 得到该用户的所有收藏
	 * @param session
	 * @return
	 */
	@GetMapping("/selectMyCollection")
	public List<MyCollection> selectMyCollection(HttpSession session){
		List<MyCollection> myCollections=new ArrayList<MyCollection>();
		User u=(User) session.getAttribute("user");
		//先从收藏表中查到该用户的所有收藏记录
		List<Collection> collections=iVideoService.selectUserCollection(u.getId());
		for (Collection collection : collections) {
			//通过遍历收藏表，根据收藏表的视频id查找所以的视频
			Video video =iVideoService.selectByPrimaryKey(collection.getVid());
			if(video!=null){
			//根据用户id、视频id、作者id查找返回一个自己封装的数组
			List<MyCollection> mcollections= iVideoService.selectMyCollection(video.getId(), video.getUserid(),u.getId());
			for (MyCollection myCollection2 : mcollections) {
				System.out.println(myCollection2.getVid()+myCollection2.getName());
				myCollections.add(myCollection2);
			}
			}
		}
		return myCollections;
	}
	
	
	//删除收藏
	@DeleteMapping("/deleterCollection")
	public void deleterCollection(Integer cid){
		iVideoService.deleteCollectionById(cid);
	}
	/**
	 * 点击作者头像进入作者的个人页面
	 * @param aid   作者id
	 * @return
	 */
	@GetMapping("/selectAuthorById")
	public User selectAuthorById(Integer aid){
		return iPersonalCenterService.selectByPrimaryKey(aid);
	}
	/**
	 * 得到作者的所有视频
	 * @param aid
	 * @return
	 */
	@GetMapping("/selectAuthorVideo")
	public List<Video> selectAuthorVideo(Integer aid){
		return iVideoService.selectVideosByUid(aid);
	}
	
	/**
	 * 得到作者
	 */
	@GetMapping("/getAuthor")
	public User getAuthor(Integer aid){
		return iPersonalCenterService.selectByPrimaryKey(aid);
	}
	
	/**
	 * 得到作者的所有收藏
	 * @param session
	 * @return
	 */
	@GetMapping("/selectAuthorCollection")
	public List<MyCollection> selectAuthorCollection(Integer aid){
		List<MyCollection> myCollections=new ArrayList<MyCollection>();
		//先从收藏表中查到该用户的所有收藏记录
		List<Collection> collections=iVideoService.selectUserCollection(aid);
		for (Collection collection : collections) {
			//通过遍历收藏表，根据收藏表的视频id查找所以的视频
			Video video =iVideoService.selectByPrimaryKey(collection.getVid());
			//根据用户id、视频id、作者id查找返回一个自己封装的数组
			List<MyCollection> mcollections= iVideoService.selectMyCollection(video.getId(), video.getUserid(),aid);
			for (MyCollection myCollection2 : mcollections) {
				System.out.println(myCollection2.getVid()+myCollection2.getName());
				myCollections.add(myCollection2);
			}
		}
		return myCollections;
	}
	
	/**
	 * 得到作者的所有动态
	 */

	@GetMapping("/selectAuthorDynamic")
	public List<Dynamic> selectAuthorDynamic(Integer aid){
		//根据根据作者id查找该作者的所有动态
		return iPersonalCenterService.selectDynamicByUserId(aid);
		
	}
	
	/**
	 * 根据改用户所有的关注 
	 * @param session
	 * @return
	 */
	@GetMapping("/selectMyFollow")
	public List<User> selectMyFollow(HttpSession session){
		List<User> followUsers=new ArrayList<User>();
		User user=(User) session.getAttribute("user");
		List<Follow> folloes=iPersonalCenterService.selectMyFollow(user.getId());//得到了用户的关注表里的记录
		for (Follow follow : folloes) {
			//根据关注着的id得到关注者的记录
			User u=iPersonalCenterService.selectByPrimaryKey(follow.getToUid());
			followUsers.add(u);//每次得到的记录加入到数组中
		}
		return followUsers;
	}
	
	/**
	 * 删除我的关注
	 * @param follow
	 * @param session
	 */
	@GetMapping("/deleterMyFollow")
	public void deleterMyFollow(Follow follow,HttpSession session){
		User user=(User) session.getAttribute("user");
		follow.setFromUid(user.getId());
		iPersonalCenterService.deleteFollow(follow);
	}
	 
	/**
	 * 根据userid查找用户的动态
	 */
	@GetMapping("/selectDynamicByUserId")
	public List<Dynamic> selectDynamicByUserId(HttpSession session){
		User user=(User) session.getAttribute("user");
		return iPersonalCenterService.selectDynamicByUserId(user.getId());
	}
	
	/**
	 * 删除我的动态
	 * @param id
	 */
	@DeleteMapping("/deleteDynamicById")
	public void deleteDynamicById(Integer id){
		iPersonalCenterService.deleteDynamicById(id);
	}
	
	/**
	 * 通过did查找动态
	 */
	@GetMapping("/selectDynamic")
	public Dynamic selectDynamic(Integer did){
		return iPersonalCenterService.selectDynamicById(did);
	}
	
	/**  
	 * 查找我的动态的评论
	 */
	@GetMapping("/selectMyDynamicComment")
	public List<MyDynamicComment>  selectMyDynamicComment(Integer did){	
		return iPersonalCenterService.selectMyDynamicComment(did);
	}
	
	/**
	 * 删除我的动态评论
	 */
	@DeleteMapping("/deleteDynamicComment")
	public void deleteDynamicComment(Integer id){
		iPersonalCenterService.deleteDynamicComment(id);
	}
}
