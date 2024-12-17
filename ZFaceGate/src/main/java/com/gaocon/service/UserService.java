package com.gaocon.service;

import com.gaocon.dao.UserDAO;
import com.gaocon.model.SignupDTO;

public interface UserService {
	
	public String findEmpNumFromRequest(String empNumber) throws Exception;
	
	public void insertUserInfoBySignup(SignupDTO signupDTO);

}
