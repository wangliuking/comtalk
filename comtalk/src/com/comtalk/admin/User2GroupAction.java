package com.comtalk.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comtalk.common.PageModel;
import com.comtalk.common.TempModel;
import com.comtalk.common.WebConstant;
import com.comtalk.dao.GroupDao;
import com.comtalk.dao.User2GroupDao;
import com.comtalk.dao.UserDao;
import com.comtalk.dto.Group;
import com.comtalk.dto.User;
import com.comtalk.dto.User2Group;

@Controller
@RequestMapping("/admin/user2group")
public class User2GroupAction {
	User2GroupDao user2groupDao = null;
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	/**
	 * 分页查询用户
	 * @param user2group
	 * @param data
	 * @return
	 */
	@RequestMapping("/user2groupList")
	public String getOrderList(User2Group user2group,@RequestParam(name="pageIndex",defaultValue="0")int pageIndex,Model data){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("user2group",user2group);
		user2groupDao = new User2GroupDao();
		int recordCount = user2groupDao.count(params);
		if(recordCount != 0){
			//设置总共有多个条记录（方便进行分页计算）
			pageModel.setRecordCount(recordCount);
			//设置当前页
			pageModel.setPageIndex(pageIndex);
			
			params.put("pageModel", pageModel);
			List<User2Group> user2groupList = user2groupDao.getUser2GroupListByPage(params);
			
			data.addAttribute("pageModel", pageModel);
			data.addAttribute("user2groupList", user2groupList);
			
		}
		return "user2group/user2groupList";
	}
		
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping("/showUpdate")
	public String showUpdate(String userId,String groupId,Model data){
		UserDao userDao = new UserDao();
		List<User> userList = userDao.getAllUserId();
		GroupDao groupDao = new GroupDao();
		List<Group> groupList = groupDao.getAllGroupId();
		data.addAttribute("userList", userList);
		data.addAttribute("groupList",groupList);
		data.addAttribute("userId",userId);
		data.addAttribute("groupId", groupId);
		return "user2group/updateUser2Group";
	}
	/**
	 * 关联数据更新
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Map<String,Object> update(String userId,String groupId,String updateUserId,String updateGroupId){
		Map<String,Object> map = new HashMap<>();
		Map<String,String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("groupId", groupId);
		params.put("updateUserId", updateUserId);
		params.put("updateGroupId", updateGroupId);
		User2GroupDao user2groupDao = new User2GroupDao();
		int countId = user2groupDao.countId(params);
		if(countId==0){
			int count = user2groupDao.update(params);	
			if(count>0){
				map.put("tip", "添加成功");
				map.put("status", 0);
			}else{
				map.put("tip", "添加失败");
				map.put("status", 1);
			}
		}else if(countId!=0){
			map.put("tip", "已存在该用户和组关联");
			map.put("status", 2);		
		}	
		return map;
	}
	/**
	 * 跳转到添加用户界面
	 */
	@RequestMapping("/toAddUser2Group")
	public String toAddUser(Model data){
		UserDao userDao = new UserDao();
		List<User> userList = userDao.getAllUserId();
		GroupDao groupDao = new GroupDao();
		List<Group> groupList = groupDao.getAllGroupId();
		data.addAttribute("userList", userList);
		data.addAttribute("groupList",groupList);
		return "user2group/addUser2Group";
	}
	/**
	 * 添加新的用户
	 */
	@ResponseBody
	@RequestMapping("/addUser2Group")
	public Map<String,Object> addUser2Group(String userId,String groupId){
		Map<String,Object> map = new HashMap<>();
		Map<String,String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("groupId", groupId);
		User2GroupDao user2groupDao = new User2GroupDao();
		int countId = user2groupDao.countId(params);
		if(countId==0){
			int count = user2groupDao.save(params);	
			if(count>0){
				map.put("tip", "添加成功");
				map.put("status", 0);
			}else{
				map.put("tip", "添加失败");
				map.put("status", 1);
			}
		}else if(countId!=0){
			map.put("tip", "已存在该用户和组关联");
			map.put("status", 2);		
		}	
		return map;
	}
	/**
	 * 删除用户和组的关联
	 */
	@RequestMapping("/delete")
	public String deleteUser2Group(String userId,String groupId){
		Map<String,String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("groupId", groupId);
		User2GroupDao user2groupDao = new User2GroupDao();
		user2groupDao.delete(params);
		return "forward:/admin/user2group/user2groupList";
	}
}
