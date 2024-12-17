package com.gaocon.util;

import java.lang.reflect.Field;

public class ObjectUtilPlus {

	// 구분자 추가
	public static String addDelimiter(Object obj, String delimiter) throws IllegalAccessException {
        StringBuilder result = new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // private 필드 접근 가능하게 설정
            Object value = field.get(obj); // 필드 값 가져오기
            if (value != null) {
                result.append(value.toString()).append(delimiter); // 필드 값을 구분자와 함께 추가
            }
        }
        if (result.length() > 0) {
        	// 마지막 구분자 제거
            result.setLength(result.length() - delimiter.length());
        }
        return result.toString();
    }
	
	//객채 요소 NULL 여부 체크
	public static String findNullField(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(obj) == null) {
                    return field.getName(); // null인 필드명 반환
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null; // 모든 필드가 null이 아니면 null 반환
    }
}
