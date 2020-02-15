package com.myplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myplay.mapper.CollectionMapper;
import com.myplay.mapper.FollowMapper;
import com.myplay.mapper.MarkMapper;
import com.myplay.mapper.MessageMapper;
import com.myplay.mapper.UserMapper;
import com.myplay.mapper.VideoCommentMapper;
import com.myplay.mapper.VideoMapper;
import com.myplay.model.Collection;
import com.myplay.model.Follow;
import com.myplay.model.Mark;
import com.myplay.model.Message;
import com.myplay.model.User;
import com.myplay.model.UserComment;
import com.myplay.model.Video;
import com.myplay.model.VideoAuthor;
import com.myplay.model.VideoComment;

@Service
public class VideoShowServiceImpl implements IVideoShowService{

	@Autowired
	private VideoCommentMapper videoCommentMapper;
	
	@Autowired
	private VideoMapper videomapper;
	
	@Autowired
	private UserMapper usermapper;
	
	@Autowired
	private FollowMapper followmapper;
	
	@Autowired
	private CollectionMapper collectionMapper;
	
	@Autowired
	private MarkMapper markMapper;
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Override
	public int insert(VideoComment comment) {
		return videoCommentMapper.insert(comment);
	}

	@Override
	public List<UserComment> selectAllComment(Integer vid) {
		return videoCommentMapper.selectAllCommentsByVid(vid);
	}

	@Override
	public List<UserComment> selectNewComment(Integer vid) {
		return videoCommentMapper.selectNewComment(vid);
	}

	@Override
	public List<UserComment> getMyComment(int id,Integer vid) {
		return videoCommentMapper.getMyComment(id,vid);
	}

	@Override
	public Video loadVideo(int id) {
		return videomapper.selectByPrimaryKey(id);
	}

	@Override
	public User loadAuthor(Integer id) {
		return usermapper.selectByPrimaryKey(id);
	}

	@Override
	public int follow(Follow follow) {
		return followmapper.insert(follow);
	}

	@Override
	public int collection(Collection collection) {
		return collectionMapper.insert(collection);
	}

	@Override
	public String loadFollowAndCollection(Integer vid, Integer fromUid) {
		Video video =videomapper.selectByPrimaryKey(vid);
		Integer toUid=video.getUserid();
		if (followmapper.loadFollow(toUid,fromUid)!=null) {
			if (collectionMapper.loadCollection(vid,fromUid)!=null) {
				return "加载关注收藏成功";
			}else {
				return "加载关注成功";
			}
		}else {
			if (collectionMapper.loadCollection(vid,fromUid)!=null) {
				return "加载收藏成功";
			}else {
				return "加载失败";
			}
		}
	}

	@Override
	public int deleteFollow(Follow follow) {
		return followmapper.deleteFollow(follow);
	}

	@Override
	public int deleteCollection(Collection collection) {
		return collectionMapper.deleteCollection(collection);
	}

	@Override
	public List<VideoAuthor> recommend(Integer cid) {
		return videomapper.recommend(cid);
	}

	@Override
	public Float loadRate(Integer vid) {
		return markMapper.loadRate(vid);
	}

	@Override
	public Float loadMark(Integer id,Integer vid) {
		return markMapper.loadMark(id,vid);
	}

	@Override
	public void makeMark(Mark mark) {
		markMapper.insert(mark);
	}

	@Override
	public int privateLetter(Message message) {
		return messageMapper.insert(message);
	}

	@Override
	public void updateVideoCount(int id) {
		videomapper.updateVideoCount(id);
	}

	@Override
	public void updateVideoCollectionnum(Integer vid) {
		videomapper.updateVideoCollectionnum(vid);
	}

	@Override
	public int loadFollow(Integer toUid,Integer fromUid) {
		return followmapper.loadFollow(toUid, fromUid);
	}

}
