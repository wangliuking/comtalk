package com.comtalk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.comtalk.dto.User2Group;

/**
 * 针对关联表进行数据库操作
 * @author 12878
 *
 */
public class User2GroupDao extends BaseDao{
	private static final String NAMESPACE_NAME = "com.comtalk.mapper.User2GroupMapper.";
	/**
	 * 分页查询所有关联信息
	 */
	public List<User2Group> getUser2GroupListByPage(Map<String,Object> params){
		SqlSession session = getSqlSession();
		List<User2Group> list = session.selectList(NAMESPACE_NAME+"getUser2GroupListByPage",params);
		closeSqlSession();
		return list;
	}
	/**
	 * 查询返回条数
	 */
	public int count(Map<String,Object> params){
		SqlSession session = getSqlSession();
		int count = session.selectOne(NAMESPACE_NAME+"count",params);
		closeSqlSession();
		return count;
	}
	/**
	 * 查询是否有重复的用户和组关联
	 */
	public int countId(Map<String,String> params){
		SqlSession session = getSqlSession();
		int count = session.selectOne(NAMESPACE_NAME+"countId", params);
		closeSqlSession();
		return count;
	}
	
	/**
	 * 添加新的用户和组关联
	 */
	public int save(Map<String,String> params){
		SqlSession session = getSqlSession();
		int count = session.insert(NAMESPACE_NAME+"save", params);
		session.commit();
		closeSqlSession();
		return count;
	}
	/**
	 * 删除用户和组的关联
	 */
	public int delete(Map<String,String> params){
		SqlSession session = getSqlSession();
		int count = session.delete(NAMESPACE_NAME+"delete",params);
		session.commit();
		closeSqlSession();
		return count;
	}
	/**
	 * 修改用户和组的关联
	 */
	public int update(Map<String,String> params){
		SqlSession session = getSqlSession();
		int count = session.update(NAMESPACE_NAME+"update", params);
		session.commit();
		closeSqlSession();
		return count;
	}
}
