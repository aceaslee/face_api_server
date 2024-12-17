package com.gaocon.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.xml.stream.events.Namespace;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gaocon.model.SignupDTO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.gaocon.mappers.UserMapper.";

	@Override
	public String findEmpNumFromRequest(String empNumber) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+"findEmpNumFromRequest", empNumber);
	}

	@Override
	public void insertUserInfoBySignup(SignupDTO signupDTO) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+"insertUserInfoBySignup", signupDTO);
	}
}
