package com.myplay.service;

import java.util.List;

import com.myplay.model.LetterBox;
import com.myplay.model.Message;
import com.myplay.model.SysNotice;

public interface IMessageService {

	//收件箱
	List<LetterBox> getInboxs(Integer id);
	//发件箱
	List<LetterBox> getOutboxs(Integer id);
	//获取信息详情
	LetterBox getMessageById(Integer id);
	//删除信息
	int close(Integer id);
	//已读
	int look(Integer id);
	//未读信息
	int inboxnolook(Integer id);
	//系统通知
	List<SysNotice> selectAll();
}
