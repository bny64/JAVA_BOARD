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
	function saveBoard(){
		
	}
	
})();