<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper row0">
  <div id="topbar" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <div class="fl_left">
      <ul class="nospace">
        <li><a href="index.html"><i class="fas fa-home fa-lg"></i></a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Contact</a></li>
        <c:choose>
        	<c:when test="${userInfo ne null }">
        	<li><a id="logout" href="javascript:void(0);">Logout</a></li>
        	</c:when>
        	<c:otherwise>
        	<li><a href="/auth/login.do">Login</a></li>
        	</c:otherwise>
        </c:choose>
        
        <li><a href="#">Register</a></li>
      </ul>
    </div>
    <div class="fl_right">
      <ul class="nospace">
        <li><i class="fas fa-phone rgtspace-5"></i> +00 (123) 456 7890</li>
        <li><i class="fas fa-envelope rgtspace-5"></i> info@domain.com</li>
      </ul>
    </div>
    <!-- ################################################################################################ -->
  </div>
</div>
<script>
(function(){

	window.onload = function(){
		const browser = jsUtil.browserCheck();
		if(browser.indexOf('ie') > -1 || browser.indexOf('safari') > -1) alert('지원하지 않는 브라우저 입니다.'); 
	};
	
	const logout = document.querySelector('#logout');

	if(logout){
		logout.addEventListener('click', function(){
			bAjax.ajaxSend({
				url : '/auth/logout.do',
				callback:function(){ location.href = '/index.do'; }		
			})
		});
	}
})();
</script>