requirejs.config({
	/**
	 * * [ paths 설정 ] * path 는 baseUrl 아래에서 직접적으로 찾을 수 없는 모듈명들을 위해 경로를 매핑해주는
	 * 속성입니다. * "/"로 시작하거나 "http" 등으로 시작하지 않으면, 기본적으로는 baseUrl 에 상대적으로 설정하게
	 * 됩니다. * 그리고 아래 exam, jQ 와 같이 별칭(alias)을 설정하여 사용하고 또한 스크립트 파일(모듈)의 .js
	 * 확장자는 붙여주지 않습니다. * 위와 같은 형태로 설정한 뒤에, define 에서 "exam/module" 로 불러오게
	 * 되면, * 스크립트 태그에서는 실제로는 src="aaaa/bbbb/module.js" 로 설정되게 됩니다. * path 는
	 * 또한 아래와 같이 특정 라이브러리 경로 선언을 위해 사용될 수 있는데, * path 매핑 코드는 자동적으로 .js 확장자를
	 * 붙여서 모듈명으로 매핑됩니다. * 정리 : 모듈의 별칭(Alias)을 설정하고 모듈의 단축 경로를 지정합니다.
	 */
	paths:{
		"jsutil" : '/js/common/util/jsUtil',
		"jquery" : '/lib/jquery/jquery.min'
	},
	shim:{
		'jsutil':{
			exports : 'jsutil'
		},
		'jquery':{
			exports : '_jquery'
		}
	}
})

requirejs(['jsutil', 'jquery'], function(a,b){
	debugger;
});
