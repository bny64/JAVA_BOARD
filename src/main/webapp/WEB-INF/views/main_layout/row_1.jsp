<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper row1">
  <header id="header" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <div id="logo" class="one_half first">
      <h1 class="logoname"><a href="index.html"><span>Suro</span>gou</a></h1>
    </div>
    <div class="one_half">
      <ul class="nospace clear">
        <li class="one_half first">
          <div class="block clear"><i class="fas fa-phone"></i> <span><strong class="block">Call Us:</strong> +00 (123) 456 7890</span> </div>
        </li>
        <li class="one_half">
          <div class="block clear"><i class="far fa-clock"></i> <span><strong class="block"> Mon. - Sat.:</strong> 08.00am - 18.00pm</span> </div>
        </li>
      </ul>
    </div>
    <c:if test="${userInfo != null }">
   	<div class="fltRt">
	   	<ul class="nospace clear">
	   		<li>
	   			<span style="color: darkcyan; font-size: large;">${userInfo.name }</span>님 환영합니다.
	   		</li>
	   	</ul>
	</div>	
    </c:if>    
    <!-- ################################################################################################ -->
  </header>
  <nav id="mainav" class="hoc clear mtm20"> 
    <!-- ################################################################################################ -->
    <ul class="clear">
      <li class="active"><a href="index.html">Home</a></li>
      <li><a class="drop" href="#">Pages</a>
        <ul>
          <li><a href="/board/boardList.do">Gallery</a></li>
          <li><a href="pages/full-width.html">Full Width</a></li>
          <li><a href="pages/sidebar-left.html">Sidebar Left</a></li>
          <li><a href="pages/sidebar-right.html">Sidebar Right</a></li>
          <li><a href="pages/basic-grid.html">Basic Grid</a></li>
          <li><a href="pages/font-icons.html">Font Icons</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">Dropdown</a>
        <ul>
          <li><a href="#">Level 2</a></li>
          <li><a class="drop" href="#">Level 2 + Drop</a>
            <ul>
              <li><a href="#">Level 3</a></li>
              <li><a href="#">Level 3</a></li>
              <li><a href="#">Level 3</a></li>
            </ul>
          </li>
          <li><a href="#">Level 2</a></li>
        </ul>
      </li>
      <li><a href="#">Link Text</a></li>
      <li><a href="#">Link Text</a></li>
      <li><a href="#">Link Text</a></li>
      <li><a href="#">Long Link Text</a></li>      
    </ul>    
    <!-- ################################################################################################ -->
  </nav>  
</div>