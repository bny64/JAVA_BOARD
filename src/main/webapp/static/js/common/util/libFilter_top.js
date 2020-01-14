define([], function(){		
	return class libFilter_top{
		constructor(){			
			for(let i=2; i<arguments[0].length-1; i++){
				let name = lsList.top_lib[1][i];
				this[name] = arguments[0][i];				
			}
		}		
	};
});