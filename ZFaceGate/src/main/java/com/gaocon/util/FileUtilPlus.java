package com.gaocon.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtilPlus {
	
	
	
	
	
	/** 키워드로 파일 검색 **/
	public static List<String> searchFilesWithKeywords(String directoryPath, List<String> keywords) {
        List<String> matchingFiles = new ArrayList<>();
        
        // 지정한 디렉토리
        File directory = new File(directoryPath);
        
        // 디렉토리가 존재하고 디렉토리인지 확인
        if (directory.exists() && directory.isDirectory()) {
            // 모든 파일을 순회하면서 파일 이름에 키워드가 모두 포함되어 있는지 확인
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    
                    // 파일 이름에 모든 키워드가 포함되는지 체크
                    boolean allKeywordsMatch = keywords.stream().allMatch(fileName::contains);
                    
                    if (allKeywordsMatch) {
                        matchingFiles.add(fileName);
                    }
                }
            }
        }
        return matchingFiles;
    }
	
	
}
