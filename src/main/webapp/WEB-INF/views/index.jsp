<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<jsp:include page="layout/header.jsp"></jsp:include>

<div class="container my-3" style="text-align: center;">
	<h3>Spring makes programming Java quicker, easier, and safer for
		everybody. <br>
		Spring’s focus on speed, simplicity, and productivity has
		made it the world’s most popular Java framework.</h3>
		
		
		
</div>

<script>
const isOk = `<c:out value = "${isOk}"/>`;
if(isOk == 1){
	alert("등록 성공");
}
</script>

<jsp:include page="layout/footer.jsp"></jsp:include>
