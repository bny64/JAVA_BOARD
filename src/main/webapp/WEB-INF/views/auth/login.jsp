<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<input type="hidden" value="${msg.msg }" id="msg">
<input type="hidden" value="${msg.msgCode }" id="msgCode">
<div class="limiter">
	<div class="container-login100">
		<div class="wrap-login100">
			<form class="login100-form validate-form" id="loginForm" method="post" action="/auth/loginCheck.do">
				<span class="login100-form-title p-b-43">
					Login to continue
				</span>
				
				<div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
					<input class="input100" type="text" name="email" id="email">
					<span class="focus-input100"></span>
					<span class="label-input100">Email</span>
				</div>
				
				
				<div class="wrap-input100 validate-input" data-validate="Password is required">
					<input class="input100" type="password" name="password" id="password">
					<span class="focus-input100"></span>
					<span class="label-input100">Password</span>
				</div>

				<div class="flex-sb-m w-full p-t-3 p-b-32">
					<div class="contact100-form-checkbox">
						<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
						<label class="label-checkbox100" for="ckb1">
							Remember me
						</label>
					</div>					
					<div>
						<a href="#" class="txt1">
							Forgot Password?
						</a>
					</div>
				</div>
				<p>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
				<div class="container-login100-form-btn">
					<button class="login100-form-btn">
						Login
					</button>
				</div>
				
				<div class="text-center p-t-46 p-b-20">
					<span class="txt2">
						<a href="/auth/join.do">or sign up using</a>
					</span>
				</div>

				<div class="login100-form-social flex-c-m">
					<a href="#" class="login100-form-social-item flex-c-m bg1 m-r-5">
						<i class="fa fa-facebook-f" aria-hidden="true"></i>
					</a>

					<a href="#" class="login100-form-social-item flex-c-m bg2 m-r-5">
						<i class="fa fa-twitter" aria-hidden="true"></i>
					</a>
				</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>

			<div class="login100-more" style="background-image: url('/images/login/images/bg-01.jpg');">
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="/js/web/auth/login.js">
</script>