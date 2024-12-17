package com.gaocon.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.tensorflow.SavedModelBundle;

import com.gaocon.dao.UserDAO;
import com.gaocon.model.SignupDTO;

@Service
public class AccessServiceImpl implements AccessService{
	
	@Inject
	private UserDAO userDao;

	@Override
	public String findEmpNumFromRequest(String empNumber) throws Exception {
		// TODO Auto-generated method stub
		return userDao.findEmpNumFromRequest(empNumber);
	}
	
	private SavedModelBundle model;
	
	@PostConstruct
	public void loadModel() {
		//model = SavedModelBundle.load("");
	}
	//GPT : https://chatgpt.com/c/675ff77a-3f14-800d-a5bd-1e54c20344b7   
	//가이드 : https://hyunah-home.tistory.com/entry/Facenet-%EC%96%BC%EA%B5%B4-%EC%9D%B8%EC%8B%9D-%EB%AA%A8%EB%8D%B8-Fine-tuning-%ED%95%98%EA%B8%B0
	//사이트 : https://github.com/davidsandberg/facenet/wiki#pre-trained-models
	
}
