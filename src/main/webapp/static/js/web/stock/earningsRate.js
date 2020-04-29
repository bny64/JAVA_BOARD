define([], function(){
		
	'use strict';
	
	function Module(){
		
		const pandora = new arguments[1]('bot', arguments);			
		let stockData = null;
		
		//--------------------substantial logic--------------------//
		const selectTag = document.getElementById('selectStock');
		let nowStockCode = selectTag.value;
		let nowStockName = selectTag.selectedIndex > -1 ? (selectTag.options[selectTag.selectedIndex].text) : '';
		getStockData(document.getElementById('selectStock').value);
	   
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
				callback : function(result){
					if(result.msgCode==='I0000'){
						location.reload(true);
					}
				}
			})
		});
		
		//주식 데이터 불러오는 이벤트
		document.getElementById('selectStock').addEventListener('change', function(){
			
			nowStockCode = this.value;
			nowStockName = this.options[this.selectedIndex].text;
			
			getStockData(this.value, 'change');
			
		});
		//버튼 이벤트
		document.getElementById('addNewData').addEventListener('click', function(){
			if(stockData.length<1) document.getElementById('beforeData').style.display = 'none';	
			else document.getElementById('beforeData').style.display = '';	
			
			document.getElementById('nowData').style.display = '';			
			document.getElementById('addData').querySelector('h3').textContent = '등록할 데이터';
		});
		document.getElementById('modifyLastData').addEventListener('click', function(){
			if(stockData.length<1) document.getElementById('beforeData').style.display = 'none';	
			else document.getElementById('beforeData').style.display = '';
			document.getElementById('nowData').style.display = '';			
			document.getElementById('addData').querySelector('h3').textContent = '수정할 데이터';
		});
		document.getElementById('deleteLastData').addEventListener('click', function(){
			document.getElementById('beforeData').style.display = 'none';
			document.getElementById('nowData').style.display = 'none';
		});
		
		document.getElementById('addBtn').addEventListener('click', function(){
			addStockData();
		});
		//--------------------event--------------------//
		
		
		
		
		
		//--------------------fucntion--------------------//		
		//주식 데이터 불러오는 함수
		function getStockData(value, evt){
			
			pandora.bx.ajaxSend({
				url : '/stock/getStockData.do',
				data : {stockCode : value},
				callback : function(result){
					
					if(result.msgCode==='S0000'){						
						stockData = result.stockDataList;						
						if(evt==='change') pandora.$('#addNewData').trigger('click');						
					}
					
					
					
				}
			});
			
		}	
		//데이터 추가
		function addStockData(){
			
			const param = {};
			
			param.stockCode = nowStockCode;
			param.stockName = nowStockName;
			
			document.querySelectorAll('#addData input').forEach(function(ele, idx){
				const key = ele.getAttribute('data-name');
				param[key] = ele.value;
			});
			
			pandora.bx.ajaxSend({
				url : '/stock/addStockData.do',
				data : param,
				callback:function(result){
					if(result.msgCode==='I0000') alert(result.msg);
				}
			});
		}
		//차트 refresh
		function chartReload(){
			
		}
		
		//--------------------fucntion--------------------//
		
		
		
		
		
		//--------------------substantial logic--------------------//	   
	}
	
   return Module;
});