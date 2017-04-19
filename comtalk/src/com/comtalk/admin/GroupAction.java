package com.comtalk.admin;

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
import com.comtalk.dao.GroupDao;
import com.comtalk.dto.Group;

@Controller
@RequestMapping("/admin/group")
public class GroupAction {
	GroupDao groupDao = null;
	
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	/**
	 * 分页查询组
	 * @param group
	 * @param data
	 * @return
	 */
	@RequestMapping("/groupList")
	public String getOrderList(Group group,@RequestParam(name="pageIndex",defaultValue="0")int pageIndex,Model data){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("group",group);
		groupDao = new GroupDao();
		int recordCount = groupDao.count(params);
		if(recordCount != 0){
			//设置总共有多个条记录（方便进行分页计算）
			pageModel.setRecordCount(recordCount);
			//设置当前页
			pageModel.setPageIndex(pageIndex);
			
			params.put("pageModel", pageModel);
			List<Group> groupList = groupDao.getPageGroup(params);
			
			data.addAttribute("pageModel", pageModel);
			data.addAttribute("groupList", groupList);
			
		}
		return "group/groupList";
	}
	
	/**
	 * 删除操作
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(String groupId){
		groupDao = new GroupDao();
		groupDao.deleteGroup(groupId);
		return "forward:/admin/group/groupLists";
	}
	/**
	 * 删除后页面跳转
	 * @param group
	 * @param pageIndex
	 * @param data
	 * @return
	 */
	
	@RequestMapping("/groupLists")
	public String getOrderLists(Group group,@RequestParam(name="pageIndex",defaultValue="0")int pageIndex,Model data){
		group.setGroupId(null);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("group",group);
		groupDao = new GroupDao();
		int recordCount = groupDao.count(params);
		if(recordCount != 0){
			//设置总共有多个条记录（方便进行分页计算）
			pageModel.setRecordCount(recordCount);
			//设置当前页
			pageModel.setPageIndex(pageIndex);
			
			params.put("pageModel", pageModel);
			List<Group> groupList = groupDao.getPageGroup(params);
			
			data.addAttribute("pageModel", pageModel);
			data.addAttribute("groupList", groupList);
			
		}
		return "group/groupList";
	}
	
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping("/showUpdate")
	public String showUpdate(String groupId,Model data){
		groupDao = new GroupDao();
		Group group = groupDao.getGroupById(groupId);
		data.addAttribute("group", group);
		return "group/updateGroup";
	}
	/**
	 * 组更新
	 * @param group
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Group group){
		String[] g = group.getGroupId().split(",");
		group.setGroupId(g[1]);
		TempModel tempModel = new TempModel();
		tempModel.setTempGroup(g[0]);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("group", group);
		params.put("tempModel", tempModel);
		groupDao = new GroupDao();
		groupDao.updateGroup(params);
		return "redirect:/admin/group/groupList";
	}
	/**
	 * 跳转到组添加界面
	 */
	@RequestMapping("/toAddGroup")
	public String toAddGroup(){
		return "group/addGroup";
	}
	/**
	 * 添加新的组
	 */
	@RequestMapping("/addGroup")
	public String addGroup(Group group){
		groupDao = new GroupDao();
		groupDao.save(group);
		return "redirect:/admin/group/groupList";
	}
	
	
}
