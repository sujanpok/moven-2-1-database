

Main.java

package com.example.demo.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dataInput.Input;
import com.example.demo.service.LoginService;

import lombok.extern.slf4j.Slf4j;

	@Controller
	@Slf4j
	public class Main {

	    @Autowired
	    private LoginService service;

		
	    @GetMapping("/user")
	    public String hello(Model model) {
	        return "user";
	    }
	    
	    @RequestMapping("/regi")
	    public String defectDetails() {
	        return "regi";
	    }
	    
	    
	    
	    
	    @PostMapping("/logined")
	    public String loginCheck(HttpSession session,Input request,Model model) {
	    	
	    	service.checklogin(request,model);
	    
	    	
	    	return "hello2";
	    	
	    }
	    
	    
	    
	    }


	
	
	

Input.java
package com.example.demo.dataInput;

public class Input {
	private String user;
	private String pwd;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}


UserInfo.java

	package com.example.demo.dataInput;

import lombok.Data;

@Data
public class UserInfo {
	
	private String mail;
	
	private int age;

}

	
	UserMapper.java
  
  package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dataInput.Input;
import com.example.demo.dataInput.UserInfo;




@Mapper
public interface  UserMapper {
	

	int doCheck(Input request);

	
	UserInfo getUserInfo(Input request);
}

  
  
  

service/package/
  serviceImpl/
  LoginServiceImpl.java
  
  package com.example.demo.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.dataInput.Input;
import com.example.demo.dataInput.UserInfo;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;



	@Override
	public String checklogin(Input request,Model model) {
 	int i = userMapper.doCheck(request);
 	String msg;
    	if(i< 1) {
    		msg= "ない";
    	}else {
    		UserInfo userInfo =	userMapper.getUserInfo(request);
    		 model.addAttribute("userinfo",userInfo);
    		msg="OK";
    	}
		
    	 model.addAttribute("msg",msg);
		return msg;
	}
	
}

  
  
  
  
  LoginService.java
  
  
  package com.example.demo.service;

import org.springframework.ui.Model;

import com.example.demo.dataInput.Input;

public interface LoginService {

	String checklogin(Input request, Model model);
}

  
	
	
	
	


