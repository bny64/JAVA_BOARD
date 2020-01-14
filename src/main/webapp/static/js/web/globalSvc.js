/**
 * function used on every page
 * [모든 페이지에 사용되는 함수]
 * */
define([], function(){
	
	'use strict';
	
	function Module(){
		
		//libFilter_top
		const pandora = new arguments[1](arguments);
		
		if(!pandora.ju.browserCheck()) alert('지원하지 않은 브라우저 입니다.\n\사용하시는 브라우저에서는 페이지가 정상 작동되지 않습니다.\n지원되는 브라우저 : edge, chrome, firefox, opera');
		
		var logout = document.querySelector('#logout');

		if(logout){
			logout.addEventListener('click', function(){
				pandora.bx.ajaxSend({
					url : '/auth/logout.do',
					callback:function(){ location.href = '/index.do'; }		
				})
			});
		}
		
		//valuePipe
		let vp = arguments[0].getGlobalVal();
		
		//middle definition[2단계 definition]
		const func_mid = arguments[arguments.length-1];
		
		lsList.mid_lib[0].push(jsFilePath + vp.bottom_path);
		
		requirejs(lsList.mid_lib[0], func_mid);
	}
	
	return Module;
		
});