/**
 * static value
 * [전역 값]
 * shared values that's defined by define of requirejs in the js files
 * [requirejs의 define으로 정의된 모든 공유 값] 
 * */
define([], function(){
	
	const globalVal = {};
	
	//middle file path[2단계 파일 경로]
	const getMidPath = location.pathname.substring(0, location.pathname.indexOf('/', 1));
	globalVal.middle_path = getMidPath + getMidPath + '.js';
	
	//bottom file path[3단계 파일 경로]
	globalVal.bottom_path = location.pathname.replace('.do', '.js');
	
	//----------예외----------
	if(globalVal.bottom_path.indexOf('loginCheck')){
		globalVal.bottom_path = globalVal.bottom_path.replace('loginCheck', 'login');
	}
	//----------예외----------		
	
	return {
		getGlobalVal:function(){
			return globalVal;
		}
	}	
});