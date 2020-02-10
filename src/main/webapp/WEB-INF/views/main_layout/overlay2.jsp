<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper bgded overlay" style="background-image:url('/images/main/demo/backgrounds/01.png');">
  <div id="breadcrumb" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <h6 class="heading">Gallery</h6>
    <ul>
    	<c:forEach items="${menuList }" var="menuList">
    		<li><a href="javascript:void(0);">${menuList.name }</a></li>
    	</c:forEach>
    </ul>    
    <!-- ################################################################################################ -->
  </div>
</div>