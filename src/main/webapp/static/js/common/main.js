require.config(requireConfig);

//js 전역 파일 경로
const jsFilePath = '/js/web';

//js 파일 경로
const path = location.pathname.replace('.do', '.js');

const lsList = checkLoadJsLib();

//전역 함수 추가(순서 중요)
lsList.g_lib.push('/js/web/globalSvc.js');
lsList.g_lib.push(jsFilePath + path);

window.onload = function(){
	//비동기 호출
	requirejs(lsList.g_lib, function(){
		
		//url별로 라이브러리 달리 호출하게 설정
		jQuery.noConflict(true);
		
		const func = arguments[arguments.length-1];
				
		//로컬		
		requirejs(lsList.l_lib, func);
		
	});
}

//로드해야할 js 라이브러리 확인
function checkLoadJsLib(){
	
	const type = {
		g_lib : null,		//전역 라이브러리
		l_lib : null, 		//로컬 라이브러리
		l_lib_naming : []   //로컬 라이브러리 이름
	};
		
	if(location.pathname.indexOf('/auth')>-1){
		
		type.g_lib = ['jquery','jqueryui','animsition','popper','bootstrap','select2','moment','daterangepicker','countdowntime','setDatepickerKor', 'bAjax',
			'domUtil','jsUtil','libFilterUtil'];
		type.l_lib = 		['libFilterUtil', 						'jquery', 	'bAjax',	'domUtil' ];
		type.l_lib_naming = ['type_l_lib를 사용할 변수명 배열 index 1부터', 	'$', 		'bx', 		'du'];
		
	}else{
		
	}
	
	return type;
};
