<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="wrapper row0">
  <div id="topbar" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <div class="fl_left">
      <ul class="nospace">
        <li><a href="index.html"><i class="fas fa-home fa-lg"></i></a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Contact</a></li>
        <sec:authorize access="isAuthenticated()">
        	<li><a id="logout" href="javascript:void(0);">Logout</a></li>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
        	<li><a href="/auth/login.do">Login</a></li>
        	<li><a href="/auth/join.do">Register</a></li>
        </sec:authorize>
      </ul>
    </div>
    <div class="fl_right">
      <ul class="nospace">
        <li><i class="fas fa-phone rgtspace-5"></i> +00 (000) 000 0000</li>
        <li><i class="fas fa-envelope rgtspace-5"></i> java@board.com</li>
      </ul>
    </div>
    <!-- ################################################################################################ -->
  </div>
</div>