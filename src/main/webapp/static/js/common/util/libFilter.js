define([], function(){
	return class libFilter{
		constructor(){
						
			const scopeType = arguments[0];
			const libList = arguments[1];
			
			if(scopeType==='top' || scopeType==='mid'){
			
				for(let i=2; i<libList.length ; i++){
					
					let name = lsList[scopeType+'_lib'][1][i];
					this[name] = libList[i];
					
				}
				
			}else if(scopeType==='bot'){
				
				for(let i=2; i<libList.length; i++){
					
					let name = lsList[scopeType+'_lib'][1][i];
					this[name] = libList[i];
					
				}
			}			
			
		}
	}
})