define([], function(){		
	return class libFilter_mid{
		constructor(){			
			for(let i=2; i<arguments[0].length-1; i++){
				let name = lsList.mid_lib[1][i];
				this[name] = arguments[0][i];
			}
		}		
	};
});