package com.baidu.ueditor.controller;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myplay.mapper.MarkMapper;
import com.myplay.model.User;
import com.myplay.model.Video;
import com.myplay.service.CategoryServiceImpl;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/uesr")
public class UserCF {

	final static int nei_num = 3;// 临近的用户个数
	final static int rec_num = 3;// 推荐物品的最大个数

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	@Autowired
	private MarkMapper markMapper;

	@GetMapping("/recom")
	public List recom(HttpSession session) throws Exception, TasteException {
		User u = (User) session.getAttribute("user");
		delnull();
		List<Video> videos = usercf(u);
		System.out.println("推荐被调用！");
		return videos;
	}

	public int delnull() throws IOException {
		String f = "D:\\upload\\a.csv";
		File file = new File(f);
		System.gc();
		if (file.delete()) {
			markMapper.outPutfile();
			System.out.println("成功更新数据");
			return 1;
		}
		return 0;
	}
	public List<Video> usercf(User u) throws Exception, TasteException {
		String fa = "D:\\upload\\a.csv";
		File filea = new File(fa);
		DataModel model = new FileDataModel(filea);// 数据模型
		UserSimilarity user = new EuclideanDistanceSimilarity(model);// 用户相识度算法
		NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(nei_num, user, model);
		// 用户近邻算法
		Recommender r = new GenericUserBasedRecommender(model, neighbor, user);// 用户推荐算法
		int iter;
		if (u != null) {
			iter = u.getId();
		} else {
			iter = 2;
		}
		List<RecommendedItem> list = r.recommend(iter, rec_num);
		List<Video> videos = new ArrayList();
		// System.out.printf("uid:%s", iter);
		for (RecommendedItem ritem : list) {
			int MovieId = (int) ritem.getItemID();
			Video v = categoryServiceImpl.selectByPrimaryKey(MovieId);
			videos.add(v);
		}
		if (null == list || list.isEmpty()) {
			videos = categoryServiceImpl.selectByvivwcount();
			return videos;

		} else {
			return videos;
		}

	}
}