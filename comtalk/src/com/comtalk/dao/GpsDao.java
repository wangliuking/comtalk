package com.comtalk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import com.comtalk.dto.Gps;

/**
 * 专门针对Gps对象的数据库操作
 * 
 * @author 12878
 *
 */
public class GpsDao extends BaseDao {
	
	private static final String NAMESPACE_NAME = "com.comtalk.mapper.GpsMapper.";
	/**
	 * 统计行数
	 * @param Gps
	 * @return
	 */
	public int count(Map<String,Object> params){
		SqlSession session = getSqlSession();
		int count = session.selectOne(NAMESPACE_NAME+"count",params);
		closeSqlSession();
		return count;
	}
	/**
	 * 分页查询 短信记录
	 * @return
	 */
	public List<Gps> getPageGps(Map<String,Object> params){
		SqlSession session = getSqlSession();
		List<Gps> gpsList = session.selectList(NAMESPACE_NAME+"getGpsListByPage",params);
		closeSqlSession();
		return gpsList;
	}
	/**
	 * 查询全部短信记录
	 * @return
	 */
	public Gps getGps(){
		SqlSession session = getSqlSession();
		Gps gps = session.selectOne(NAMESPACE_NAME+"getGps");
		closeSqlSession();
		return gps;
	}
	

	/**
	 * 根据id删除短信记录
	 * @param gps
	 * @return
	 */
	public int delete(int id){
		SqlSession session = getSqlSession();
		int count = session.delete(NAMESPACE_NAME+"delete", id);
		session.commit();
		closeSqlSession();
		return count;
	}

}
