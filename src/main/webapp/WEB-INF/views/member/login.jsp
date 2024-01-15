<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<jsp:include page="../layout/header.jsp"></jsp:include>

<style type="text/css">
a{
text-decoration: none;
}
</style>

	<div class="container-md">
	
		<h2>로그인 페이지 (login Page)</h2>
		
			<form action="/member/login" method="post">

				<div class="mb-3">
				<label for="email" class="form-label">아이디</label> 
				<input type="text" name="email" class="form-control" id="email" placeholder="ID">
				</div>
	
				<div class="mb-3">
				<label for="pwd" class="form-label">비밀번호</label> 
				<input type="password" name="pwd" class="form-control" id="pwd" placeholder="PW">
				</div>
			<c:if test="${not empty param.errorMsg }">
				<div class="mb-3 text-danger">
					<c:choose>
						<c:when test="${param.errorMsg eq 'Bad credentials' }">
							<c:set value="이메일 & 비밀번호가 일치하지 않습니다" var="errText"></c:set>
						</c:when>
						<c:otherwise>
							<c:set value="관리자에게 문의해주세요." var="errText"></c:set>
						</c:otherwise>
					</c:choose>
					${errText }
				</div>
			</c:if>
				
				<button type="submit" class="btn btn-secondary">로그인</button>
	
			</form>
			

	</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>