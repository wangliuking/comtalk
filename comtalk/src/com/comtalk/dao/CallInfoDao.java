package com.comtalk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import com.comtalk.dto.CallInfo;

/**
 * 专门针对CallInfo对象的数据库操作
 * 
 * @author 12878
 *
 */
public class CallInfoDao extends BaseDao {
	
	private static final String NAMESPACE_NAME = "com.comtalk.mapper.CallInfoMapper.";
	/**
	 * 统计行数
	 * @param CallInfo
	 * @return
	 */
	public int count(Map<String,Object> params){
		SqlSession session = getSqlSession();
		int count = session.selectOne(NAMESPACE_NAME+"count",params);
		closeSqlSession();
		return count;
	}
	/**
	 * 分页查询 呼叫信息
	 * @return
	 */
	public List<CallInfo> getPageCallInfo(Map<String,Object> params){
		SqlSession session = getSqlSession();
		List<CallInfo> callInfoList = session.selectList(NAMESPACE_NAME+"getCallInfoListByPage",params);
		closeSqlSession();
		return callInfoList;
	}
	/**
	 * 查询全部呼叫信息
	 * @return
	 */
	public CallInfo getCallInfo(){
		SqlSession session = getSqlSession();
		CallInfo callInfo = session.selectOne(NAMESPACE_NAME+"getCallInfo");
		closeSqlSession();
		return callInfo;
	}
	

	/**
	 * 根据id删除呼叫信息
	 * @param callInfo
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
