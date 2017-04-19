package com.comtalk.admin;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comtalk.common.WebConstant;
import com.comtalk.dao.UserDao;
import com.comtalk.dto.User;

import javax.servlet.jsp.JspWriter;
/**
 * 后台请求处理类
 */
@Controller
@RequestMapping("/admin")
public class AdminAction {
	
	@RequestMapping(value="/mistake")
	public String main(){
		return "main";
	}
	/**
	 * 管理员注销处理方法
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
		return "login";
	}
	
}
