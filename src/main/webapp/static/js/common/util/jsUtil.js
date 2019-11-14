(function(window){
	
	const jsUtil = function(){};
	
	jsUtil.prototype.browserCheck = function(){
		 
		let browser = navigator.userAgent.toLowerCase();  

		if ( -1 != browser.indexOf('chrome') ) return 'chrome';
		if ( -1 != browser.indexOf('opera') ) return 'opera';
	    if ( -1 != browser.indexOf('sapari') ) return 'sapari';
	    if ( -1 != browser.indexOf('firefox') ) return 'firefox';
	    if ( navigator.appName == "Microsoft Internet Explorer" ) return 'IE10 or below';
	    if ( agent.search( "trident" ) > -1 ) return 'IE11';
	    if ( agent.search( "edge/" ) > -1 ) return 'edge'; 
	    return 'undefined browser';
	    
	};
	
	window.jsUtil = new jsUtil();
	
})(window);