(function(){
	
	'user strict';
	
	/**
	 * 윈도우 로드시
	 * */
	window.onload = function(){
		
		const msg = document.querySelector('#msg').value;
		
		if(msg) alert(msg);
	};
	
	/**
	 * 이벤트
	 * */
	//input blur 이벤트
	document.querySelectorAll('.input100').forEach(function(element){
		element.addEventListener('blur', function(){
			if(element.value.trim()!== ''){
				domUtil.addClass(element, 'has-val');
			}else{
				domUtil.removeClass(element, 'has-val');
			}
		});
	});
	
	//input list
   const inputList = document.querySelectorAll('[data-validate].validate-input .input100');
	
   //input focus 이벤트
   inputList.forEach(function(element){	   
	   element.addEventListener('focus', function(){
		   hideValidate(element);
	   });	   
   });
   
   document.querySelector('#loginForm').addEventListener('submit', function(e){
	   
	   const that = this;
	   let check = true;
	   
	   e.preventDefault();
	   
	   inputList.forEach(function(element){
		   if(validate(element)===false){
			   showValidate(element);
			   check = false;
		   }
	   });
	   
	   if(!check) return;
	   
	   that.submit();
   });
   
	/**
    * 함수
    * */
   function validate (input) {
       if(input.getAttribute('type') == 'email' || input.getAttribute('name') == 'email') {
           if(input.value.trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
               return false;
           }
       }
       else {
           if(input.value.trim() == ''){
               return false;
           }
       }
   }

   function showValidate(input) {	   
	   let thisAlert = input.parentNode;      
	   domUtil.addClass(thisAlert, 'alert-validate');	  	   
   }

   function hideValidate(input) {
	   let thisAlert = input.parentNode;
	   domUtil.removeClass(thisAlert, 'alert-validate');
   }
	
})();