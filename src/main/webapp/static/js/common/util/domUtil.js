//함수 형식
(function(window){
	
	const domUtil = function(){};
	
	domUtil.prototype.addClass = function(element, clsName){
		if(element.classList)
			element.classList.add(clsName);
		else
			element.className += ' ' + clsName;
	};
	
	domUtil.prototype.removeClass = function(element, clsName){
		if(element.classList)
			element.classList.remove(clsName);
		else
			element.className = element.className.replace(new RegExp('(^|\\b)' + clsName.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
	};
	
	domUtil.prototype.hasClass = function(element, clsName){
		
		let result = false;
		
		if(element.classList){
			if(element.classList.contains(clsName)) result = true;
			else result = false;
		}else{
			if(new RegExp('(^| )' + clsName + '( |$)', 'gi').test(element.className)) result = true;
			else result = false;
		}
		
		return result;
	}
	
	window.domUtil = new domUtil();
	
})(window);