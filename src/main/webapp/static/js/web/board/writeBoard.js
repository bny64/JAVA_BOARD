(function(){
	'use strict';
		
	$('#content').summernote({
		width : '70%',
		height : '400px',
		align : 'center',
		toolbar: [
		    // [groupName, [list of button]]
		    ['style', ['bold', 'italic', 'underline', 'clear']],
		    ['font', ['strikethrough', 'superscript', 'subscript']],
		    ['fontsize', ['fontsize']],
		    ['color', ['color']],
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['height', ['height']]
		]
	});
	
	/**이벤트 시작**/
	
	document.querySelectorAll('[name="passYn"]').forEach(function(elements){
		
		elements.addEventListener('click', function(){
			const type = this.value;
			const passTag = document.querySelector('#boardPassDiv');
			
			if(type==='passY'){
				passTag.style.display = '';
			}else{
				passTag.style.display = 'none';
			}
		});
		
	});
		
	/**이벤트 종료**/
	
	document.querySelector('#registBtn').addEventListener('click', function(){
		
		if(!validateCheck()) return;
		
		
	});
	
	function validateCheck(){
		
		if(!document.querySelector('#title').value.trim()){
			alert('제목을 입력하세요.');
			return false;
		} else if(!document.querySelector('#content').value.trim()){
			alert('내용을 입력하세요.');
			return false;
		} else if(!document.querySelector('#viewYn').value.trim()){
			alert('글 공개여부를 선택해주세요.');
			return false;
		} else if(document.querySelector('input[type="radio"][name="passYn"]:checked').value==='Y'){
			if(!document.querySelector('#boardPass').value) alert('비밀번호를 입력해주세요.');
			return false;
		}
		
		return true;
		
	}
	
})();