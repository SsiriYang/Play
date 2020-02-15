package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myplay.mapper.CategoryMapper;
import com.myplay.mapper.GoodsMapper;
import com.myplay.mapper.GoodsTypeMapper;
import com.myplay.mapper.SysNoticeMapper;
import com.myplay.mapper.UserMapper;
import com.myplay.mapper.VideoMapper;
import com.myplay.model.Category;
import com.myplay.model.Goods;
import com.myplay.model.GoodsType;
import com.myplay.model.SysNotice;
import com.myplay.model.User;
import com.myplay.model.Video;

@Service
public class AdminService {
	@Autowired
	private VideoMapper videomapper;
	@Autowired
	private CategoryMapper categorymapper;
	@Autowired
	private GoodsMapper goodsmapper;
	@Autowired
	private GoodsTypeMapper goodstypemapper;
	@Autowired
	private UserMapper usermapper;
	@Autowired
	private SysNoticeMapper sysnoticemapper ;
	
	public List<Video> getVideoList() {
		return videomapper.selectAllDesc();
	}

	public int pass(Integer videoid) {
		return videomapper.updateforpass(videoid);
	}

	public int notpass(Integer videoid) {
		return videomapper.updatefornotpass(videoid);
	}
	//模糊查询
	public List<Video> searchByWord(String searchword) {
		return videomapper.searchByWord(searchword);
	}

	public List<Category> getvideocategory() {
		return categorymapper.selectAll();
	}
	//类型查询
	public List<Video> searchByCategory(Integer categoryid) {
		return videomapper.searchByCategory(categoryid);
	}
	
	
	//商品管理*********************************************************************
	//获取商品并展示
	public List<Goods> getShopList() {
		return goodsmapper.selectAll();
	}
	//获取视频类型
	public List<GoodsType> getGoodType() {
		return goodstypemapper.selectAll();
	}

	public List<Goods> searchByShopCategory(Integer categoryid) {
		return goodsmapper.searchByShopCategory(categoryid);
	}

	public List<Goods> searchShopByWord(String searchword) {
		return goodsmapper.searchShopByWord(searchword);
	}
	//编辑商品
	public int editShop(Goods goods) {
		return goodsmapper.updateByPrimaryKey(goods);
	}
	//删除商品
	public int delShop(Integer shopid) {
		return goodsmapper.deleteByPrimaryKey(shopid);
	}

	public int addShop(Goods goods) {
		return goodsmapper.insert(goods);
	}

	public Goods getGoodById(Integer goodid) {
		return goodsmapper.selectByPrimaryKey(goodid);
	}

	public int addGood(Goods goods) {
		return goodsmapper.insert(goods);
	}

	public int addCategory(Category record) {
		return categorymapper.insert(record);
	}
	//删除视频类型
	public int delCategory(Integer categoryid) {
		return categorymapper.deleteByPrimaryKey(categoryid);
	}
	
	public int addGoodType(GoodsType type) {
		return goodstypemapper.insert(type);
	}

	public int delGoodType(Integer typeid) {
		return goodstypemapper.deleteByPrimaryKey(typeid);
	}
	//获取用户列表
	public List<User> getUserList() {
		return usermapper.selectAll();
	}

	public int addSysNotice(SysNotice sysnotice) {
		return sysnoticemapper.insert(sysnotice);
	}
	
}
