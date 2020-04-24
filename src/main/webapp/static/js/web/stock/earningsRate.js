define([], function(){
		
	'use strict';
	
	function Module(){
		
		const pandora = new arguments[1]('bot', arguments);			
		//--------------------substantial logic--------------------//
		
		
		
		
	   
		//--------------------event--------------------//		
		document.getElementById('addNewStock').addEventListener('click', function(){
			const stockName = document.getElementById('stockName');
			const stockCode = document.getElementById('stockCode');
			
			pandora.bx.ajaxSend({
				url : '/stock/addNewStock.do',
				data : {
					stockName : stockName.value,
					stockCode : stockCode.value
				},
				callback : function(result){
					if(result.msgCode==='I0000'){
						stockName.value = '';
						stockCode.value = '';
						alert(result.msg);
						
					}
					
				}
			})
			
		});
		//--------------------event--------------------//
		
		
		
		
		
		//--------------------fucntion--------------------//
		
		//--------------------fucntion--------------------//
		
		
		
		
		
		//--------------------substantial logic--------------------//	   
	}
	
   return Module;
});