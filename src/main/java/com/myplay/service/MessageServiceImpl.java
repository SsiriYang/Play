package com.myplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myplay.mapper.MessageMapper;
import com.myplay.mapper.SysNoticeMapper;
import com.myplay.model.LetterBox;
import com.myplay.model.SysNotice;

@Service
public class MessageServiceImpl implements IMessageService{
	
	@Autowired
	public MessageMapper messageMapper;

	@Autowired
	public SysNoticeMapper sysNoticeMapper;
	
	@Override
	public List<LetterBox> getInboxs(Integer id) {
		// TODO Auto-generated method stub
		return messageMapper.getInboxs(id);
	}

	
	@Override
	public List<LetterBox> getOutboxs(Integer id) {
		// TODO Auto-generated method stub
		return messageMapper.getOutboxs(id);
	}


	@Override
	public LetterBox getMessageById(Integer id) {
		// TODO Auto-generated method stub
		return messageMapper.getMessageById(id);
	}


	@Override
	public int close(Integer id) {
		// TODO Auto-generated method stub
		return messageMapper.close(id);
	}


	@Override
	public int look(Integer id) {
		// TODO Auto-generated method stub
		return messageMapper.look(id);
	}


	@Override
	public int inboxnolook(Integer id) {
		// TODO Auto-generated method stub
		return messageMapper.inboxnolook( id);
	}


	@Override
	public List<SysNotice> selectAll() {
		// TODO Auto-generated method stub
		return sysNoticeMapper.selectAll();
	}

}
