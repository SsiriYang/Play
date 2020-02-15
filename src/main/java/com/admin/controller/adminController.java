package com.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.admin.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myplay.model.AdminData;
import com.myplay.model.Category;
import com.myplay.model.Goods;
import com.myplay.model.GoodsType;
import com.myplay.model.SysNotice;
import com.myplay.model.User;
import com.myplay.model.Video;

@RequestMapping("/admin")
@RestController
public class adminController {
    @Autowired
    private AdminService adminservice;
    
 
    //  视频管理*********************************************************************
    @ResponseBody
    @RequestMapping("/getvideolist")
    public AdminData getVideoList(Model model,@RequestParam(defaultValue="1",value="currentpage",required=false) Integer  currentpage){
        PageHelper.startPage(currentpage, 3);
        System.out.println(currentpage);
        List<Video> videoList = adminservice.getVideoList();
        PageInfo<Video> pageinfo = new PageInfo<Video>(videoList);
//        PageInfo pageinfo = new PageInfo(videoList, 3);
        return AdminData.success().add("pageinfo", pageinfo);
    }
    @ResponseBody
    @RequestMapping("/pass")
    public int pass(Integer videoid){
        return adminservice.pass(videoid);
    }
    
    @ResponseBody
    @RequestMapping("/notpass")
    public int notpass(Integer videoid){
        return adminservice.notpass(videoid);
    }
//    模糊查询
    @ResponseBody
    @RequestMapping("/search")
    public AdminData searchByWord(String searchword,@RequestParam(defaultValue="1",value="currentpage",required=false) Integer  currentpage){
        System.out.println(currentpage+searchword);
        PageHelper.startPage(currentpage, 3);
        List<Video> videoList = adminservice.searchByWord(searchword);
        PageInfo<Video> pageinfo = new PageInfo<Video>(videoList);
        return AdminData.success().add("pageinfo", pageinfo);
    }
    //获取用户列表
    @ResponseBody
    @RequestMapping("/getuserlist")
    public List<User> getUserList(){
        return adminservice.getUserList();
    }
    //获取视频类型
    @ResponseBody
    @RequestMapping("/getcategory")
    public List<Category> getcategory(){
        return adminservice.getvideocategory();
    }
    //添加视频类型
    @ResponseBody
    @RequestMapping("/addcategory")
    public int addCategory(String name){
    	
    	Category cate = new Category(null, name);
        return adminservice.addCategory(cate);
    }
    //删除视频类型
    @ResponseBody
    @RequestMapping("/delcategory")
    public int delCategory(Integer categoryid){
        return adminservice.delCategory(categoryid);
    }
    //类型查询
    @ResponseBody
    @RequestMapping("/searchbycategory")
    public AdminData searchByCategory(Integer categoryid,@RequestParam(defaultValue="1",value="currentpage",required=false) Integer  currentpage){
        PageHelper.startPage(currentpage, 3);
        List<Video> videoList = adminservice.searchByCategory(categoryid);
        PageInfo<Video> pageinfo = new PageInfo<Video>(videoList);
        return AdminData.success().add("pageinfo", pageinfo);
    }
    
    
    //  商品管理*********************************************************************
    @ResponseBody
    @RequestMapping("/getshoplist")
    public AdminData getShopList(@RequestParam(defaultValue="1",value="currentpage",required=false) Integer  currentpage){
        PageHelper.startPage(currentpage, 3);
        System.out.println(currentpage);
        List<Goods> videoList = adminservice.getShopList();
        PageInfo<Goods> pageinfo = new PageInfo<Goods>(videoList);
        return AdminData.success().add("pageinfo", pageinfo);
    }
    
    //获取商品类型
    @ResponseBody
    @RequestMapping("/getgoodtype")
    public List<GoodsType> getGoodType(){
        return adminservice.getGoodType();
    }
    //添加商品类型
    @ResponseBody
    @RequestMapping("/addgoodtype")
    public int addGoodType(String typename){
    	GoodsType goodsType = new GoodsType(null,typename);
        return adminservice.addGoodType(goodsType);
    }
    //删除商品类型
    @ResponseBody
    @RequestMapping("/delgoodtype")
    public int delGoodType(Integer typeid){
        return adminservice.delGoodType(typeid);
    }
    //商品类型查询
    @ResponseBody
    @RequestMapping("/searchbyshopcategory")
    public AdminData searchByShopCategory(Integer categoryid,@RequestParam(defaultValue="1",value="currentpage",required=false) Integer  currentpage){
        PageHelper.startPage(currentpage, 3);
        List<Goods> goodList = adminservice.searchByShopCategory(categoryid);
        PageInfo<Goods> pageinfo = new PageInfo<Goods>(goodList);
        return AdminData.success().add("pageinfo", pageinfo);
    }
    //模糊查询
    @ResponseBody
    @RequestMapping("/searchshop")
    public AdminData searchShopByWord(String searchword,@RequestParam(defaultValue="1",value="currentpage",required=false) Integer  currentpage){
        PageHelper.startPage(currentpage, 3);
        List<Goods> goodList = adminservice.searchShopByWord(searchword);
        PageInfo<Goods> pageinfo = new PageInfo<Goods>(goodList);
        return AdminData.success().add("pageinfo", pageinfo);
    }
    //编辑商品
    @ResponseBody
    @RequestMapping("/editshop")
    public int editShop(@RequestParam(value = "file", required = false) MultipartFile file,Goods goods){
    	try {
        	String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        	File newfile = new File("D:/upload/"+uuid+file.getOriginalFilename());
        	file.transferTo(newfile);
        	//保存数据
        	goods.setNum(1);
        	goods.setPicture(file.getOriginalFilename());
            int i = adminservice.editShop(goods);
            if(i>0){
            	return 1;
            }
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        return 0;
    }
    //删除商品
    @ResponseBody
    @RequestMapping("/delgoodbyid")
    public int delShop(String goodids){
    	if(goodids.contains("-")){
    		String[] goodidlist = goodids.split("-");
    		for(String goodid:goodidlist){
    			adminservice.delShop(Integer.parseInt(goodid));
    		}
    	}else{
    		Integer goodid = Integer.parseInt(goodids);
    		adminservice.delShop(goodid);
    	}
    		
        return 1;
    }
    //添加商品
    @ResponseBody
    @RequestMapping("/addshop")
    public int addShop(@RequestParam(value = "file", required = false) MultipartFile file,Goods goods){
    	System.out.println("Controller 。。。");
    	System.out.println(goods.getName()+"  "+goods.getPrice()+"  "+goods.getTypeId()+"  "+goods.getIntroduce()+"  "+goods.getPicture());
    	System.out.println(file.getOriginalFilename());
    	
    	//上传图片
    	try {
        	String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        	File newfile = new File("D:/upload/"+uuid+file.getOriginalFilename());
        	file.transferTo(newfile);
        	//保存数据
        	goods.setNum(1);
        	goods.setPicture("http://localhost/"+file.getOriginalFilename());
            int i = adminservice.addGood(goods);
            if(i>0){
            	return 1;
            }
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        return 0;
    }
    //通过ID查商品
    @ResponseBody
    @RequestMapping("/getgoodbyid")
    public Goods getGoodById(Integer goodid){
        return adminservice.getGoodById(goodid);
    }
    
    //图片上传
    @ResponseBody
    @RequestMapping("/uploadphoto")
    public String upload(MultipartFile file){
        return "";
        
    }
    //管理员登录
    @ResponseBody
    @RequestMapping("/adminlogin")
    public int adminLogin(String admin_phone,String admin_password){
    	if(admin_phone!=null && admin_password!=null && admin_phone.trim()!="" && admin_password.trim()!=""){
    		return 1;
    	}else{
    		return 0;
    	}
    }
    //管理员登录
    @ResponseBody
    @RequestMapping("/addsysnotice")
    public int addSysNotice(SysNotice sysnotice){
    	System.out.println(sysnotice.getTitle()+sysnotice.getCreateTime()+sysnotice.getContent());
    	int i = adminservice.addSysNotice(sysnotice);
    	return i;
    	
    }
}