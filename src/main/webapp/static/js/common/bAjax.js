(function(window){
	
	const bAjax = function(){};
	
	bAjax.prototype.ajaxSend = function(settings){
		
		let ajaxSetting = {
			type : 'post',
			url : '',
			async : true,
			promise : false,
			data : {},
			header : 'Content-type',
			headerValue : 'application/x-www-form-urlencoded; charset=UTF-8',
			dataType:'json',
			callback : function(){},
			fail : function(){}
		};
		
		const xhr = new XMLHttpRequest();
		
		Object.assign(ajaxSetting, settings);
		
		xhr.open(ajaxSetting.type, ajaxSetting.url, ajaxSetting.async);
		xhr.setRequestHeader(ajaxSetting.header, ajaxSetting.headerValue);
		xhr.response = ajaxSetting.dataType;
		
		if(ajaxSetting.promise){
			xhr.onreadystatechange = function(){
				
				return new Promise(function(resolve, reject){
					if(xhr.readyState===4 && xhr.status===200){
						resolve(xhr.status, xhr.responseText);
					}else{
						reject(xhr.status);
					}
				});
			};
		}else{
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState===4){
					let result = JSON.parse(xhr.responseText);
					if(xhr.status===200){						
						return ajaxSetting.callback(xhr.status, result);
					}else{
						return ajaxSetting.fail(xhr.status, result);
					}
				}				
			};
		}
		
		xhr.send(JSON.stringify(ajaxSetting.data));
		
	};
	
	window.bAjax = new bAjax();
	
})(window);