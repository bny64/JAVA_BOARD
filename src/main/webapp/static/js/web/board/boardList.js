define([], function(){
	
	'use strict';
	
	function Module(){
		
		const pandora = new arguments[1]('bot', arguments);
		
		//--------------------substantial logic--------------------//
		let page = 1;
		let pageSize = 12;
		
		getBoardList();
		
		function getBoardList(){
			pandora.bx.ajaxSend({
				url : '/board/boardList.do',
				data : {
					page : page,
					pageSize : pageSize
				},
				callback:setData
			})
		};
		
		function setData(result){
			
			if(result.msgCode==='S0000'){
				
				if(result.boards.length>0){
					
					const html = [];
					
					let boardLen = result.boards.length;
					
					for(let i=0; i<boardLen; i++){
						
						const data = result.boards[i];
						html.push('<li class="one_quarter' + ((i+1)%4===1?' first':'') + '">');
						html.push(	'<a href="javascript:void(0);">');
						if(data.thumbImgFilePath) html.push(		'<img src="' +  data.thumbImgFilePath+ '/' + data.thumbFileName + '" alt="">');
						html.push(			'<span>' + data.title + '</span><br>');
						html.push('<span>' + data.id + '(' + data.name + ')</span>');
						html.push(	'</a>');
						html.push('</li>');
						
					}
										
					document.querySelector('#boards').appendChild(document.createRange().createContextualFragment(html.join('')));					
				}				
			}
			
		}
		//--------------------substantial logic--------------------//
	};
	
	return Module;
	
});
