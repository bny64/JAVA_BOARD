define([], function(){		
	return class libFilterUtil{
		constructor(){			
			for(let i=1; i<arguments[0].length; i++){
				this[lsList.l_lib_naming[i]] = arguments[0][i];
			}
		}		
	};
});