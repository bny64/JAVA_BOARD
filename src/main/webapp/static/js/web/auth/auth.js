define([], function(){
	
	'use strict';
	
	function Module(){

		const pandora = new arguments[1](arguments);
		
		//valuePipe
		let vp = arguments[0].getGlobalVal();
		
		//----------FUNCTION SCOPE----------
		const msg = document.querySelector('#msg');
		
		if(msg !== null && msg.value !== null && msg.value !== '') alert(msg);
		//----------FUNCTION SCOPE---------
		
		//bottom definition[3단계 definition]
		const func_bot = arguments[arguments.length-1];
		
		requirejs(lsList.bot_lib[0], func_bot);		
	};
	
	return Module;
});