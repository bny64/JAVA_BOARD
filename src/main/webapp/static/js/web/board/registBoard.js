(function(){
	'use strict';
		
	let reqParam;
	
	const whiteExt = ['jpeg', 'jpg', 'gif', 'png'];
	
	$('#contents').summernote({
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
			
			if(type==='Y'){
				passTag.style.display = '';
			}else{
				passTag.style.display = 'none';
			}
		});
		
	});
	
	document.querySelector('#registBtn').addEventListener('click', function(){
		
		if(!validateCheck()) return;
		
		const $form = $('#registBoardForm');
		$form.ajaxForm({
			url : '/board/registBoard.do',
			enctype : "multipart/form-data",
			beforeSend : function(xhr){
				const csrfToken = document.querySelector('#_csrf').getAttribute('content');
				const csrfHeader = document.querySelector('#_csrf_header').getAttribute('content');
				xhr.setRequestHeader(csrfHeader, csrfToken);
			},
			success : function(result){
				console.log(result);
			},
			error : function(result){
				console.error(result);
			}
		})
		
		$form.submit();
		
	});
	
	document.getElementById('boardFile').addEventListener('change', function(){
		
		let element = this;
		
		if(!element.value) return;
		
		const fileInfo = this.files[0];
		
		let fileExt = fileInfo.name.substring(fileInfo.name.lastIndexOf('.')+1, fileInfo.name.length);
		if(!fileValidateCheck(fileExt)){
			
			let msg = whiteExt.join(', ');
			
			msg = msg.substring(0, msg.length-2);
			element.value = '';
			alert(msg);
			
			return;
		}
		
		if(fileInfo.size > 10000000){
			element.value = '';
			alert('10MB까지 저장 가능합니다.');			
		}
	});
	/**이벤트 종료**/
	
	/**함수**/
	//게시글 유효성 검사
	function validateCheck(){
		
		if(!document.querySelector('#title').value.trim()){
			alert('제목을 입력하세요.');
			return false;
		} else if(!document.querySelector('#contents').value.trim()){
			alert('내용을 입력하세요.');
			return false;
		} else if(!document.querySelector('#viewYn').value.trim()){
			alert('글 공개여부를 선택해주세요.');
			return false;
		} else if(document.querySelector('input[type="radio"][name="passYn"]:checked').value==='passY'){
			if(!document.querySelector('#boardPass').value) alert('비밀번호를 입력해주세요.');
			document.getElementById('boardPass').focus();
			return false;
		}
		
		reqParam = {
			title : document.getElementById('title').value,
			contents : document.getElementById('contents').value,
			viewYn : document.getElementById('viewYn').value,
			passwordYn : document.querySelector('input[type="radio"][name="passYn"]:checked').value,
			password : document.getElementById('boardPass').value
		};		
		
		return true;
		
	}
	
	//파일 확장자 유효성 검사
	function fileValidateCheck(fileExt){
		
		for(let index in whiteExt){
			if(whiteExt[index]===fileExt) return true;			
		}
		
		return false;
	}
	/**함수**/
})();