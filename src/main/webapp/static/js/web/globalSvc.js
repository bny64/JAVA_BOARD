define(['bAjax', 'jsUtil'], function(bx, ju){
	
	'use strict';
	
	if(!ju.browserCheck()) alert('지원하지 않은 브라우저 입니다.\n\사용하시는 브라우저에서는 페이지가 정상 작동되지 않습니다.\n지원되는 브라우저 : edge, chrome, firefox, opera');
	
	var logout = document.querySelector('#logout');

	if(logout){
		logout.addEventListener('click', function(){
			bx.ajaxSend({
				url : '/auth/logout.do',
				callback:function(){ location.href = '/index.do'; }		
			})
		});
	}
	
});