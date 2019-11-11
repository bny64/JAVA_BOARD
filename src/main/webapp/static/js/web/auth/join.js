(function(){
	
	'use strict';
	
	//생일 고르는 날짜 셋팅
	$("#birth").datepicker();
	 
	//이벤트
	$('.input100').not('.datepicker').each(function(){
       $(this).on('blur', function(){
       	
       	if($(this).val().trim() != "") {
               $(this).addClass('has-val');
           }else {
               $(this).removeClass('has-val');
           }       	
       })    
   })
   
   $('.input100.datepicker').on('blur', function(e){
	   var $this = $(this);
	   var $rt = $(e.relatedTarget);
	   
	   if($rt.is('[class*=ui-state]')){		   
		   $this.addClass('has-val');
	   }else{
		   $this.focus();
	   }
   });
	    
   var input = $('[data-validate].validate-input .input100');

   $('.validate-form').on('submit',function(){
       var check = true;

       for(var i=0; i<input.length; i++) {
           if(validate(input[i]) == false){
               showValidate(input[i]);
               check=false;
           }
       }
       return check;
   });
	    
   $('.validate-form .input100').each(function(){
       $(this).focus(function(){
          hideValidate(this);
       });
   });

   $('#chkValId').click(function(){
	   var $id = $('#id').val();
	   $.ajax({
		   url : '/auth/chkValId.do',
		   data : {id : $id},
		   type:'post',
		   success:function(result){
			   var data = result.map;
			   alert(data.msg);
			   if(data.msgCode==='1001') $('#id').val('');
		   },
		   fail:function(){}
	   });
   });
   
   $('#chkValEmail').click(function(){
	   var $id = $('#email').val();
	   $.ajax({
		   url : '/auth/chkValEmail.do',
		   data : {id : $id},
		   type:'post',
		   success:function(result){
			   var data = result.map;
			   alert(data.msg);
			   if(data.msgCode==='1001') $('#email').val('');
		   },
		   fail:function(){}
	   });
   });
   
   function validate (input) {
       if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
           if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
               return false;
           }
       }
       else {
           if($(input).val().trim() == ''){
               return false;
           }
       }
   }

   function showValidate(input) {
       var thisAlert = $(input).parent();

       $(thisAlert).addClass('alert-validate');
   }

   function hideValidate(input) {
       var thisAlert = $(input).parent();

       $(thisAlert).removeClass('alert-validate');
   }
})();