package com.comtalk.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comtalk.common.PageModel;
import com.comtalk.common.TempModel;
import com.comtalk.common.WebConstant;
import com.comtalk.dao.UserDao;
import com.comtalk.dto.User;

@Controller
@RequestMapping("/admin/user")
public class UsersAction {
	UserDao userDao = null;
	
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	/**
	 * 分页查询用户
	 * @param user
	 * @param data
	 * @return
	 */
	@RequestMapping("/userList")
	public String getOrderList(User user,@RequestParam(name="pageIndex",defaultValue="0")int pageIndex,Model data){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("user",user);
		userDao = new UserDao();
		int recordCount = userDao.count(params);
		if(recordCount != 0){
			//设置总共有多个条记录（方便进行分页计算）
			pageModel.setRecordCount(recordCount);
			//设置当前页
			pageModel.setPageIndex(pageIndex);
			
			params.put("pageModel", pageModel);
			List<User> userList = userDao.getPageUser(params);
			
			data.addAttribute("pageModel", pageModel);
			data.addAttribute("userList", userList);
			
		}
		return "user/userList";
	}
	
	/**
	 * 删除操作
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(String userId){
		userDao = new UserDao();
		userDao.deleteUser(userId);
		return "forward:/admin/user/userLists";
	}
	/**
	 * 删除后页面跳转
	 * @param user
	 * @param pageIndex
	 * @param data
	 * @return
	 */
	
	@RequestMapping("/userLists")
	public String getOrderLists(User user,@RequestParam(name="pageIndex",defaultValue="0")int pageIndex,Model data){
		user.setUserId(null);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("user",user);
		userDao = new UserDao();
		int recordCount = userDao.count(params);
		if(recordCount != 0){
			//设置总共有多个条记录（方便进行分页计算）
			pageModel.setRecordCount(recordCount);
			//设置当前页
			pageModel.setPageIndex(pageIndex);
			
			params.put("pageModel", pageModel);
			List<User> userList = userDao.getPageUser(params);
			
			data.addAttribute("pageModel", pageModel);
			data.addAttribute("userList", userList);
			
		}
		return "user/userList";
	}
	
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping("/showUpdate")
	public String showUpdate(String userId,Model data){
		userDao = new UserDao();
		User user = userDao.getUserById(userId);
		data.addAttribute("user", user);
		return "user/updateUser";
	}
	/**
	 * 用户更新
	 * @param user
	 * @return
	 */
	@RequestMapping("/update")
	public String update(User user){
		String[] u = user.getUserId().split(",");
		user.setUserId(u[1]);
		TempModel tempModel = new TempModel();
		tempModel.setTemp(u[0]);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("user", user);
		params.put("tempModel", tempModel);
		userDao = new UserDao();
		userDao.updateUser(params);
		return "redirect:/admin/user/userList";
	}
	/**
	 * 跳转到添加用户界面
	 */
	@RequestMapping("/toAddUser")
	public String toAddUser(){
		return "user/addUser";
	}
	/**
	 * 添加新的用户
	 */
	@RequestMapping("/add")
	public String add(User user){
		userDao = new UserDao();
		userDao.save(user);
		return "redirect:/admin/user/userList";
	}
}
