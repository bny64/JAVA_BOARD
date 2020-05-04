define([], function(){
		
	'use strict';
	
	function Module(){
		
		const pandora = new arguments[1]('bot', arguments);			
		let stockData = null;
		
		//--------------------substantial logic--------------------//
		const selectTag = document.getElementById('selectStock');
		let nowStockCode = selectTag.value;
		let nowStockName = selectTag.selectedIndex > -1 ? (selectTag.options[selectTag.selectedIndex].text) : '';
		initGrid();
		getUserStockList();
	   
		//--------------------event--------------------//		
		//새로운 주식 종목 추가 이벤트
		document.getElementById('addNewStock').addEventListener('click', function(){
			const stockNameEle = document.getElementById('stockName');
			const stockCodeEle = document.getElementById('stockCode');
			const stockNickNameEle = document.getElementById('stockNickName');
			
			if(!(stockNameEle.value || stockCodeEle.value || stockNickNameEle.value)){
				alert('공백을 채워주세요.');
				return;
			}
			
			pandora.bx.ajaxSend({
				url : '/stock/addNewStock.do',
				data : {
					stockName : stockName.value,
					stockCode : stockCode.value,
					stockNickName : stockNickName.value
				},
				callback : function(result){
					if(result.msgCode==='I0000'){
						alert(result.msg);
						stockNameEle.value = '';
						stockCodeEle.value = '';
						stockNickNameEle.value = '';
						getUserStockList();
					}
				}
			})
		});
		//기존 주식 종목 삭제 이벤트
		document.getElementById('deleteStock').addEventListener('click', function(){
			
			if(confirm('삭제하시면 종목에 대한 데이터도 삭제 됩니다.\n삭제하시겠습니까?')){
				pandora.bx.ajaxSend({
					url : '/stock/deleteStock.do',
					data : {
						stockKey : document.getElementById('nowStockKey').value
					},
					callback : function(result){
						if(result.msgCode==='D0000'){
							alert(result.msg);
							getUserStockList();
						}
					}
				})
			}
			
		});
		
		//주식 데이터 불러오는 이벤트
		/*document.getElementById('selectStock').addEventListener('change', function(){
			
			nowStockCode = this.value;
			nowStockName = this.options[this.selectedIndex].text;
			
			getStockData(this.value, 'change');
			
		});*/
		//버튼 이벤트
		/*document.getElementById('addNewData').addEventListener('click', function(){
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
		});*/
		//--------------------event--------------------//
		
		
		
		
		
		//--------------------fucntion--------------------//		
		//사용자 주식종목 불러오는 함수		
		function getUserStockList(){
			pandora.bx.ajaxSend({
				url : '/stock/getUserStockList.do',
				callback : function(result){
					if(result.msgCode==='S0000'){
						let selectElement = document.getElementById('selectStock');
						while(selectElement.firstChild) selectElement.removeChild(selectElement.firstChild);
						
						let html = '<option selected="selected">종목추가</option>';
						
						let userStockList = result.userStockList;
						for(let i = 0; i<userStockList.length; i++){
							const data = userStockList[i];
							html += '<option value="'+ data.stockKey +'" data-stockName="'+data.stockName+'" data-stockCode="'+data.stockCode+'">' 
							+ data.stockNickName + '[' + data.stockName + '(' + data.stockCode + ')]</option>';
						}
						
						selectElement.insertAdjacentHTML('beforeend', html);
						
						setChangeEvt();
					}
				}
			});
		};
		//change 이벤트 등록
		function setChangeEvt(){
			
			document.getElementById('selectStock').addEventListener('change', function(){
				
				let selectedIdx = this.selectedIndex;
				
				if(selectedIdx===0){
					document.getElementById('addNewStock').style.display = '';
					document.getElementById('deleteStock').style.display = 'none';
					return;
				}else{
					document.getElementById('addNewStock').style.display = 'none';
					document.getElementById('deleteStock').style.display = '';
				}
				
				document.getElementById('stockCode').value = '';
				document.getElementById('stockName').value = '';
				document.getElementById('stockNickName').value = '';
				
				document.getElementById('nowStockKey').value = this.value;
				document.getElementById('nowStockCode').value = this.childNodes[selectedIdx].dataset.stockcode;
				document.getElementById('nowStockName').value = this.childNodes[selectedIdx].dataset.stockname;				
				
				getStockData(this.value);
				
			});
			
		}
		//주식 데이터 불러오는 함수
		function getStockData(value){
			
			pandora.bx.ajaxSend({
				url : '/stock/getStockData.do',
				data : {stockKey : value},
				callback : function(result){
					
					if(result.msgCode==='S0000'){						
						stockData = result.stockDataList;
						pandora.$('#stockGrid').pqGrid('option', 'dataModel.data', stockData);
						pandora.$('#stockGrid').pqGrid('refreshDataAndView');
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
		//그리드 생성
		function initGrid(){
			
			let colModel = [
				{ dataIndx:'stockDate', title:"날짜", width:"100", dataType:"date", align:"center", editable:true},
				{ dataIndx:'nowPrc', title:"현재가", width:"100", dataType:"integer", format:'#,###',  align:"center"},				
				{ dataIndx:'byMnt', title:"매수수량", width:"100", dataType:"integer", format:'#,###', align:"center"},
				{ dataIndx:'accMnt', title:"누적수량", width:"100", dataType:"integer", format:'#,###', align:"center"},
				{ dataIndx:'buySrvfee', title:"매수수수료", width:"100", dataType:"float", format:'#,###.0000%', align:"center"},
				{ dataIndx:'ivstPrc', title:"투자액", width:"100", dataType:"integer", format:'#,###', align:"center"},
				{ dataIndx:'sellSrvfee', title:"매도수수료", width:"100", dataType:"float", format:'#,###.0000%', align:"center"},
				{ dataIndx:'taxFee', title:"세금", width:"100", dataType:"float", format:'#,###.0000%', align:"center"},
				{ dataIndx:'accIvstPrc', title:"누적투자액", width:"100", dataType:"integer", format:'#,###', align:"center"},
				{ dataIndx:'accEstPrc', title:"누적평가액", width:"100", dataType:"integer",  format:'#,###',align:"center"},
				{ dataIndx:'ernRate', title:"누적수익률", width:"100", dataType:"float", format:'#,###.0000%', align:"center"},
				{ dataIndx:'ernRatePer', title:"누적수익률(백분위)", width:"100", dataType:"float", format:'#,###.0000%', align:"center"},
			];
			
			pandora.$('#stockGrid').pqGrid({
				width:'auto',
				height:'450',
				scrollModel : {horizontal : true, autoFit:true},
				colModel : colModel,
				numberCell : {show: false}
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