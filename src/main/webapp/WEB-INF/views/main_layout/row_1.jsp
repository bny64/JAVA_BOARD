<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="wrapper row1">	
  <header id="header" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <div id="logo" class="one_half first">
      <h1 class="logoname"><a href="/index.do"><span>JAVA</span>BOARD</a></h1>
    </div>
    <!-- <div class="one_half">
      <ul class="nospace clear">
        <li class="one_half first">
          <div class="block clear"><i class="fas fa-phone"></i> <span><strong class="block">Call Us:</strong> +00 (123) 456 7890</span> </div>
        </li>
        <li class="one_half">
          <div class="block clear"><i class="far fa-clock"></i> <span><strong class="block"> Mon. - Sat.:</strong> 08.00am - 18.00pm</span> </div>
        </li>
      </ul>
    </div> -->
        
    <sec:authorize access="isAuthenticated()">
    	<sec:authentication property="principal" var="userInfo"/>
    	<div class="fltRt">
		   	<ul class="nospace clear">
		   		<li>
		   			<span style="color: darkcyan; font-size: large;">${userInfo.name }</span>님 환영합니다.
		   		</li>
		   	</ul>
	   	</div>
    </sec:authorize>      
    <!-- ################################################################################################ -->
  </header>
  <nav id="mainav" class="hoc clear mtm20"> 
    <!-- ################################################################################################ -->    
    <ul class="clear">    	
    	<li class=""><a href="${menuTree.url }.do">${menuTree.name }</a></li>
    	<c:forEach items="${menuTree.children }" var="twoDepth">
			<li><a class="drop" href="${twoDepth.url }.do">${twoDepth.name }</a>
				<ul>
				<c:forEach items="${twoDepth.children }" var="threeDepth">
					<li><a href="${twoDepth.url }${threeDepth.url}.do">${threeDepth.name }</a>
				</c:forEach>
				</ul>
			</li>    		
    	</c:forEach>    
    </ul>    
    <!-- ################################################################################################ -->
  </nav>  
</div>