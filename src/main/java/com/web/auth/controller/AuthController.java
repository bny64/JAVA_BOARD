package com.web.auth.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.auth.domain.User;
import com.web.auth.service.AuthService;
import com.web.common.controller.WebCommonController;
import com.web.common.resolver.CommandMap;
import com.web.common.security.Security;

@Controller
@RequestMapping(value = "/auth")
public class AuthController extends WebCommonController{
	
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AuthService authService;

	@Autowired
	private Security security;
	
	//method �Է����� ���� �� default���� GET
	//�α��� ȭ�� �̵�
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mnv) throws Exception {
		logger.debug("---------- [AuthController]:[login] -----------");		
		mnv.setViewName("auth/login");		
		return mnv;
	}
	
	//���� ȭ�� �̵�
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public ModelAndView join(ModelAndView mnv) throws Exception {
		logger.debug("---------- [AuthController]:[join] -----------");		
		mnv.setViewName("auth/join");	
		return mnv;
	}
	
	//���̵� �ߺ� üũ
	@RequestMapping(value="/chkValId", method = RequestMethod.POST)
	public @ResponseBody CommandMap chkValId(CommandMap map) throws Exception {
		logger.debug("---------- [AuthController]:[chkValId] -----------");
				
		CommandMap comMap = new CommandMap();
				
		List<User> user = authService.selectById(map.get("id").toString());
		
		if(user.size() > 0) {
			comMap.put("msg", "�̹� ������� ID�Դϴ�.");
			comMap.put("msgCode", "1001");
		}else {
			comMap.put("msg", "��밡���� ID�Դϴ�.");
			comMap.put("msgCode", "1000");
		}			
		
		return comMap;
	}
	
	//�̸��� �ߺ� üũ
	@RequestMapping(value="/chkValEmail", method = RequestMethod.POST)
	public @ResponseBody CommandMap chkValEmail(CommandMap map) throws Exception {
		logger.debug("---------- [AuthController]:[chkValEmail] -----------");		
		CommandMap comMap = new CommandMap();
		
		List<User> user = authService.selectByEmail(map.get("email").toString());
		
		if(user.size() > 0) {
			comMap.put("msg", "�̹� ������� �̸����Դϴ�.");
			comMap.put("msgCode", "1001");
		}else {
			comMap.put("msg", "��밡���� �̸����Դϴ�.");
			comMap.put("msgCode", "1000");
		}
		
		return comMap;
	}
	
	//���� �� ����
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public ModelAndView joinForm(ModelAndView mnv, CommandMap map) throws Exception{
		logger.debug("---------- [AuthController]:[joinForm] -----------");
		
		String salt = security.generateSalt();
		User user = new User();
		
		user.setUserKey(UUID.randomUUID().toString());
		user.setSalt(salt);
		user.setId(map.get("id").toString());	
		user.setPassword(security.getEncrypt(map.get("password").toString(), salt));
		user.setEmail(map.get("email").toString());
		user.setName(map.get("name").toString());
		user.setJoinType("java");
		if(map.get("phoneNumber")!= null) user.setPhoneNumber(map.get("phoneNumber").toString());
		if(map.get("imgPath")!= null) user.setImgPath(map.get("imgPath").toString());
		if(map.get("introduce")!=null) user.setIntroduce(map.get("introduce").toString());
		if(map.get("birth")!= null) user.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse(map.get("birth").toString()));
		user.setEmailYn(map.get("emailYn").toString());
		user.setUserType("D");
		
		authService.join(user);
		
		mnv.setViewName("auth/login");
		return mnv;
	}
	
}
