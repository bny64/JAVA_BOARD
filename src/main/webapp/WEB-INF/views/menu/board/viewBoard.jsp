<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" value="${board.listNo }" id="listNo"> 
<div class="wrapper row3 board">
	<main class="hoc container clear"> 
		<form id="registBoardForm" method="post" enctype="multipart/form-data">
			<div class="bdArea">
				<label class="title editor" for="title">제목</label>
				<div class="bdtitle">${board.title }</div>
				<label class="editor" for="contents">내용</label>
				<div class="bdcontents">${board.contents }</div>
			</div>
			
			<div class="mt10 bdArea">
				<div class="bdOth">
					<span class="span" style="color: blueviolet">작성자</span>
					<span>${board.name }</span>
				</div>
				<div class="bdOth">
					<span class="span" style="color: blueviolet">작성자 ID</span>
					<span>${board.id }</span>
				</div>
				<div class="bdOth">
					<span class="span" style="color: blueviolet">작성 날짜</span>
					<span>${board.createdAt }</span>
				</div>				
			</div>
			<c:if test="${board.equalUserYn eq 'Y' }">
				<div class="pt5">
					<button class="btn2" id="modifyBtn" type="button">수정</button>
				</div>
				<div class="pt5">
					<button class="btn2" id="deleteBtn" type="button">삭제</button>
				</div>
			</c:if>
			<div class="pt5">
				<button class="btn2" id="backBtn" type="button">돌아가기</button>
			</div>
		</form>
	</main>
</div>