package com.web.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ExpressionParser;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * The class that use libraries to save and control files
 * 라이브러리를 사용하여 파일을 저장하고 제어하는 클래스
 * ライブラリーを用いてファイルを保存, 制御するクラス
 * @author Nam Yul Bae
 * @version 1.0.0
 * @since 2020.01.17
 * */

/**
 * To be defined as bean at util-context.xml
 * util-context.xml에 bean으로 정의되어 있다
 * util-context.xmlにbeanとして定義されている
 * */
@Component
public class FileUtil {

	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	/**
	 * @see
	 * @Value("${pathInfo}")
	 * private static [type] [variable] -> It cannot be used in the same format as before
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
	
	/**
	 * @param : String type, Map<String, Object> requestMap 
	 * 
	 * */
	public List<HashMap<String, String>> saveDateFiles(String type, Map<String, Object> requestMap) throws Exception{
		
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
			
			map.put("filePath", midPath);
			map.put("fileName", fileName);
			map.put("orgFileName", orgFileName);
			map.put("fileFullPath", filePath + "/" + fileName);
			
			fileList.add(map);
		}
		
		return fileList;
	}
	
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> saveDateThumbFiles(String type, Map<String, Object> requestMap) throws Exception{
	
		List<HashMap<String, String>> fileList = new ArrayList<HashMap<String, String>>(); //리턴되는 파일 정보 리스트
		List<String> fileFullPaths = (List<String>) requestMap.get("fileFullPaths");
		List<String> fileNames = (List<String>) requestMap.get("fileNames");
		
		String filePath = parseName(type);
		
		String date = new SimpleDateFormat("yyyy.MM").format(new Date());
		String[] dateSplit = date.split("\\.");
		String midPath = "/" + dateSplit[0] + "/" + dateSplit[1];
		
		filePath += midPath;
		
		File dir = new File(filePath);
		
		if(!dir.exists()) dir.mkdirs();
		
		for(int i=0; i<fileFullPaths.size(); i++) {
			
			BufferedImage thumbImg = ImageIO.read(new File(fileFullPaths.get(i)));
			HashMap<String, String> map = new HashMap<String, String>();
			
			int dw = 240, dh = 240;
			
			int ow = thumbImg.getWidth();
			int oh = thumbImg.getHeight();
			
			int nw = ow;
			int nh = (ow * dh)/dw;
			
			if(nh > oh) { 
				nw = (oh * dw) / dh;
				nh = oh; 
			}
			
			BufferedImage cropImg = Scalr.crop(thumbImg, (ow-nw)/2, (oh-nh)/2, nw, nh);
			BufferedImage destImg = Scalr.resize(cropImg, dw, dh);
			
			String orgFileName = fileNames.get(i);
			String fileName = orgFileName.substring(0, orgFileName.indexOf(".")) + "_thumb";
			String ext = orgFileName.substring(orgFileName.indexOf(".")+1);
			
			File thumbFile = new File(filePath + "/" + fileName + "." + ext);
			ImageIO.write(destImg, ext, thumbFile);
			
			map.put("thumbImgFilePath", midPath);
			map.put("thumbFileName", fileName + "." + ext);
			
			fileList.add(map);
		}
		
		return fileList;
	}
	
	public void deleteFile(String type, Map<String, Object> requestMap) throws Exception {
		
		String filePath = parseName(type);
		String fileName = (String) requestMap.get("fileName");
		String fileMidPath = (String) requestMap.get("filePath");
		
		File file = new File(filePath + fileMidPath + "/" + fileName);
		if(file.exists()) file.delete();
		
	}
	
	@SuppressWarnings("unchecked")
	public String parseName(String number) {		
		logger.debug("---------- [FileUtil]:[parseName] -----------");
		
		Map<String, String> filePaths = (Map<String, String>) this.parser.parseExpression(filePathInfo).getValue();
		String name = "";
		
		//추후 properties가 많아지면 케이스 추가
		switch(number) {
		case "1": name = "boardImgFilePath"; break;
		case "thumb_1" : name = "boardImgFileThumbPath"; break;
		case "thumbUrl_1" : name = "boardImgFileThumbUrl"; break;
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
	
	public int reqFileCheck(Map<String, Object> requestMap) throws Exception {
		
		int fileLen = 0;
		
		for(Entry<String, Object> entry : requestMap.entrySet()) {
			
			if(entry.getValue() instanceof MultipartFile) {
				fileLen++;			
			}			
		}
		
		return fileLen;
	}
}