package com.gaocon.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class SignupDTO {
	private String clientCompId;
	private String empName;
    private String empNumber;
    private String empPhone;
    
    public String joinWith(String delimiter, List<String> elements) {
    	return String.join(delimiter, elements);
    };
}
