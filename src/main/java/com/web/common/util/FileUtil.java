package com.web.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ExpressionParser;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * @Value("${pathInfo}")
	 * private static 타입 변수 -> 이 형식은 @Value 사용할 수 없음. null값 나옴
	 */
	@Value("${pathInfo}")
	private String filePathInfo;
	
	private ExpressionParser parser;
	
	public ExpressionParser getParser() {
		return parser;
	}

	public void setParser(ExpressionParser parser) {
		this.parser = parser;
	}
	
	public List<HashMap<String, String>> saveFiles(String type, Map<String, Object> requestMap) throws Exception{
		
		List<HashMap<String, String>> fileList = new ArrayList<HashMap<String, String>>(); //리턴되는 파일 정보 리스트
		List<MultipartFile> files = new ArrayList<MultipartFile>(); //request로 넘어온 파일 리스트
		
		String filePath = parseName(type);
		
		for(Entry<String, Object> entry : requestMap.entrySet()) {
			
			if(entry.getValue() instanceof MultipartFile) {
				files.add((MultipartFile) entry.getValue());				
			}			
		}
		
		String date = new SimpleDateFormat("yyyy.MM").format(new Date());
		String[] dateSplit = date.split("\\.");
		String midPath = "/" + dateSplit[0] + "/" + dateSplit[1];
		
		filePath += midPath; 
		
		File dir = new File(filePath);
		
		if(!dir.exists()) dir.mkdirs();
		
		for(int i=0; i<files.size(); i++) {
			
			HashMap<String, String> map = new HashMap<String, String>();
			MultipartFile file = files.get(i);
			
			String orgFileName = file.getOriginalFilename();
			String ext = orgFileName.substring(orgFileName.lastIndexOf("."));
			String fileName = String.join("", dateSplit) + "_" + UUID.randomUUID().toString() + ext;
			
			file.transferTo(new File(filePath + "/" + fileName));
			
			map.put("imgFilePath", midPath);
			map.put("fileName", fileName);
			map.put("orgFileName", orgFileName);
			
			fileList.add(map);
		}
		
		return fileList;
	}
	
	@SuppressWarnings("unchecked")
	public String parseName(String number) {		
		logger.debug("---------- [FileUtil]:[parseName] -----------");
		
		Map<String, String> filePaths = (Map<String, String>) this.parser.parseExpression(filePathInfo).getValue();
		String name = "";
		
		//추후 properties가 많아지면 케이스 추가
		switch(number) {
		case "1": name = "boardImgFilePath"; break;
		default : break;
		}
			
		for(Entry<String, String> entry : filePaths.entrySet()) {
			if(entry.getKey().equals(name)) {
				name = entry.getValue();
				break;
			}
		}
		
		return name;		
	}	
	
}