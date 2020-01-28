<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div class="wrapper row3 board">
	<main class="hoc container clear"> 
		<form action="/board/viewBoard.do" method="post">
			<div class="bdArea">
				<input type="hidden" value="${listNo }" id="listNo" name="listNo">
				<label class="title editor" for="password">비밀번호</label>
				<input style="margin : 0 auto;" id="password" name="password" type="text" placeholder="비밀번호를 입력하세요.">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</div>
		</form>
	</main>
</div>