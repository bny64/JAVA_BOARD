<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input type="hidden" value="N" id="checkValIdYn">
<input type="hidden" value="N" id="checkValEmailYn">
<div class="limiter">
	<div class="container-login100">
		<div class="wrap-login100">
			<form class="login100-form validate-form" action="/auth/join.do" method="post" id="joinForm">
				<span class="login100-form-title p-b-43">
					Join to continue 
				</span>
								
				<div class="wrap-input100 validate-input" data-validate = "ID is required">
					<input class="input100" type="text" name="id" id="id">
					<span class="focus-input100"></span>
					<span class="label-input100">Id</span>
				</div>
				<div>
					<button style="float: right;margin-bottom: 10px; border: 1px;" id="chkValId">Id 중복 체크</button>
				</div>				
				
				<div class="wrap-input100 validate-input" data-validate="PASSWORD is required">
					<input class="input100" type="password" name="password">
					<span class="focus-input100"></span>
					<span class="label-input100">Password</span>
				</div>
				
				<div class="wrap-input100 validate-input" data-validate = "Valid EMAIL is required: ex@abc.xyz">
					<input class="input100" type="email" name="email" id="email">
					<span class="focus-input100"></span>
					<span class="label-input100">Email</span>
				</div>
				<div>
					<button style="float: right;margin-bottom: 10px; border: 1px;" id="chkValEmail">Email 중복 체크</button>
				</div>
								
				<div class="wrap-input100 validate-input" data-validate="NAME is required">
					<input class="input100" type="text" name="name">
					<span class="focus-input100"></span>
					<span class="label-input100">Name</span>
				</div>
				
				<div class="wrap-input100 validate-input">
					<input class="input100 datepicker" type="text" name="birth" id="birth">
					<span class="focus-input100"></span>
					<span class="label-input100">Birth</span>				
				</div>
				
				<div class="wrap-input100 validate-input">
					<input class="input100" type="text" name="phoneNumber">
					<span class="focus-input100"></span>
					<span class="label-input100">PhoneNumber</span>
				</div>
							
				<div class="wrap-input200 validate-input">
					<textarea class="input100" name="introduce"></textarea>
					<span class="focus-input100"></span>
					<span class="label-input100">Introduce</span>
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
						JOIN
					</button>
				</div>		
				
				<button class="login100-form-btn" id="loginBtn">
					LOGIN
				</button>
							
			</form>

			<div class="login100-more" style="background-image: url('/images/login/images/bg-01.jpg');">
			</div>
		</div>
	</div>
</div>
<script src="/js/web/auth/join.js"></script>