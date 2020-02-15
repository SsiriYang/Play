package com.myplay.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.myplay.mapper.UserMapper;
import com.myplay.model.User;
import com.myplay.service.IUserService;
import com.myplay.util.Commons;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private UserMapper userMapper;

	@PostMapping("/login")
	public User login(HttpSession session,User user){
		
		User uu = iUserService.login(user);
		session.setAttribute("user", uu);
		System.out.println(uu.getId()+"--"+uu.getName());
		return uu;
	}
	
	@GetMapping("/logout")
	public void logout(HttpSession session){
		session.removeAttribute("user");
	}
	
	@PostMapping("/register")
	public void register(User user,String codes,HttpSession session){
		int code = (Integer)session.getAttribute("codes");
		int codess = Integer.parseInt(codes);
		
		if(code==codess){
			iUserService.insert(user);
		}
	}
	
	@GetMapping("/Refresh")
	public int Refresh(HttpSession session){
		if(session.getAttribute("user")==null){
			return 0;//未登录
		}
		return 1;//已登录
	}
	
	//判断手机号是否存在
	@GetMapping("/phoneExit")
	public Integer phoneExit(User user){
		
		return iUserService.phoneExit(user);	
	}

	@RequestMapping("/sendCode")
	public Integer sendCode(User user,HttpSession session){
		
		String phone  = user.getPhone();
		int pe = phoneExit(user);
		if(pe>=Commons.phone_num){
			return pe;
		}else{

			
			 int codes;//定义变量
	         Random ne=new Random();//实例化一个random的对象ne
	         codes=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
	         session.setAttribute("codes", codes);    
			 DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIwa4gCPcXO4DC", "aJJ9Qd7DBzP4SiXvyleyoxrYeUrKGa");
		        IAcsClient client = new DefaultAcsClient(profile);

		        CommonRequest request = new CommonRequest();
		        request.setMethod(MethodType.POST);
		        request.setDomain("dysmsapi.aliyuncs.com");
		        request.setVersion("2017-05-25");
		        request.setAction("SendSms");
		        request.putQueryParameter("RegionId", "default");
		        request.putQueryParameter("PhoneNumbers", phone);
		        request.putQueryParameter("SignName", "myplay应用");
		        request.putQueryParameter("TemplateCode", "SMS_172860011");
		        request.putQueryParameter("TemplateParam", "{\"code\":\""+codes+"\"}");
		        try {
		            CommonResponse response = client.getCommonResponse(request);
		            System.out.println(response.getData());
		        } catch (ServerException e) {
		            e.printStackTrace();
		        } catch (ClientException e) {
		            e.printStackTrace();
		        }
		}
		
		
	        
	        return pe;
	}
	

		 
		
	 
	    
	 
	
}
