package com.gaocon.util;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gaocon.api.UserController;

public class LoggerPlus {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// DTO값을 로그로 출력
	public static void dtoLogger(Object dto, String dtoName) {
        Field[] fields = dto.getClass().getDeclaredFields(); // DTO의 모든 필드 가져오기

        logger.debug("-------<"+dtoName+">-------");
        for (Field field : fields) {
            field.setAccessible(true); // private 필드 접근 가능하게 설정
            try {
                Object value = field.get(dto); // 필드의 값 가져오기
                logger.debug(field.getName() + ": " + value); // 필드명과 값 로깅
            } catch (IllegalAccessException e) {
                logger.error("Error accessing field: " + field.getName(), e);
            }
        }
        logger.debug("---------------------------");
    }
}
