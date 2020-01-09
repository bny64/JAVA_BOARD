require.config(requireConfig);
//js 전역 파일 경로
const jsFilePath = '/js/web';
//js 파일 경로
const path = location.pathname.replace('.do', '.js');
const loadJsList = checkLoadJsLib();

loadJsList.g_lib.push(jsFilePath + path);

//비동기 호출
requirejs(loadJsList.g_lib, function(){
	//url별로 라이브러리 달리 호출하게 설정
	
	const func = arguments[arguments.length-1];
	
	//전역
	//requirejs();
	//로컬
	requirejs(loadJsList.l_lib, func);
	
});

//로드해야할 js 라이브러리 확인
function checkLoadJsLib(){
	
	const type = {
		g_lib : null,	//전역 라이브러리
		l_lib : null 	//로컬 라이브러리
	};
	
	if(location.pathname.indexOf('/auth')>-1){
		type.g_lib = ['jquery','jqueryui','animsition','popper','bootstrap','select2','moment','daterangepicker','countdowntime','setDatepickerKor', 'bAjax',
			'domUtil','jsUtil'];
		type.l_lib = ['bAjax','domUtil'];
	}else{
		
	}
		
	return type;
};
