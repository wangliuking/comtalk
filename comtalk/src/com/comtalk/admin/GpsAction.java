package com.comtalk.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comtalk.common.PageModel;
import com.comtalk.dao.GpsDao;
import com.comtalk.dto.Gps;

/**
 * GPS数据处理类
 * @author 12878
 *
 */
@Controller
@RequestMapping("admin/gps")
public class GpsAction {
	GpsDao gpsDao = null;
	
	/**
	 * 分页模型对象
	 */
	PageModel pageModel = new PageModel();
	/**
	 * 分页查询短信记录
	 * @param gps
	 * @param data
	 * @return
	 */
	@RequestMapping("/gpsList")
	public String getOrderList(Gps gps,@RequestParam(name="pageIndex",defaultValue="0")int pageIndex,Model data){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("gps",gps);
		gpsDao = new GpsDao();
		int recordCount = gpsDao.count(params);
		if(recordCount != 0){
			//设置总共有多个条记录（方便进行分页计算）
			pageModel.setRecordCount(recordCount);
			//设置当前页
			pageModel.setPageIndex(pageIndex);
			
			params.put("pageModel", pageModel);
			List<Gps> gpsList = gpsDao.getPageGps(params);
			
			data.addAttribute("pageModel", pageModel);
			data.addAttribute("gpsList", gpsList);
			
		}
		return "gps/gpsList";
	}
	

	/**
	 * 根据id删除gps记录
	 * @param gps
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(int id){
		gpsDao = new GpsDao();
		gpsDao.delete(id);
		return "forward:/admin/gps/gpsList";
	}
	
	
}
