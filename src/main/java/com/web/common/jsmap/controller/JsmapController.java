package com.web.common.jsmap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.common.jsmap.domain.Jsmap;
import com.web.common.jsmap.service.JsmapService;

@Controller
@RequestMapping(value="/common")
public class JsmapController {

	@Autowired
	JsmapService jsmapService;
	
	@RequestMapping(value="/jsmap")	
	public @ResponseBody List<Jsmap> getJsmap() throws Exception{		
		return jsmapService.getJsmap();
	}	
}
