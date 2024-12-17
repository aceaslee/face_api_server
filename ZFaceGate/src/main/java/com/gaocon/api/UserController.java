package com.gaocon.api;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gaocon.model.SignupDTO;
import com.gaocon.model.ResponseDTO;
import com.gaocon.service.UserService;
import com.gaocon.util.FileUtilPlus;
import com.gaocon.util.LoggerPlus;
import com.gaocon.util.ObjectUtilPlus;

@RestController
@RequestMapping(value="/auth")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService userService;
	private ResponseDTO responseDTO = new ResponseDTO();
	
	
	@GetMapping(value ="/chk_conn") 
	public ResponseEntity<ResponseDTO> checkConnection() {
		return ResponseEntity.ok(responseDTO.httpConnected());
	}
	
	@PostMapping(value ="/access/face", produces = "application/json; charset=UTF-8")
	public ResponseEntity<ResponseDTO> faceAccess(
			@RequestParam("clientCompId") String clientCompId, 
			@RequestParam("file") MultipartFile file){
		
		String fileName = System.currentTimeMillis()+".png";
		String filePath="C:\\gaocon_apps\\face_gate\\"+clientCompId+"\\captured_face";
		
		/// -> AI 얼굴대조
		
		
		
		try {
			
		}catch (Exception e) {
			return ResponseEntity.ok(responseDTO.errorProcessingImage());
		}
		
		// 테스트용 저장
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream() 
					,new File(filePath, fileName));
			return ResponseEntity.ok(responseDTO.success());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.ok(responseDTO.serverErrorSaveImage());
		}
	}
	
	
	@PostMapping(value ="/signup", produces = "application/json; charset=UTF-8")
	public ResponseEntity<ResponseDTO> signUp(
			@ModelAttribute final SignupDTO signupDTO,
			@RequestParam("file") MultipartFile file) throws Exception{
		
		// 파라메터 테스트
		LoggerPlus.dtoLogger(signupDTO, "signupDTO");
		
		// 파라메터 Null일 경우 에러 리턴
		if(ObjectUtilPlus.findNullField(signupDTO) != null) {
			logger.debug("파라메터가 없습니다.");
			return ResponseEntity.ok(responseDTO.nullParam());
		}
		
		// 파일 이름 및 경로 생성
		String[] fileNameElement= {
				signupDTO.getClientCompId(),
				signupDTO.getEmpNumber(),
				signupDTO.getEmpName()
		};
		String fileName = String.join("_", fileNameElement).concat(".png");
		String filePath="C:\\gaocon_apps\\face_gate\\"+signupDTO.getClientCompId()+"\\registered_faces";
		
		// 파일이 들어왔는지 확인
		if(file.isEmpty()) {
			logger.debug("이미지가 전송되지 않았습니다");
			return ResponseEntity.ok(responseDTO.noImageParam());
		}
		
		// 디렉토리에 해당 얼굴파일로 검색
		List<String> keywordArr = Arrays.asList(
				signupDTO.getClientCompId(),signupDTO.getEmpNumber());
		List<String> fileInDir = FileUtilPlus.searchFilesWithKeywords(
				filePath, keywordArr);
		
		// DB에 해당 아이디로 검색
		String foundEmpNum = userService.findEmpNumFromRequest(
				signupDTO.getEmpNumber());
		
		// 신규아이디 여부와 기존에 생성된 이미지 여부에 따른 분기
		if(!StringUtils.isEmpty(foundEmpNum) && fileInDir.isEmpty()) {
			logger.debug(fileName+"는 이미 등록된 아이디입니다");
			
			FileUtils.copyInputStreamToFile(file.getInputStream()
					,new File(filePath, fileName)); // 파일생성
			return ResponseEntity.ok(responseDTO.createMissingImageByExitedId());
			
		}else if(!StringUtils.isEmpty(foundEmpNum)) {
			logger.debug("이미 "+foundEmpNum+" 아이디가 있습니다");
			return ResponseEntity.ok(responseDTO.idConflict());
		}
		
		// 파일생성 및 유저정보 DB 업데이트
		try {
			//경로에 파일 생성
			FileUtils.copyInputStreamToFile(file.getInputStream()
					,new File(filePath, fileName));
			
			userService.insertUserInfoBySignup(signupDTO);
			
			logger.debug("signup::사용자 생성됨");
			return ResponseEntity.ok(responseDTO.signUpSuccess());
			
		}catch (Exception e) {
			// TODO: handle exception
			logger.debug("에러::", e);
			return ResponseEntity.ok(responseDTO.signUpFailure());
		}
		
	}
	
	@PostMapping(value ="/signup_test", produces = "application/json; charset=UTF-8")
	public ResponseEntity<ResponseDTO> signUp(
			@ModelAttribute final SignupDTO signupDTO) throws Exception{
		
		// 파라메터 테스트
		LoggerPlus.dtoLogger(signupDTO, "signupDTO");
		
		// 파라메터 Null일 경우 에러 리턴
		if(ObjectUtilPlus.findNullField(signupDTO) != null) {
			logger.debug("파라메터가 없습니다.");
			return ResponseEntity.ok(responseDTO.nullParam());
		}
		return ResponseEntity.ok(responseDTO.signUpSuccess());
	}
}
