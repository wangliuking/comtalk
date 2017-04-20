package com.comtalk.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.comtalk.common.WebConstant;
import com.comtalk.dao.UserDao;
import com.comtalk.dto.User;

/**
 * 登录处理类
 */
@Controller
@RequestMapping("/customer")
public class LoginController {
	
	/**
	 * ajax登录
	 * @param userId
	 * @param password
	 * @param vcode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/loginAjax")
	public Map<String,Object> login(String userName,String password){
			Map<String,Object> map = new HashMap<>();
			//获取ServletAPI
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
			//创建dao对象
			UserDao userDao = new UserDao();
			User u = new User();
			u.setUserName(userName);
			u.setPassword(password);
			//查询登录用户是否存在
			User user = userDao.getUserByUserIdAndByPassword(u);			
			if(user != null){
				//存在将用户添加到session中方便后面使用
				request.getSession().setAttribute(WebConstant.SESSION_USER, user);
				if(user.getPriority() == 1){
					userDao.updateLogin(user.getUserId());
					map.put("tip", "登录成功");
					map.put("status", 4);
				}else if(user.getPriority() == 2){
					userDao.updateLogin(user.getUserId());
					map.put("tip", "超级管理员登录成功");
					map.put("status", 0);
				}
			}else{
				map.put("tip", "用户名或密码不正确！");
				map.put("status", 3);
			}
			
		/*
		 * 	登录成功 = 0
			验证码不正确 = 1
			账号或密码不能为空 = 2
			账号或密码格式不正确 = 3
			用户还未激活 = 4
		 */
		return map;
	}
	/**
	 * 用户注销处理方法
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		User u=(User) session.getAttribute(WebConstant.SESSION_USER);
		UserDao userDao = new UserDao();
		userDao.updateLogout(u.getUserId());
		//用户名注销（退出）
		session.invalidate();
		return "index";
	}
}
