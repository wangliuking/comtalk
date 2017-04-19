package com.comtalk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import com.comtalk.dto.User;

/**
 * 专门针对User对象的数据库操作
 * 
 * @author 12878
 *
 */
public class UserDao extends BaseDao {
	
	private static final String NAMESPACE_NAME = "com.comtalk.mapper.UserMapper.";
	/**
	 * 统计订单行数
	 * @param user
	 * @return
	 */
	public int count(Map<String,Object> params){
		SqlSession session = getSqlSession();
		int count = session.selectOne(NAMESPACE_NAME+"count",params);
		closeSqlSession();
		return count;
	}
	/**
	 * 分页查询 用户数据
	 * @return
	 */
	public List<User> getPageUser(Map<String,Object> params){
		SqlSession session = getSqlSession();
		List<User> userList = session.selectList(NAMESPACE_NAME+"getUserListByPage",params);
		closeSqlSession();
		return userList;
	}
	
	/**
	 * 通过用户名和密码查询用户名（登录）
	 * @param u
	 * @return
	 */
	public User getUserByUserIdAndByPassword(User u){
		
		SqlSession session = getSqlSession();
		User user = session.selectOne(NAMESPACE_NAME+"getUserByUserIdAndByPassword", u);
		closeSqlSession();
		return user;
	}
	
	/**
	 * 通过用户编号查询用户数据
	 * @param id
	 * @return user
	 */
	public User getUserById(String userId){
		
		SqlSession session = getSqlSession();
		User user = session.selectOne(NAMESPACE_NAME+"getUserById", userId);
		closeSqlSession();
		return user;
	}
	
	/**
	 * 检查用户名是否重复
	 * @param userId
	 * @return true 表示重复，false表示不重复
	 */
	public boolean getUserByUserId(String userId){
		
		SqlSession session = getSqlSession();
		int count = session.selectOne(NAMESPACE_NAME+"getUserByUserId", userId);
		closeSqlSession();
		return count>0?true:false;
	}
	/**
	 * 管理员登录
	 * @param u
	 * @return
	 */
	public User getAdmin(User u){
		
		SqlSession session = getSqlSession();
		User user = session.selectOne(NAMESPACE_NAME+"getAdmin", u);
		closeSqlSession();
		return user;
	}
	/**
	 * 新增用户
	 * @param u
	 * @return
	 */
	public int save(User u){
		SqlSession session = getSqlSession();
		int count = session.insert(NAMESPACE_NAME+"save",u);
		session.commit();
		closeSqlSession();
		return count;	
	}
	
	/**
	 * 用户数据更新
	 * @param user
	 * @return
	 */
	public int updateUser(Map<String,Object> params){
		SqlSession session = getSqlSession();
		int count = session.update(NAMESPACE_NAME+"updateUser", params);
		session.commit();
		closeSqlSession();
		return count;
	}
	
	/**
	 * 删除用户操作
	 * @param id
	 * @return
	 */
	public int deleteUser(String userId){
		SqlSession session = getSqlSession();
		int count = session.delete(NAMESPACE_NAME+"deleteUser", userId);
		session.commit();
		closeSqlSession();
		return count;
	}
	/**
	 * 更新用户登录时间和登录状态
	 */
	public int updateLogin(String userId){
		SqlSession session = getSqlSession();
		int count = session.update(NAMESPACE_NAME+"updateLogin",userId);
		session.commit();
		closeSqlSession();
		return count;
	}
	/**
	 * 注销用户登录状态
	 */
	public int updateLogout(String userId){
		SqlSession session = getSqlSession();
		int count = session.update(NAMESPACE_NAME+"updateLogout",userId);
		session.commit();
		closeSqlSession();
		return count;
	}
	/**
	 * 查询所有用户ID数据
	 *
	 */
	public List<User> getAllUserId(){
		SqlSession session = getSqlSession();
		List<User> userList = session.selectList(NAMESPACE_NAME+"getAllUserId");
		closeSqlSession();
		return userList;
	}
}
