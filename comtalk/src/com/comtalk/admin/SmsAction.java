package com.comtalk.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comtalk.common.PageModel;
import com.comtalk.dao.SmsDao;
import com.comtalk.dto.Sms;

/**
 * 系统参数处理类
 * @author 12878
 *
 */
@Controller
@RequestMapping("admin/sms")
public class SmsAction {
	SmsDao smsDao = null;
	
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	/**
	 * 分页查询短信记录
	 * @param sms
	 * @param data
	 * @return
	 */
	@RequestMapping("/smsList")
	public String getOrderList(Sms sms,@RequestParam(name="pageIndex",defaultValue="0")int pageIndex,Model data){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sms",sms);
		smsDao = new SmsDao();
		int recordCount = smsDao.count(params);
		if(recordCount != 0){
			//设置总共有多个条记录（方便进行分页计算）
			pageModel.setRecordCount(recordCount);
			//设置当前页
			pageModel.setPageIndex(pageIndex);
			
			params.put("pageModel", pageModel);
			List<Sms> smsList = smsDao.getPageSms(params);
			
			data.addAttribute("pageModel", pageModel);
			data.addAttribute("smsList", smsList);
			
		}
		return "sms/smsList";
	}
	

	/**
	 * 根据id删除短信记录
	 * @param sms
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(int id){
		smsDao = new SmsDao();
		smsDao.delete(id);
		return "forward:/admin/sms/smsList";
	}
	
	
}
