package com.gaocon.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.gaocon.model.SignupDTO;

public interface UserDAO {
	
	public String findEmpNumFromRequest (String empNumber) throws Exception;
	
	public void insertUserInfoBySignup(SignupDTO signupDTO);
}
