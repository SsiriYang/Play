package com.myplay.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myplay.model.Goods;
import com.myplay.service.IGoodsService;
import com.myplay.util.Commons;

@CrossOrigin
@RestController
@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private IGoodsService goods;

	@GetMapping("/select")
	public PageInfo selectAll(@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,@RequestParam(value="typeId") Integer typeId) {
		System.out.println("pageNum:"+pageNum+"typeId:"+typeId);
		PageHelper.startPage(pageNum,Commons.goods_num);
		List<Goods> allGoods = goods.selectAllGoodsByType(typeId);
		PageInfo pages = new PageInfo<>(allGoods);
		return pages;
		
	}

	@GetMapping("/selectById")
	public Goods selectById(Integer id) {
		System.out.println("qqq");
		return goods.selectById(id);
	}

	@GetMapping("/addcart")
	public List addCart(HttpSession session, Integer id) {
		Goods item = goods.selectById(id);
		Map<Integer, Goods> cart = (Map) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<Integer, Goods>();
			cart.put(id, item);
			session.setAttribute("cart", cart);
		} else {
			if (cart.containsKey(id)) {
				Goods it = cart.get(id);
				it.setNum(it.getNum() + 1);
			} else {
				cart.put(id, item);
			}
		}
		List list = new ArrayList();
		Set<Integer> set = cart.keySet();
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			list.add(cart.get(it.next()));
		}
		System.out.println(cart);
		return list;
	}

}
