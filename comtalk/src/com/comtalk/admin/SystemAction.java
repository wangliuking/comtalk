package com.comtalk.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comtalk.common.PageModel;
import com.comtalk.dao.SystemDao;
import com.comtalk.dto.System;

/**
 * 系统参数处理类
 * @author 12878
 *
 */
@Controller
@RequestMapping("admin/system")
public class SystemAction {
	SystemDao systemDao = null;
	
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	/**
	 * 分页查询用户
	 * @param system
	 * @param data
	 * @return
	 */
	@RequestMapping("/systemList")
	public String getOrderList(System system,@RequestParam(name="pageIndex",defaultValue="0")int pageIndex,Model data){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("system",system);
		systemDao = new SystemDao();
		int recordCount = systemDao.count(params);
		if(recordCount != 0){
			//设置总共有多个条记录（方便进行分页计算）
			pageModel.setRecordCount(recordCount);
			//设置当前页
			pageModel.setPageIndex(pageIndex);
			
			params.put("pageModel", pageModel);
			List<System> systemList = systemDao.getPageSystem(params);
			
			data.addAttribute("pageModel", pageModel);
			data.addAttribute("systemList", systemList);
			
		}
		return "system/systemList";
	}
	
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping("/showUpdate")
	public String showUpdate(Model data){
		systemDao = new SystemDao();
		System system = systemDao.getSystem();
		data.addAttribute("system", system);
		return "system/updateSystem";
	}
	/**
	 * 系统参数更新
	 * @param system
	 * @return
	 */
	@RequestMapping("/update")
	public String update(System system){
		systemDao = new SystemDao();
		systemDao.updateSystem(system);
		return "forward:/admin/system/systemList";
	}
	
	
}
