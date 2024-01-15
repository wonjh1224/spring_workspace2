<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<jsp:include page="../layout/header.jsp"></jsp:include>

<style type="text/css">
a {
	text-decoration: none;
}
</style>

<br>

<div class="container-md">
	<h2>Register</h2>
	<br>

	<form action="/board/register" method="post" enctype="multipart/form-data">

		<div class="mb-3">
			<label for="title" class="form-label">제목</label> <input
				type="text" name="title" class="form-control" id="title"
				placeholder="title">
		</div>

   	
        <sec:authentication property="principal.mvo.email" var="authEmail"/>
		<div class="mb-3">
			<label for="writer" class="form-label">작성자</label> <input
				type="text" name="writer" class="form-control" id="writer"
				value="${authEmail }"
				placeholder="writer">
		</div>


		<div class="mb-3">
			<label for="content" class="form-label">내용</label>
			<textarea class="form-control" name="content" id="content" rows="3"
				placeholder="content"></textarea>
		</div>

		<!-- file 입력 라인 추가 -->
		<div class="mb-3">
			<label for="file" class="form-label">files..</label> <input
				type="file" name="files" class="form-control" id="files"
				multiple="multiple" style="display: none;"><br>
			<button type="button" class="btn btn-success" id="trigger">FileUpload</button>
		</div>
		<!-- 파일 목록 표시라인 -->
		<div class="mb-3" id="fileZone">
			
		</div>

		<button type="submit" class="btn btn-secondary" id="regBtn">Register</button>
                        
	</form>
</div>

<script src="/resources/js/boardRegister.js"></script> 

<jsp:include page="../layout/footer.jsp"></jsp:include>