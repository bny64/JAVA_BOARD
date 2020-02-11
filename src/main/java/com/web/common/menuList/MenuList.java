package com.web.common.menuList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuList {
	
	private int listNo;
	private String name;
	private String type;
	private int depth;
	private int parentListNo;
	private String url;
	private String viewYn;
}
