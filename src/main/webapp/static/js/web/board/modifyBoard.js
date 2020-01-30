define([], function(){
	
	'use strict';
	
	function Module(){
		
		const vp = arguments[0].getGlobalVal();
		const pandora = new arguments[1]('bot', arguments);
		
		//--------------------substantial logic--------------------//
		
		
				
		//--------------------init--------------------//
		pandora.$('#contents').summernote({
			width : '70%',
			height : '400px',
			align : 'center',
			toolbar: [
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']]
			]
		});
		
		pandora.bx.ajaxSend({
			url : '/board/modifyBoard.do',
			data : {
				listNo : document.querySelector('#listNo').value				
			},
			callback : setInitData
		});
		//--------------------init--------------------//
		
		
		
		
		//--------------------event--------------------//	
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
		
		/*파일 변경 버튼*/
		document.querySelector('#fileBtn').addEventListener('click', function(){
			
			let fileStatus = document.querySelector('#fileStatus');
			const divTag = document.querySelector('#fileDiv');
			const fileBtnTag = document.querySelector('#fileBtn');
			
			if(fileStatus.value==='L'){
				if(confirm('파일을 삭제하시겠습니까?')){
					fileStatus.value = 'D';
					document.querySelector('#fileNm').textContent = '없음';
					fileBtnTag.textContent = '파일 추가';
					document.querySelector('#fileDelRvBtn').style.display = '';
					document.querySelector('#fileWarn').style.display = 'none';
				}
			}else if(fileStatus.value==='D'){
				document.querySelector('#fileDiv').style.display = '';				
			}
			
		});
		
		document.querySelector('#fileDelRvBtn').addEventListener('click', function(){
			document.querySelector('#fileStatus').value = 'L';
			this.style.display = 'none';
			document.querySelector('#fileNm').textContent = document.querySelector('#fileOrgNm').value;
			document.querySelector('#fileBtn').textContent = '파일 삭제';
			document.querySelector('#fileWarn').style.display = '';
			document.querySelector('#fileDiv').style.display = 'none';
			
		});
		
		document.querySelector('#boardFile').addEventListener('change', function(){
			document.querySelector('#fileStatus').value = 'U';
			
			if(this.files.length>0){
				document.querySelector('#fileNm').textContent = this.files[0].name;
				document.querySelector('#fileDelRvBtn').style.display = 'none';
			}			
		});
		//--------------------event--------------------//	
		
		
		
		
		//--------------------function--------------------//		
		function setInitData(result){
			
			if(result.msgCode==='S0000'){
			
				const board = result.board;				
				
				document.querySelector('#title').value = board.title;
				pandora.$('#contents').summernote('code', board.contents);
				document.querySelector('#viewYn').selectedIndex = (board.viewYn === 'Y' ? 1 : board.viewYn === 'N' ? 2 : 0);
				
				const passYnIdx = board.passwordYn === 'Y' ? 0 : board.passwordYn === 'N' ? 2 : 1;				
				document.querySelectorAll('[name="passYn"]')[passYnIdx].checked = true;
				
				document.querySelector('#listNo').value = board.listNo;
				
				if(board.orgFileName){
					document.querySelector('#fileOrgNm').value = board.orgFileName;
					document.querySelector('#fileNm').textContent = board.orgFileName;
					document.querySelector('#fileBtn').textContent = '파일 삭제';
					document.querySelector('#fileWarn').style.display = '';
				} 
			}
		}
		//--------------------function--------------------//
		
		
		
		
		//--------------------substantial logic--------------------//
		
	}
	
	return Module;
	
});