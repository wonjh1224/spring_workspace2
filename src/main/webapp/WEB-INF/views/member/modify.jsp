<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<jsp:include page="../layout/header.jsp"></jsp:include>

<!-- email pwd nick_name -->
<div class="container-md">
	<h2>Modify</h2>
	<br>

	<form action="/member/modify" method="post">

		<div class="mb-3">
			<label for="email" class="form-label">email</label> 
			<input type="text" name="email" class="form-control" id="email" value="${mvo.email }">
		</div>
		
		<div class="mb-3">
			<label for="pwd" class="form-label">pwd</label> 
			<input type="password" name="pwd" class="form-control" id="pwd" placeholder="********">
		</div>
		
		<div class="mb-3">
			<label for="nickName name" class="form-label">nickName</label> 
			<input type="text" name="nickName" class="form-control" id="nickName" value="${mvo.nickName }">
		</div>
		<!-- 해당 멤버의 권한을 출력 (2개일수도 있음) -->
		
		<button type="submit" class="btn btn-secondary" id="regBtn">Register</button>

	</form>
</div>


<jsp:include page="../layout/footer.jsp"></jsp:include>
