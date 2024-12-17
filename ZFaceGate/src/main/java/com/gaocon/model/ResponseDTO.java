package com.gaocon.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
	private String message;
    private String status;
    
    // NoArgs 생성자 -> 파라메터 없이 호출하는 경우 ->
    // AllArgs 생성자 -> 파라메터가 있게 호출하는 경우
    
    public ResponseDTO success() {
    	return new ResponseDTO("성공하였습니다", String.valueOf(HttpStatus.OK));
    }
    
    public ResponseDTO httpConnected() {
    	return new ResponseDTO("서버와 연결되었습니다", String.valueOf(HttpStatus.OK));
    }
    
    public ResponseDTO signUpSuccess(){
    	return new ResponseDTO("회원가입되었습니다", String.valueOf(HttpStatus.CREATED));
    }
    
    public ResponseDTO signUpFailure(){
    	return new ResponseDTO("회원가입에 실패하였습니다", String.valueOf(HttpStatus.NOT_FOUND));
    }
    
    public ResponseDTO idConflict() {
    	return new ResponseDTO("이미 아이디가 있습니다", String.valueOf(HttpStatus.CONFLICT));
    }
    
    public ResponseDTO badRequestImage() {
    	return new ResponseDTO("잘못된 이미지 데이터입니다.", String.valueOf(HttpStatus.BAD_REQUEST));
    }
    
    public ResponseDTO noImageParam() {
    	return new ResponseDTO("이미지가 전송되지 않았습니다.", String.valueOf(HttpStatus.BAD_REQUEST));
    }
    
    public ResponseDTO nullParam() {
    	return new ResponseDTO("파라메터가 없습니다.", String.valueOf(HttpStatus.BAD_REQUEST));
    }
    
    public ResponseDTO createMissingImageByExitedId() {
    	return new ResponseDTO("이미 등록된 아이디입니다. 사용자 사진이 등록되지 않은 상태라 자동으로 생성합니다."
    			, String.valueOf(HttpStatus.NOT_FOUND));
    }
    
    public ResponseDTO errorProcessingImage() {
    	return new ResponseDTO("이미지 생성 중 에러 발생.", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
    }
    
    public ResponseDTO serverErrorSaveImage() {
    	return new ResponseDTO("이미지 저장 중 에러 발생.", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
    }
    
}
