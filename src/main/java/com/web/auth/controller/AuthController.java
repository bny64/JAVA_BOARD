package com.web.auth.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.web.auth.domain.User;
import com.web.auth.domain.UserAuthority;
import com.web.auth.service.AuthService;
import com.web.common.controller.WebCommonController;
import com.web.common.resolver.CommandMap;
import com.web.common.security.PasswordEncoding;
import com.web.common.support.message.MsgCode;
import com.web.common.support.message.MsgList;
import com.web.log.domain.LoginLog;
import com.web.log.service.LogService;

@Controller
@RequestMapping(value = "/auth")
public class AuthController extends WebCommonController{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AuthService authService;

	@Autowired
	private LogService logService;
	
	@Autowired
	private PasswordEncoding passwordEncoding;
	
	
	
	//method 입력하지 않을 시 default값은 GET
	//로그인 화면 이동
	@RequestMapping(value="/login") //로그아웃 실패 후 이 페이지로 이동하기 때문에 GET, POST 모두 받아야 함.
	public ModelAndView login(CommandMap reqMap, ModelAndView mnv, HttpServletRequest request) throws Exception {		
		logger.debug("********************[BoardController]:[login:GET]********************");
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		
		if(flashMap!=null) {
			mnv.addObject("msg", flashMap.get("msg"));
		}
				
		mnv.setViewName("auth/login");		
		return mnv;
	}
	
	//가입 화면 이동
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public ModelAndView join(ModelAndView mnv) throws Exception {
		logger.debug("********************[BoardController]:[join:GET]********************");
		
		mnv.setViewName("auth/join");	
		return mnv;
	}
	
	@RequestMapping(value="/forgetEmail", method= RequestMethod.GET)
	public ModelAndView forgetEmail(ModelAndView mnv) throws Exception{
		logger.debug("********************[BoardController]:[forgetEmail:GET]********************");
		
		mnv.setViewName("auth/forgetEmail");
		return mnv;
	}	
	
	@RequestMapping(value="/forgetPassword", method= RequestMethod.GET)
	public ModelAndView forgetPassword(ModelAndView mnv) throws Exception{
		logger.debug("********************[BoardController]:[forgetPassword:GET]********************");
		
		mnv.setViewName("auth/forgetPassword");
		return mnv;
	}	
	
	//아이디 중복 체크
	@RequestMapping(value="/chkValId", method = RequestMethod.POST)
	public @ResponseBody CommandMap chkValId(CommandMap reqMap) throws Exception {
		logger.debug("********************[BoardController]:[chkValId:POST]********************");
				
		CommandMap comMap = new CommandMap();	
		String[] msg;
				
		List<User> user = authService.selectById(reqMap.get("id").toString());
		
		if(user.size() > 0) {
			msg = MsgList.getInstance().getCodeMessage(MsgCode.DuplicateId);
			comMap.put("msgCode", msg[0]);
			comMap.put("msg", msg[1]);
		}else {
			msg = MsgList.getInstance().getCodeMessage(MsgCode.ValidateId);
			comMap.put("msgCode", msg[0]);
			comMap.put("msg", msg[1]);
		}			
		
		return comMap;
	}
	
	//이메일 중복 체크
	@RequestMapping(value="/chkValEmail", method = RequestMethod.POST)
	public @ResponseBody CommandMap chkValEmail(CommandMap reqMap) throws Exception {
		logger.debug("********************[BoardController]:[chkValEmail:POST]********************");		
		
		CommandMap comMap = new CommandMap();
		String[] msg;
		
		List<User> user = authService.selectByEmail(reqMap.get("email").toString());
		
		if(user.size() > 0) {
			msg = MsgList.getInstance().getCodeMessage(MsgCode.DuplicateEmail);
			comMap.put("msgCode", msg[0]);
			comMap.put("msg", msg[1]);
		}else {
			msg = MsgList.getInstance().getCodeMessage(MsgCode.ValidateEmail);
			comMap.put("msgCode", msg[0]);
			comMap.put("msg", msg[1]);
		}
		
		return comMap;
	}
	
	//가입 폼 전송
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String joinForm(ModelAndView mnv, CommandMap reqMap, RedirectAttributes redirectAttr) throws Exception{
		logger.debug("********************[BoardController]:[joinForm:POST]********************");	
		
		String[] msg;
		Map<String, String> msgMap = new HashMap<String, String>();
		
		User user = new User();
		UserAuthority userAuthority = new UserAuthority();
		
		user.setUserKey(UUID.randomUUID().toString());		
		user.setId(reqMap.get("id").toString());
		user.setPassword(passwordEncoding.encode(reqMap.get("password").toString()));
		user.setEmail(reqMap.get("email").toString());
		user.setName(reqMap.get("name").toString());
		if(reqMap.get("phoneNumber")!= null) user.setPhoneNumber(reqMap.get("phoneNumber").toString());
		if(reqMap.get("imgPath")!= null) user.setImgPath(reqMap.get("imgPath").toString());
		if(reqMap.get("introduce")!=null) user.setIntroduce(reqMap.get("introduce").toString());
		if(reqMap.get("birth")!= null) user.setBirth(reqMap.get("birth").toString());
		user.setEmailYn(reqMap.get("emailYn").toString());
		
		authService.join(user);
		
		userAuthority.setUser(user);
		userAuthority.setId(user.getId());
		userAuthority.setName(user.getName());
		
		authService.saveAuth(userAuthority);
		
		msg = MsgList.getInstance().getCodeMessage(MsgCode.SuccessJoin);
		msgMap.put("msgCode", msg[0]);
		msgMap.put("msg", msg[1]);
		redirectAttr.addFlashAttribute("msg", msgMap);
		
		return "redirect:/auth/login.do";
	}
	
	//이메일 찾기
	@RequestMapping(value="/forgetEmail", method = RequestMethod.POST)
	public @ResponseBody CommandMap forgetEmail(CommandMap reqMap) throws Exception {
		logger.debug("********************[BoardController]:[chkValId:POST]********************");
		
		CommandMap comMap = new CommandMap();	
		String[] msg;
				
		List<User> user = authService.selectEmailByIdName(reqMap.getMap());
		
		if(user.size()>0) {
			comMap.put("email", user.get(0).getEmail());
			msg = MsgList.getInstance().getCodeMessage(MsgCode.SelectSuccess);
			comMap.put("msgCode", msg[0]);
			comMap.put("msg", msg[1]);
		}else {
			msg = MsgList.getInstance().getCodeMessage(MsgCode.SelectNonResult);
			comMap.put("msgCode", msg[0]);
			comMap.put("msg", msg[1]);
		}
		
		return comMap;		
	}
	
	//이메일 찾기
	@RequestMapping(value="/forgetPassword", method = RequestMethod.POST)
	public @ResponseBody CommandMap forgetPassword(CommandMap reqMap) throws Exception {
		logger.debug("********************[BoardController]:[forgetPassword:POST]********************");
		
		CommandMap comMap = new CommandMap();	
		String[] msg;
		Map<String, String> serviceMap = new HashMap<String, String>();
		
		
		
		int cnt = authService.selectByIdNmEml(reqMap.getMap());
		
		if(cnt==1) {
		
			String newPass = String.valueOf((int) (Math.random()*9999) + 1000);
			serviceMap.put("password", passwordEncoding.encode(newPass));
			serviceMap.put("email", (String) reqMap.get("email")); 
			
			try {
				
				authService.resetPassword(serviceMap);
				msg = MsgList.getInstance().getCodeMessage(MsgCode.UpdateSuccess);
				comMap.put("password", newPass);
				comMap.put("msgCode", msg[0]);
				comMap.put("msg", msg[1]);
				
			} catch(Exception e) {
				
				e.printStackTrace();
				msg = MsgList.getInstance().getCodeMessage(MsgCode.UpdateFail);
				comMap.put("msgCode", msg[0]);
				comMap.put("msg", msg[1]);
			}
			
		} else {
			
			msg = MsgList.getInstance().getCodeMessage(MsgCode.SelectNonResult);
			comMap.put("msgCode", msg[0]);
			comMap.put("msg", msg[1]);
			
		}
		
		return comMap;
		
	}
	
	//로그인 처리
	//@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginForm(CommandMap map, RedirectAttributes redirectAttr, HttpSession session) throws Exception {		
		logger.debug("********************[BoardController]:[loginForm:POST]********************");	
		
		String email = map.get("email").toString();
		String password = map.get("password").toString();		
		Map<String, String> msgMap = new HashMap<String, String>();
		Map<String, Object> sessionMap = new HashMap<String, Object>();
		boolean result = false;
		
		List<User> user = authService.selectByEmail(email);
		LoginLog log = new LoginLog();		
		User getUser = null;
		
		if(user.size() > 0) {
			getUser = user.get(0);
			String getPassword = getUser.getPassword();			
			result = passwordEncoding.matches(password, getPassword);			
		}
		
		if(!result) {			
			String[] msg = MsgList.getInstance().getCodeMessage(MsgCode.NullUser);
			msgMap.put("msgCode", msg[0]);
			msgMap.put("msg", msg[1]);
			redirectAttr.addFlashAttribute("msg", msgMap);
			return "redirect:/auth/login.do";			
		}
		
		sessionMap.put("id", getUser.getId());
		sessionMap.put("name", getUser.getName());
		sessionMap.put("joinType", getUser.getJoinType());
		
		session.setAttribute("userInfo", sessionMap);
		
		log.setJoinType(getUser.getJoinType());
		log.setName(getUser.getName());		
		log.setId(getUser.getId());

		logService.writeLoginLog(log);
		
		String[] msg = MsgList.getInstance().getCodeMessage(MsgCode.SuccessLogin);
		msgMap.put("msgCode", msg[0]);
		msgMap.put("msg", msg[1]);
		redirectAttr.addFlashAttribute("msg", msgMap);
		return "redirect:/index.do";		
	}
	
	//로그아웃
	
	//@RequestMapping(value="/logout", method = RequestMethod.POST) public
	ModelAndView logout(ModelAndView mnv, HttpSession session) throws Exception {
		logger.debug("********************[BoardController]:[logout:POST]********************");	
  
		if(session.getAttribute("userInfo") != null) {
			session.removeAttribute("userInfo"); }
  
		mnv.setViewName("index");
  
		return mnv; 
	}
	 
	
}
