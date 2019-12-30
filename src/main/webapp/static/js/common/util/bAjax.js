//class 형식
(function(window){
	
	/**
	 * bAjax.ajaxSend : ajax 전송
	 * bAjax.ajaxSendAll : ajax 병렬 전송
	 * bAjax.ajaxForm : ajax form 전송
	 * */
	
	class bAjax {
		
		constructor(){}
		
		/**
		 * 비동기 호출 함수
		 * settings값에 callback 함수를 넣어주는 경우 callback 함수 실행
		 * callback 함수 없는 경우는 promise를 리턴
		 * */
		ajaxSend(settings){
						
			const that = this;
			let result = {};//결과 값
			const xhr = new XMLHttpRequest();
			
			const ajaxSetting = {				
				type : 'post', //reqeust 타입
				url : '', //url
				async : true, //비동기 여부
				promise : false, //promise 사용 여부
				data : {}, //request data
				mtnData : {}, //유지되는 데이터
				token : true, //spring security 토큰값
				header : 'Content-type', //헤더 타입
				headerValue : 'application/x-www-form-urlencoded; charset=UTF-8', //헤더 값				
				dataType:'json',				
				fail : function(error){ //에러함수(promise가 false 일 때)					
					alert(error);
				}
			};
			
			Object.assign(ajaxSetting, settings);
			
			xhr.open(ajaxSetting.type, ajaxSetting.url, ajaxSetting.async);
			xhr.setRequestHeader(ajaxSetting.header, ajaxSetting.headerValue);			
			
			if(ajaxSetting.token){
				const csrfToken = document.querySelector('#_csrf').getAttribute('content');
				const csrfHeader = document.querySelector('#_csrf_header').getAttribute('content');
				xhr.setRequestHeader(csrfHeader, csrfToken);
			}
			
			xhr.responseType = ajaxSetting.dataType;
			
			if(ajaxSetting.promise){
				
				return new Promise(function(resolve, reject){
					
					xhr.onreadystatechange = function(){		
						
						if(xhr.readyState===4){
							
							let resValue = xhr.response;
														
							if(resValue){								
								if(xhr.responseType!=='json') resValue = JSON.parse(xhr.response);

								result = that.parseRtnType(resValue);
								result.status = xhr.status;

							}							
														
							if(xhr.status===200){
								if(ajaxSetting.mtnData) result.mtnData = ajaxSetting.mtnData;
								resolve(result);
							}else{
								reject(result);
							}
						}					
					};
					
					xhr.send(JSON.stringify(ajaxSetting.data));
					
				});			
				
			}else{
				
				xhr.onreadystatechange = function(){
					
					if(xhr.readyState===4){
						
						let resValue = xhr.response;
												
						if(resValue){								
							if(xhr.responseType!=='json') resValue = JSON.parse(xhr.response);

							result = that.parseRtnType(resValue);
							result.status = xhr.status;

						}

						if(xhr.status===200){					
							if(ajaxSetting.mtnData) result.mtnData = ajaxSetting.mtnData;
							return ajaxSetting.callback(result);
						}else{
							return ajaxSetting.fail(result);
						}
					}				
				};
			
				xhr.send(JSON.stringify(ajaxSetting.data));				
			}
		}
		
		/**
		 * 비동기 호출 함수(form)
		 * settings값에 promise로 사용할 경우 promise값을 true 설정(default -> promise : false)
		 * */
		ajaxForm(settings){
			
			console.log('ajaxForm은 JQUERY를 사용합니다.');
						
			const that = this;
			let result = {};//결과 값
			
			const ajaxSetting = {
				form : $('#form'), //form 태그
				type : 'post', //reqeust 타입
				url : '', //url
				async : true, //비동기 여부
				enctype : 'multipart/form-data',
				promise : false, //promise 사용 여부
				data : {}, //request data
				mtnData : {}, //유지되는 데이터
				token : true, //spring security 토큰값 설정						
				dataType:'json',
				beforeSend : function(xhr){ //spring security 토큰
					const csrfToken = document.querySelector('#_csrf').getAttribute('content');
					const csrfHeader = document.querySelector('#_csrf_header').getAttribute('content');
					xhr.setRequestHeader(csrfHeader, csrfToken);
				},				
				callback : function(){},				
				error : function(result){
					const error = that.parseRtnType(result);
					alert(error.msg);
				}				
			};
			
			Object.assign(ajaxSetting, settings);
			
			//promise : true
			if(ajaxSetting.promise){
				
				return new Promise(function(resolve, reject){
				
					ajaxSetting.success = function(result, textStatus){
						
						result = that.parseRtnType(result);
						
						result.status = textStatus;
						result.mtnData = ajaxSetting.mtnData;
						
						resolve(result);						
					}
					
					ajaxSetting.error = function(result){
						result = that.parseRtnType(result);
						reject(result);						
					}
					
					ajaxSetting.form.ajaxForm(ajaxSetting);					
					ajaxSetting.form.submit();					
				});
				
			//proise : false(callback 함수 이용)
			}else{
				
				ajaxSetting.success = function(result, textStatus){
					
					result = that.parseRtnType(result);
					
					result.mtnData = ajaxSetting.mtnData;
					result.status = textStatus;
					
					ajaxSetting.callback(result);					
				}
				
				ajaxSetting.form.ajaxForm(ajaxSetting);
				ajaxSetting.form.submit();				
				
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
			
			if(data.map){
				returnValue = data.map;
			}else if(data.responseJSON){
				returnValue = data.responseJSON.map;
			}else{
				returnValue = resVal;
			} 
			
			return returnValue;
		}
	}
	
	window.bAjax = new bAjax();
	
})(window);