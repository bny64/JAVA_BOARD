<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="limiter">
	<div class="container-login100">
		<div class="wrap-login100">
			<div class="login100-form validate-form">
				<span class="login100-form-title p-b-43">
					비밀번호 초기화
				</span>
									
				<div class="wrap-input100 validate-input" data-validate = "ID를 입력해 주세요">
					<input class="input100" type="text" name="id" id="id">
					<span class="focus-input100"></span>
					<span class="label-input100">아이디</span>
				</div>						
				
				<div class="wrap-input100 validate-input" data-validate = "이메일 형식은 ex@abc.xyz와 같이 입력해 주세요">
					<input class="input100" type="email" name="email" id="email">
					<span class="focus-input100"></span>
					<span class="label-input100">이메일</span>
				</div>
													
				<div class="wrap-input100 validate-input" data-validate="이름을 입력해 주세요">
					<input class="input100" type="text" name="name" id="name">
					<span class="focus-input100"></span>
					<span class="label-input100">이름</span>
				</div>
									
				<button class="login100-form-btn mb10" id="resetPasswordBtn">
					비밀번호 초기화
				</button>
				
				<button class="login100-form-btn mb10" id="findEmailBtn">
					이메일 찾기
				</button>
				
				<button class="login100-form-btn" id="loginBtn">
					로그인
				</button>
				
			</div>
			<div class="login100-more" style="background-image: url('/images/login/images/bg-01.jpg');">
			</div>
		</div>
		
	</div>
</div>