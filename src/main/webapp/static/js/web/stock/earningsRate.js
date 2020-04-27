define([], function(){
		
	'use strict';
	
	function Module(){
		
		const pandora = new arguments[1]('bot', arguments);			
		let stockData = null;
		//--------------------substantial logic--------------------//
		
		
		
		
	   
		//--------------------event--------------------//		
		//새로운 주식 종목 추가 이벤트
		document.getElementById('addNewStock').addEventListener('click', function(){
			const stockName = document.getElementById('stockName');
			const stockCode = document.getElementById('stockCode');
			
			pandora.bx.ajaxSend({
				url : '/stock/addNewStock.do',
				data : {
					stockName : stockName.value,
					stockCode : stockCode.value
				},
				callback : addNewStock
			})
		});
		
		//주식 데이터 불러오는 이벤트
		document.getElementById('selectStock').addEventListener('change', function(){
			const stockCode = this.value;
			
			pandora.bx.ajaxSend({
				url : '/stock/getStockData.do',
				data : {stockCode : stockCode},
				callback:getStockData
			})
			
		});
		//버튼 이벤트
		document.getElementById('addNewData').addEventListener('click', function(){
			if(stockData.length<1) document.getElementById('beforeData').style.display = 'none';	
			else ocument.getElementById('beforeData').style.display = '';	
			
			document.getElementById('nowData').style.display = '';			
			document.getElementById('addData').querySelector('h3').textContent = '등록할 데이터';
		});
		document.getElementById('modifyLastData').addEventListener('click', function(){
			if(stockData.length<1) document.getElementById('beforeData').style.display = 'none';	
			else ocument.getElementById('beforeData').style.display = '';
			document.getElementById('nowData').style.display = '';			
			document.getElementById('addData').querySelector('h3').textContent = '수정할 데이터';
		});
		document.getElementById('deleteLastData').addEventListener('click', function(){
			document.getElementById('beforeData').style.display = 'none';
			document.getElementById('nowData').style.display = 'none';
		});
		//--------------------event--------------------//
		
		
		
		
		
		//--------------------fucntion--------------------//
		//새로운 주식 종목 추가 함수
		function addNewStock(result){
			if(result.msgCode==='I0000'){
				stockName.value = '';
				stockCode.value = '';
				alert(result.msg);
				
			}
		}
		//주식 데이터 불러오는 함수
		function getStockData(result){
			if(result.msgCode==='S0000'){
				stockData = result.stockDataList;
			}
		}		
		//차트 refresh
		function chartReload(){
			
		}
		
		//--------------------fucntion--------------------//
		
		
		
		
		
		//--------------------substantial logic--------------------//	   
	}
	
   return Module;
});