//set envirment in requirejs[requirejs 환경설정 셋팅]
require.config(requireConfig);

//js path[js 경로]
const jsFilePath = '/js/web';

//top file path[1단계 파일 경로]
const top_path = '/globalSvc.js';

const lsList = checkLoadJsLib();

//add top definition[1단계 defintion 추가]
lsList.lib.push(jsFilePath + top_path);

window.onload = function(){
	
	//asynchronous send[비동기 호출]
	requirejs(lsList.lib, function(){
		
		//prevent usage of jquery in global scope[jquery가 전역에서 사용되는 것을 방지]
		jQuery.noConflict(true);

		//valuePipe
		let vp = arguments[0].getGlobalVal();
		
		//top definition[1단계 definition]
		const func_top = arguments[arguments.length-1];
				
		lsList.top_lib[0].push(jsFilePath + vp.middle_path);
		
		requirejs(lsList.top_lib[0], func_top);
		
	});
}

//check libraries to load[로드해야할 js 라이브러리 확인]
function checkLoadJsLib(){
	
	let type = {
			top_lib : [['valuePipe', 'libFilterTop', 'bAjax','jsUtil'],['', '', 'bx', 'ju']]				//top
	};
		
	if(location.pathname.indexOf('/auth')>-1){
		
		type.lib = ['valuePipe', 'jquery','jqueryui','animsition','popper','bootstrap','select2','moment','daterangepicker','countdowntime','setDatepickerKor'];
		type.mid_lib = [['valuePipe', 'libFilterMid'],['','']];												//middle
		type.bot_lib = [['valuePipe', 'libFilterBot','jquery','bAjax','domUtil'],['','','$','bx','du']];	//bottom
		
	}else if(location.pathname.indexOf('/board')>-1){
		
		type.lib = ['valuePipe','jquery','jqueryui', 'backtotop', 'popper', 'bootstrap','summernote'];
		type.mid_lib = [['valuePipe', 'libFilterMid'],['','']];												//middle
		type.bot_lib = [['valuePipe', 'libFilterBot','jquery','bAjax'],['','','$','bx']];					//bottom
		
	}else if(location.pathname.indexOf('/index')>-1){
		
	}
	
	return type;
};