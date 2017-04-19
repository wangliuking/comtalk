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
	 * 添加新的用户和组关联
	 */
	public int save(User2Group user2group){
		SqlSession session = getSqlSession();
		int count = session.insert(NAMESPACE_NAME+"save", user2group);
		session.commit();
		closeSqlSession();
		return count;
	}
}
