define([], function(){
	
	'use strict';
	
	function Module(){
		
		const vp = arguments[0].getGlobalVal();
		const pandora = new arguments[1]('bot', arguments);
		
		//--------------------substantial logic--------------------//
		pandora.$('#contents').summernote({
			width : '70%',
			height : '400px',
			align : 'center',
			toolbar: [
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']]
			]
		});
		
		pandora.bx.ajaxSend({
			url : '/board/modifyBoard.do',
			data : {
				listNo : document.querySelector('#listNo').value				
			},
			callback : setInitData
		})

		function setInitData(result){
			
			if(result.msgCode==='S0000'){
				
				const board = result.board;
				
				
			}
			
		}
		
		
		
		
		
		//--------------------substantial logic--------------------//
		
	}
	
	return Module;
	
});