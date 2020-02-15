package com.myplay.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myplay.model.Collection;
import com.myplay.model.Follow;
import com.myplay.model.Mark;
import com.myplay.model.Message;
import com.myplay.model.User;
import com.myplay.model.UserComment;
import com.myplay.model.Video;
import com.myplay.model.VideoAuthor;
import com.myplay.model.VideoComment;
import com.myplay.service.IVideoShowService;


@RestController
public class VideoShowController {
	private static String SPLIT = ":";
	private static String LIKE = "LIKE";
    private static String DISLIKE = "DISLIKE";
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
	@Autowired
	private IVideoShowService service;
	/**
	 * 发送评论
	 * @param comment
	 * @param session
	 * @return
	 */
	@GetMapping("/sendcomment")
	public String sendComment(VideoComment comment,HttpSession session){
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = s.format(new Date());
		User user = (User) session.getAttribute("user");
		comment.setUid(user.getId());
		comment.setCreattime(strDate);
		int flag = service.insert(comment);
		if(flag!=0){
			return "评论成功";
		}else{
			return "评论失败";
		}
	}
	/**
	 * 获取所有用户对该视频的评论
	 * @return
	 */
	@GetMapping("/allcomment")
	public List<UserComment> getAllComments(Integer vid){
		return service.selectAllComment(vid);
	}
	/**
	 * 获取该视频的最新评论
	 * @return
	 */
	@GetMapping("/getNewComment")
	public List<UserComment> getNewComment(Integer vid){
		return service.selectNewComment(vid);
	}
	/**
	 * 获取我对该视频的所有评论
	 * @param session
	 * @return
	 */
	@GetMapping("/getMyComment")
	public List<UserComment> getMyComment(HttpSession session,Integer vid){
		User user =(User) session.getAttribute("user");
		return service.getMyComment(user.getId(),vid);
	}
	/**
	 * 加载id对应的视频
	 * @param id
	 * @return
	 */
	@PostMapping("/loadVideo")
	public Video loadVideo(int id){
		service.updateVideoCount(id);
		return service.loadVideo(id);
	}
	/**
	 * 加载作者信息
	 * @param id
	 * @return
	 */
	@PostMapping("/loadAuthor")
	public User loadAuthor(Integer id){
		return service.loadAuthor(id);
	}
	/**
	 * 关注某人
	 * @param toUid
	 * @param session
	 * @return
	 */
	@GetMapping("/follow")
	public String follow(Integer toUid,HttpSession session){
		User user = (User) session.getAttribute("user");
		Follow follow = new Follow();
		follow.setFromUid(user.getId());
		follow.setToUid(toUid);
		if(service.follow(follow)!=0){
			return "关注成功";
		}else {
			return "您已关注，请勿重新登录";
		}
		
	}
	/**
	 * 收藏某视频
	 * @param collection
	 * @param session
	 * @return
	 */
	@PostMapping("/collection")
	public String collection(Collection collection ,HttpSession session){
		User user = (User) session.getAttribute("user");
		collection.setUid(user.getId());//设置收藏人的id
		if(service.collection(collection)!=0){
			service.updateVideoCollectionnum(collection.getVid());
			return "收藏成功";
		}else {
			return "收藏失败，请重试！";
		}
	}
	/**
	 * 加载关注和收藏的信息
	 * @param vid
	 * @param session
	 * @return
	 */
	@PostMapping("/loadFollowAndCollection")
	public String loadFollowAndCollection(Integer vid,HttpSession session){
		User user = (User) session.getAttribute("user");
		return service.loadFollowAndCollection(vid,user.getId());
	}
	/**
	 * 删除关注某作者
	 * @param follow
	 * @param session
	 * @return
	 */
	@DeleteMapping("/follow")
	public String deleteFollow(Follow follow,HttpSession session){
		User user = (User) session.getAttribute("user");
		follow.setFromUid(user.getId());
		service.deleteFollow(follow);
		return "已取消关注";
	}
	/**
	 * 删除收藏的视频
	 * @param collection
	 * @param session
	 * @return
	 */
	@DeleteMapping("/collection")
	public String deleteCollection(Collection collection,HttpSession session){
		User user = (User) session.getAttribute("user");
		collection.setUid(user.getId());
		System.out.println(collection.getVid());
		service.deleteCollection(collection);
		return "已取消收藏";
	}
	/**
	 * 下载视频
	 * @param response
	 * @param fileName
	 */
	@GetMapping("/downloadVideo")
	public void downloadVideo(HttpServletResponse response,String fileName){
		try {
			String name=fileName.substring(fileName.lastIndexOf("/")+1);//获取文件真实名称
			System.out.println(name);
			String path = "D:/upload/"+name;
			File file = new File(path);
			if(!file.exists()){
				response.sendError(404, "您要下载的文件不存在");
				return;
			}
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;filename="+name);
			ServletOutputStream out = response.getOutputStream();
			byte [] buff = new byte[1024];
			int len = -1;
			while((len= bis.read(buff))!=-1){
				out.write(buff,0,len);
			}
			bis.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取该视频下对应类别的所有视频。除去自己
	 * @param cid
	 * @return
	 */
	@PostMapping("/recommend")
	public List<VideoAuthor> recommend(Integer cid){
		return service.recommend(cid);
	}
	/**
	 * 点赞，点赞同时去除点踩的set
	 * @param vid
	 * @param session
	 */
	@PostMapping("/thumbsup")
	public Long thumbsUp(Integer vid,HttpSession session){
		User user = (User)session.getAttribute("user");
		redisTemplate.opsForSet().add(LIKE+SPLIT+vid, String.valueOf(user.getId()));
		redisTemplate.opsForSet().remove(DISLIKE+SPLIT+vid, String.valueOf(user.getId()));
		return redisTemplate.opsForSet().size(LIKE+SPLIT+vid);
	}
	/**
	 * 点踩，同时去除点赞的set
	 * @param vid
	 * @param session
	 */
	@PostMapping("/thumbsdown")
	public Long thumbsDown(Integer vid,HttpSession session){
		User user = (User)session.getAttribute("user");
		redisTemplate.opsForSet().add(DISLIKE+SPLIT+vid, String.valueOf(user.getId()));
		redisTemplate.opsForSet().remove(LIKE+SPLIT+vid, String.valueOf(user.getId()));
		return redisTemplate.opsForSet().size(DISLIKE+SPLIT+vid);
	}
	/**
	 * 加载点赞数
	 * @param vid
	 * @return
	 */
	@PostMapping("/loadGood")
	public Long loadGood(Integer vid){
		return redisTemplate.opsForSet().size(LIKE+SPLIT+vid);
	}
	/**
	 * 加载点踩数
	 * @param vid
	 * @return
	 */
	@PostMapping("/loadBad")
	public Long loadBad(Integer vid){
		return redisTemplate.opsForSet().size(DISLIKE+SPLIT+vid);
	}
	/**
	 * 加载平均分
	 * @param vid
	 * @return
	 */
	@GetMapping("/loadRate")
	public float loadRate(Integer vid){
		return service.loadRate(vid);
	}
	/**
	 * 加载打分
	 * @param session
	 * @return
	 */
	@GetMapping("/loadMark")
	public float loadMark(HttpSession session,Integer vid){
		User user = (User) session.getAttribute("user");
		return service.loadMark(user.getId(),vid);
	}
	/**
	 * 打分
	 * @param session
	 * @param mark
	 * @return
	 */
	@GetMapping("/makeMark")
	public String makeMark(HttpSession session,Mark mark){
		User user = (User) session.getAttribute("user");
		mark.setUid(user.getId());
		if(service.loadMark(user.getId(),mark.getVid())!=null){
			return "您已评分过！请勿重新评分";
		}else {
			service.makeMark(mark);
			return "打分成功！";
		}
	}
	/**
	 * 私信
	 * @param message
	 * @param session
	 * @return
	 */
	@GetMapping("/privateLetter")
	public String privateLetter(Message message,HttpSession session){
		User user = (User) session.getAttribute("user");
		message.setFromUid(user.getId());
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = s.format(new Date());
		message.setCreatedate(strDate);
		if(service.privateLetter(message)!=0){
			return "私信成功！";
		}
		return "发送失败，请重新发送！";
	}
	/**
	 * 在别人主页加载是否关注了该作者
	 * @param toUid
	 * @param session
	 * @return
	 */
	@GetMapping("/loadFollow")
	public String loadFollow(Integer toUid,HttpSession session){
		User user = (User)session.getAttribute("user");
		if(service.loadFollow(toUid,user.getId())!=0){
			return "已关注";
		}
		return "关注";
	}
	
	@PostMapping("/loadMyGood")
	public String loadMyGood(Integer vid,HttpSession session){
		User user = (User)session.getAttribute("user");
		if(user!=null){
			Boolean flag = redisTemplate.opsForSet().isMember(LIKE+SPLIT+vid, user.getId().toString());
			if(flag){
				return "存在";
			}
		}
		return "不存在";
	}
	
	@PostMapping("/loadMyBad")
	public String loadMyBad(Integer vid,HttpSession session){
		User user = (User)session.getAttribute("user");
		if(user!=null){
			Boolean flag = redisTemplate.opsForSet().isMember(DISLIKE+SPLIT+vid, user.getId().toString());
			if(flag){
				return "存在";
			}
		}
		return "不存在";
	}
}
