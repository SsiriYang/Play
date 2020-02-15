package com.myplay.service;

import java.util.List;

import com.myplay.model.Collection;
import com.myplay.model.Follow;
import com.myplay.model.Mark;
import com.myplay.model.Message;
import com.myplay.model.User;
import com.myplay.model.UserComment;
import com.myplay.model.Video;
import com.myplay.model.VideoAuthor;
import com.myplay.model.VideoComment;

public interface IVideoShowService {

	int insert(VideoComment comment);
	List<UserComment> selectAllComment(Integer vid);
	List<UserComment> selectNewComment(Integer vid);
	List<UserComment> getMyComment(int id,Integer vid);
	Video loadVideo(int id);
	User loadAuthor(Integer id);
	int follow(Follow follow);
	int collection(Collection collection);
	String loadFollowAndCollection(Integer vid, Integer fromUid);
	int deleteFollow(Follow follow);
	int deleteCollection(Collection collection);
	List<VideoAuthor> recommend(Integer cid);
	Float loadRate(Integer vid);
	Float loadMark(Integer id, Integer vid);
	void makeMark(Mark mark );
	int privateLetter(Message message);
	void updateVideoCount(int id);
	void updateVideoCollectionnum(Integer vid);
	int loadFollow(Integer toUid,Integer fromUid);
}
