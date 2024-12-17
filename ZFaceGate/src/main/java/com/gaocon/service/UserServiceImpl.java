package com.gaocon.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.gaocon.dao.UserDAO;
import com.gaocon.model.SignupDTO;

@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	private UserDAO userDao;

	@Override
	public String findEmpNumFromRequest(String empNumber) throws Exception {
		// TODO Auto-generated method stub
		return userDao.findEmpNumFromRequest(empNumber);
	}

	@Override
	public void insertUserInfoBySignup(SignupDTO signupDTO) {
		// TODO Auto-generated method stub
		userDao.insertUserInfoBySignup(signupDTO);
	}

}
