package com.comtalk.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comtalk.common.PageModel;
import com.comtalk.dao.CallInfoDao;
import com.comtalk.dto.CallInfo;

/**
 * 呼叫信息处理类
 * @author 12878
 *
 */
@Controller
@RequestMapping("admin/callInfo")
public class CallInfoAction {
	CallInfoDao callInfoDao = null;
	
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	/**
	 * 分页查询呼叫信息
	 * @param callInfo
	 * @param data
	 * @return
	 */
	@RequestMapping("/callInfoList")
	public String getOrderList(CallInfo callInfo,@RequestParam(name="pageIndex",defaultValue="0")int pageIndex,Model data){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("callInfo",callInfo);
		callInfoDao = new CallInfoDao();
		int recordCount = callInfoDao.count(params);
		if(recordCount != 0){
			//设置总共有多个条记录（方便进行分页计算）
			pageModel.setRecordCount(recordCount);
			//设置当前页
			pageModel.setPageIndex(pageIndex);
			
			params.put("pageModel", pageModel);
			List<CallInfo> callInfoList = callInfoDao.getPageCallInfo(params);
			
			data.addAttribute("pageModel", pageModel);
			data.addAttribute("callInfoList", callInfoList);
			
		}
		return "callInfo/callInfoList";
	}
	

	/**
	 * 根据id删除呼叫信息
	 * @param callInfo
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(int id){
		callInfoDao = new CallInfoDao();
		callInfoDao.delete(id);
		return "forward:/admin/callInfo/callInfoList";
	}
	
	
}
