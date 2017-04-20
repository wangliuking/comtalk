package com.comtalk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import com.comtalk.dto.Sms;

/**
 * 专门针对Sms对象的数据库操作
 * 
 * @author 12878
 *
 */
public class SmsDao extends BaseDao {
	
	private static final String NAMESPACE_NAME = "com.comtalk.mapper.SmsMapper.";
	/**
	 * 统计行数
	 * @param sms
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
	public List<Sms> getPageSms(Map<String,Object> params){
		SqlSession session = getSqlSession();
		List<Sms> smsList = session.selectList(NAMESPACE_NAME+"getSmsListByPage",params);
		closeSqlSession();
		return smsList;
	}
	/**
	 * 查询全部短信记录
	 * @return
	 */
	public Sms getSms(){
		SqlSession session = getSqlSession();
		Sms sms = session.selectOne(NAMESPACE_NAME+"getSms");
		closeSqlSession();
		return sms;
	}
	

	/**
	 * 根据id删除短信记录
	 * @param sms
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
