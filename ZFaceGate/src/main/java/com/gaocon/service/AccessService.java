package com.gaocon.service;

import com.gaocon.dao.UserDAO;
import com.gaocon.model.SignupDTO;

public interface AccessService {
	
	public String findEmpNumFromRequest(String empNumber) throws Exception;

}
