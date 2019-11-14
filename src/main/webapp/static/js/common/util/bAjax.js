//class 형식
(function(window){
	
	class bAjax {
		
		constructor(){}
		
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
				header : 'Content-type', //헤더 타입
				headerValue : 'application/x-www-form-urlencoded; charset=UTF-8', //헤더 값
				dataType:'json',
				callback : function(){}, //콜백함수(promise가 false 일 때)
				fail : function(){} //에러함수(promise가 false 일 때)
			};
			
			Object.assign(ajaxSetting, settings);
			
			xhr.open(ajaxSetting.type, ajaxSetting.url, ajaxSetting.async);
			xhr.setRequestHeader(ajaxSetting.header, ajaxSetting.headerValue);
			xhr.responseType = ajaxSetting.dataType;
			
			xhr.send(JSON.stringify(ajaxSetting.data));
			
			if(ajaxSetting.promise){
				
				return new Promise(function(resolve, reject){
					
					xhr.onreadystatechange = function(){		
						
						if(xhr.readyState===4){
							
							let resValue = xhr.response;
							
							if(xhr.responseType==='json') resValue = xhr.response;
							else resValue = JSON.parse(xhr.response);

							result = that.parseRtnType(resValue);
							result.status = xhr.status;
							
							if(xhr.status===200){
								resolve(result);
							}else{
								reject(result);
							}
						}					
					};
					
				});			
				
			}else{
				
				xhr.onreadystatechange = function(){
					
					if(xhr.readyState===4){
						
						let resValue = xhr.response;
						
						if(xhr.responseType==='json') resValue = xhr.response;
						else resValue = JSON.parse(xhr.response);
						
						result = that.parseRtnType(resValue);
						result.status = xhr.status;

						if(xhr.status===200){						
							return ajaxSetting.callback(result);
						}else{
							return ajaxSetting.fail(result);
						}
					}				
				};
			}
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