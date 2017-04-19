package com.comtalk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import com.comtalk.dto.Group;

/**
 * 专门针对Group对象的数据库操作
 * 
 * @author 12878
 *
 */
public class GroupDao extends BaseDao {
	
	private static final String NAMESPACE_NAME = "com.comtalk.mapper.GroupMapper.";
	/**
	 * 统计行数
	 * @param group
	 * @return
	 */
	public int count(Map<String,Object> params){
		SqlSession session = getSqlSession();
		int count = session.selectOne(NAMESPACE_NAME+"count",params);
		closeSqlSession();
		return count;
	}
	/**
	 * 分页查询 组数据
	 * @return
	 */
	public List<Group> getPageGroup(Map<String,Object> params){
		SqlSession session = getSqlSession();
		List<Group> groupList = session.selectList(NAMESPACE_NAME+"getGroupListByPage",params);
		closeSqlSession();
		return groupList;
	}

	/**
	 * 通过组编号查询
	 * @param id
	 * @return group
	 */
	public Group getGroupById(String groupId){
		
		SqlSession session = getSqlSession();
		Group group = session.selectOne(NAMESPACE_NAME+"getGroupById", groupId);
		closeSqlSession();
		return group;
	}

	/**
	 * 新增组
	 * @param u
	 * @return
	 */
	public int save(Group u){
		SqlSession session = getSqlSession();
		int count = session.insert(NAMESPACE_NAME+"save",u);
		session.commit();
		closeSqlSession();
		return count;	
	}
	
	/**
	 * 组数据更新
	 * @param group
	 * @return
	 */
	public int updateGroup(Map<String,Object> params){
		SqlSession session = getSqlSession();
		int count = session.update(NAMESPACE_NAME+"updateGroup", params);
		session.commit();
		closeSqlSession();
		return count;
	}
	
	/**
	 * 删除组
	 * @param id
	 * @return
	 */
	public int deleteGroup(String groupId){
		SqlSession session = getSqlSession();
		int count = session.delete(NAMESPACE_NAME+"deleteGroup", groupId);
		session.commit();
		closeSqlSession();
		return count;
	}
	
	/**
	 * 查询所有组ID数据
	 */
	public List<Group> getAllGroupId(){
		SqlSession session = getSqlSession();
		List<Group> groupList = session.selectList(NAMESPACE_NAME+"getAllGroupId");
		closeSqlSession();
		return groupList;
	}
}
