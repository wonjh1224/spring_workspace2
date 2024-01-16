
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<style type="text/css">
a {
	text-decoration: none;
}
</style>

<jsp:include page="../layout/header.jsp"></jsp:include>
<div class="container-md">
<table class="table">
	<thead>
		<tr>
			<th scope="col">email</th>
			<th scope="col">nickName</th>
			<th scope="col">Auth</th>
			<th scope="col">regAt</th>
			<th scope="col">lastLogin</th>
		</tr>
	</thead>
	<c:forEach items="${list }" var="mvo">
		<tr>
			<th scope="row"> <a href="/member/modify/${mvo.email }">${mvo.email }</a></th>
			<td>${mvo.nickName }</td>
			<td>
			<c:forEach items="${mvo.authList }" var="authVO">
				${authVO.auth }
			</c:forEach>
			</td>
			<td>${mvo.regAt }</td>
			<td>${mvo.lastLogin }</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>