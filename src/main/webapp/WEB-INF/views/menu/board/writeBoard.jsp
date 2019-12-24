<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="wrapper row3 board">
	<main class="hoc container clear"> 
		<div>
			<label class="title editor" for="title">제목</label>
			<input class="title mb10" type="text" id="title">
			<label class="editor" for="content">내용</label>
			<textarea id="content"></textarea>
		</div>
		<div class="mt10">
			<select class="editor">
				<option id="viewYn" value="" selected disabled>글 공개 여부</option>				
				<option value="Y">공개</option>
				<option value="N">비공개</option>
			</select>
		</div>
		<div class="editor radio mt10">
			<span class="span">비밀번호 설정 여부</span>
			<input type="radio" class="" name="passYn" value="passY"/>
		    <label for="passY">설정</label>
		    <input type="radio" class="" name="passYn" value="passN" checked/>
		    <label for="passN">설정 안함</label>		    
		</div>
		<div class="mt10" style="display:none;" id="boardPassDiv">
			<input placeholder="비밀번호" type="password" id="boardPass">
		</div>
		<div class="mt10 pt5">
			<button class="mt30 btn1" id="registBtn">등록</button>
		</div>
	</main>
</div>
<script src="/js/web/board/writeBoard.js"></script>