<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" id="fileStatus" value="L">
<input type="hidden" id="fileOrgNm">
<div class="wrapper row3 board">
	<main class="hoc container clear">
		<form id="modifyBoardForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="listNo" id="listNo" value="${listNo }">
			<div>
				<label class="title editor" for="title">제목</label>
				<input class="title mb10" type="text" id="title" name="title">
				<label class="editor" for="contents">내용</label>
				<textarea id="contents" name="contents"></textarea>
			</div>
			<div class="mt10">
				<select class="editor" id="viewYn" name="viewYn">
					<option value="" disabled>글 공개 여부</option>				
					<option value="Y">공개</option>
					<option value="N">비공개</option>
				</select>
			</div>
			<div class="editor radio2 mt10">
				<span class="span">비밀번호 설정 여부</span>
				<input type="radio" id="passNC" class="" name="passYn" value="NC" checked/>
			    <label for="passNC">기존 비밀번호 사용</label>
				<input type="radio" id="passY" class="" name="passYn" value="Y"/>
			    <label for="passY">설정</label>
			    <input type="radio" id="passN" class="" name="passYn" value="N"/>
			    <label for="passN">설정 안함</label>
			</div>
			<div class="mt10" style="display:none;" id="boardPassDiv">
				<input placeholder="새로운 비밀번호" type="password" id="boardPass" name="boardPass">
			</div>			
			<div class="editor mt10 pdt30" style="text-align: center;">
				<h3 style="margin: 10px;" id="fileNm">없음</h3>
				<button id="fileBtn" type="button" style=" margin: 0 auto;">파일 추가</button>
				<button class="mt10" id="fileDelRvBtn" type="button" style="margin: 1% 0 0 45%; display:none;">파일 삭제 취소</button>
				<p id="fileWarn" style=" color: red; display : none;">파일을 변경하려면 파일 삭제 후 변경해야 합니다.</p>
			</div>
			<div class="mt10" style="display: none;" id="fileDiv">
				<input type="file" id="boardFile" accept=".jpg, .jpeg, .png, .gif" name="boardFile">
			</div>			
			<div class="mt10 pt5">
				<button class="mt30 btn1" id="registBtn" type="button">수정</button>
			</div>					
		</form>
	</main>
</div>