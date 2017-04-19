package com.comtalk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import com.comtalk.dto.System;

/**
 * 专门针对System对象的数据库操作
 * 
 * @author 12878
 *
 */
public class SystemDao extends BaseDao {
	
	private static final String NAMESPACE_NAME = "com.comtalk.mapper.SystemMapper.";
	/**
	 * 统计行数
	 * @param system
	 * @return
	 */
	public int count(Map<String,Object> params){
		SqlSession session = getSqlSession();
		int count = session.selectOne(NAMESPACE_NAME+"count",params);
		closeSqlSession();
		return count;
	}
	/**
	 * 分页查询 系统参数
	 * @return
	 */
	public List<System> getPageSystem(Map<String,Object> params){
		SqlSession session = getSqlSession();
		List<System> systemList = session.selectList(NAMESPACE_NAME+"getSystemListByPage",params);
		closeSqlSession();
		return systemList;
	}
	/**
	 * 查询全部系统参数
	 * @return
	 */
	public System getSystem(){
		SqlSession session = getSqlSession();
		System system = session.selectOne(NAMESPACE_NAME+"getSystem");
		closeSqlSession();
		return system;
	}
	

	/**
	 * 系统参数更新
	 * @param system
	 * @return
	 */
	public int updateSystem(System system){
		SqlSession session = getSqlSession();
		int count = session.update(NAMESPACE_NAME+"updateSystem", system);
		session.commit();
		closeSqlSession();
		return count;
	}

}
