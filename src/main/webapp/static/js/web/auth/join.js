(function(){
	
	'use strict';
	
	//생일 고르는 날짜 셋팅
	$("#birth").datepicker();
	
   [].filter.call(document.querySelectorAll('.input100'), function(element){	   
	   return domUtil.hasClass(element, 'datepicker') ? false : true;	   
   }).forEach(function(element){
	   element.addEventListener('blur', function(){
		   if(element.value.trim()!== ''){
			   domUtil.addClass(element, 'has-val');
		   }else{
			   domUtil.removeClass(element, 'has-val');
		   }
	   });
   });
   
   document.querySelector('#birth').onchange = function(){
	   const that = this;
	   if(that.value!=='') domUtil.addClass(that, 'has-val');
	   else domUtil.removeClass(that, 'has-val');
   };
   
   /*document.querySelector('.input100.datepicker').addEventListener('blur', function(e){
	   let that = this;
	   
	   let rt = e.relatedTarget || null;
	   
	   if(!rt) return;
	   
	   if(rt.parentNode.querySelector('[class*=ui-state]')){
		   domUtil.addClass(that, 'has-val');
	   }else{
		   that.focus();
	   }
	   
   });*/
   
   /*$('.input100.datepicker').on('blur', function(e){
	   var $this = $(this);
	   var $rt = $(e.relatedTarget);
	   
	   if($rt.is('[class*=ui-state]')){		   
		   $this.addClass('has-val');
	   }else{
		   $this.focus();
	   }
   });*/

	//input list
   const inputList = document.querySelectorAll('[data-validate].validate-input .input100');
   
   //join 버튼
   const joinForm = document.querySelector('#joinForm');
   
   joinForm.addEventListener('submit', function(e){
	   
	   e.preventDefault();
	   
	   if(document.activeElement !== document.querySelector('.login100-form-btn')) return; 
	   
	   let check = false;
	   
	   inputList.forEach(function(element){
		   if(validate(element)===false){
			   showValidate(element);
			   check = false;
		   }
	   });
   });
      
   inputList.forEach(function(element){	   
	   element.addEventListener('focus', function(){
		   hideValidate(element);
	   });	   
   });

   document.querySelector('#chkValId').addEventListener('click', function(e){
	   let userId = document.querySelector('#id').value;
	   
	   if(userId===''){
			  document.querySelector('#id').value = '';
			  alert('ID를 입력해 주세요.')
			  return;
		  }
	   
	   bAjax.ajaxSend({
		   url : '/auth/chkValId.do',
		   data : {id : userId},
		   callback:function(result){
			   const data = result;
			   if(data.msgCode==='1001') document.querySelector('#id').value = '';
			   alert(data.msg);
		   }
	   });
   });   
   
   document.querySelector('#chkValEmail').addEventListener('click', function(){
	  let userEmail = document.querySelector('#email').value;
	  
	  if(userEmail===''){
		  document.querySelector('#email').value = '';
		  alert('이메일을 입력해 주세요.')
		  return;
	  }
	  
	  bAjax.ajaxSend({
		   url : '/auth/chkValEmail.do',
		   data : {email : userEmail},
		   callback:function(result){
			   const data = result;
			   if(data.msgCode==='1001') document.querySelector('#email').value = '';
			   alert(data.msg);
		   }
	   });
   });
      
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