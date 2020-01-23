define([], function(){
	
	'use strict';
	
	function Module(){
		
		const vp = arguments[0].getGlobalVal();
		const pandora = new arguments[1]('bot', arguments);
		
		//--------------------substantial logic--------------------//
		
		
		
		
		const listNo = document.querySelector('#listNo').value;
		//--------------------event--------------------//
		const delBtn = document.querySelector('#deleteBtn');
		const modBtn = document.querySelector('#modifyBtn');
		
		if(delBtn){
			delBtn.addEventListener('click', function(){
				pandora.bx.ajaxSend({
					url : '/board/deleteBoard.do',
					data : {
						listNo : listNo
					},
					promise : true
				}).then(function(result){
					
				});
			});
		}
		
		if(modBtn){
			modBtn.addEventListener('click', function(){
				location.href = '/board/modifyBoard.do?listNo='+listNo;
			});
		}
		
		document.querySelector('#backBtn').addEventListener('click', function(){
			location.href = '/board/boardList.do'
		});
		//--------------------event--------------------//
		
		
		
		
		
		//--------------------substantial logic--------------------//
		
	}
	
	return Module;
	
});