define([], function(){
	
	'use strict';
	
	function Module(){
		
		const vp = arguments[0].getGlobalVal();
		const pandora = new arguments[1]('bot', arguments);
		
		//--------------------substantial logic--------------------//
		let page = 1;
		let pageSize = 10;
		
		getBoardList();
		
		function getBoardList(){
			pandora.bx.ajaxSend({
				url : '/board/boardList.do',
				data : {
					page : page,
					pageSize : pageSize
				},
				callback:function(result){
					console.log(result);
				}
			})
		}
		//--------------------substantial logic--------------------//
	};
	
	return Module;
	
});
