//class 형식
(function(window){
	
	/**
	 * bAjax.ajaxSend
	 * bAjax.ajaxSendAll
	 * */
	
	class bAjax {
		
		constructor(){}
		
		/**
		 * 비동기 호출 함수
		 * settings값에 callback 함수를 넣어주는 경우 callback 함수 실행
		 * callback 함수 없는 경우는 promise를 리턴
		 * */
		ajaxSend(settings){
			
			let that = this;
			let result = {};//결과 값
			const xhr = new XMLHttpRequest();
			
			const ajaxSetting = {				
				type : 'post', //reqeust 타입
				url : '', //url
				async : true, //비동기 여부
				promise : false, //promise 사용 여부
				data : {}, //request data
				stcData : {}, //유지되는 데이터
				header : 'Content-type', //헤더 타입
				headerValue : 'application/x-www-form-urlencoded; charset=UTF-8', //헤더 값
				dataType:'json',				
				fail : function(error){ //에러함수(promise가 false 일 때) 
					console.error(error);
				}
			};
			
			Object.assign(ajaxSetting, settings);
			
			xhr.open(ajaxSetting.type, ajaxSetting.url, ajaxSetting.async);
			xhr.setRequestHeader(ajaxSetting.header, ajaxSetting.headerValue);
			xhr.responseType = ajaxSetting.dataType;
			
			xhr.send(JSON.stringify(ajaxSetting.data));
			
			if(!ajaxSetting.callback){
				
				return new Promise((resolve, reject)=>{
					
					xhr.onreadystatechange = () => {		
						
						if(xhr.readyState===4){
							
							let resValue = xhr.response;
														
							if(resValue){								
								if(xhr.responseType!=='json') resValue = JSON.parse(xhr.response);

								result = this.parseRtnType(resValue);
								result.status = xhr.status;

							}							
														
							if(xhr.status===200){
								resolve(result);
							}else{
								reject(result);
							}
						}					
					};
					
				});			
				
			}else{
				
				xhr.onreadystatechange = () => {
					
					if(xhr.readyState===4){
						
						let resValue = xhr.response;
												
						if(resValue){								
							if(xhr.responseType!=='json') resValue = JSON.parse(xhr.response);

							result = this.parseRtnType(resValue);
							result.status = xhr.status;

						}

						if(xhr.status===200){						
							return ajaxSetting.callback(result);
						}else{
							return ajaxSetting.fail(result);
						}
					}				
				};
			}
		}
		
		ajaxSendAll(...funcs){
			
			let i = 0;
			const promiseArr = [];
			
			for(; i<funcs.length; i++){
				promiseArr.push(this.ajaxSend(funcs[i]));
			}
			
			return Promise.all(promiseArr);
			
		}
		
		parseRtnType(resVal){
			const data = resVal;
			let returnValue = {};
			
			if(data.map) returnValue = data.map;
			else returnValue = resVal;
			
			return returnValue;
		}
	}
	
	window.bAjax = new bAjax();
	
})(window);