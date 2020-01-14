define([], function(){		
	return class libFilter_bot{
		constructor(){			
			for(let i=1; i<arguments[0].length; i++){
				let name = lsList.bot_lib[1][i];
				this[name] = arguments[0][i];
			}
		}		
	};
});