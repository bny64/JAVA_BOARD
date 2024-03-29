<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" value="N" id="checkValIdYn">
<input type="hidden" value="N" id="checkValEmailYn">
<div class="limiter">
	<div class="container-login100">
		<div class="wrap-login100">
			<form class="login100-form validate-form" action="/auth/join.do" method="post" id="joinForm">
				<span class="login100-form-title p-b-43">
					가입하기
				</span>
								
				<div class="wrap-input100 validate-input" data-validate = "ID를 입력해 주세요">
					<input class="input100" type="text" name="id" id="id">
					<span class="focus-input100"></span>
					<span class="label-input100">아이디</span>
				</div>
				<div>
					<button style="float: right;margin-bottom: 10px; border: 1px;" id="chkValId">아이디 중복 체크</button>
				</div>				
				
				<div class="wrap-input100 validate-input" data-validate="비밀번호를 입력해 주세요">
					<input class="input100" type="password" name="password">
					<span class="focus-input100"></span>
					<span class="label-input100">비밀번호</span>
				</div>
				
				<div class="wrap-input100 validate-input" data-validate = "이메일 형식은 ex@abc.xyz와 같이 입력해 주세요">
					<input class="input100" type="email" name="email" id="email">
					<span class="focus-input100"></span>
					<span class="label-input100">이메일</span>
				</div>
				<div>
					<button style="float: right;margin-bottom: 10px; border: 1px;" id="chkValEmail">이메일 중복 체크</button>
				</div>
								
				<div class="wrap-input100 validate-input" data-validate="이름을 입력해 주세요">
					<input class="input100" type="text" name="name">
					<span class="focus-input100"></span>
					<span class="label-input100">이름</span>
				</div>
				
				<div class="wrap-input100 validate-input">
					<input class="input100 datepicker" type="text" name="birth" id="birth">
					<span class="focus-input100"></span>
					<span class="label-input100">생년월일</span>				
				</div>
				
				<div class="wrap-input100 validate-input">
					<input class="input100" type="text" name="phoneNumber">
					<span class="focus-input100"></span>
					<span class="label-input100">휴대폰 번호</span>
				</div>
							
				<div class="wrap-input200 validate-input">
					<textarea class="input100" name="introduce"></textarea>
					<span class="focus-input100"></span>
					<span class="label-input100">자기소개</span>
				</div>
				
				<div class="mb10">
					<span class="mr20">이메일 수신 여부</span>
					<span>
						<span class="mr20"><input type="radio" name="emailYn" checked="checked" value="Y">수신</span>
						<span><input type="radio" name="emailYn" value="N">비수신</span>
					</span>		
				</div>
				
				<div class="container-login100-form-btn mb10">
					<button class="login100-form-btn" id="joinBtn">
						가입
					</button>
				</div>		
				
				<button class="login100-form-btn" id="loginBtn">
					로그인
				</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />		
			</form>

			<div class="login100-more" style="background-image: url('/images/login/images/bg-01.jpg');">
			</div>
		</div>
	</div>
</div>