<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="../layout/header.jsp"></jsp:include>

<style type="text/css">
a {
	text-decoration: none;
}
</style>

<form action="/board/modify" method="post" enctype="multipart/form-data">
	<div class="container-md">
		<h2>게시판</h2>
		<br>
		<c:set value="${BoardVO }" var="bvo" />
		<c:set value="${BoardDTO.bvo }" var="bvo" />


		<div class="mb-3">
			<label for="bno" class="form-label">bno</label> <input type="text"
				name="bno" class="form-control" id="title" placeholder="Title"
				value="${bvo.bno }" readonly="readonly" style="background-color: gray;">
		</div>
		<div class="mb-3">
			<label for="title" class="form-label">제목</label> <input type="text"
				name="title" class="form-control" id="title" placeholder="Title"
				value="${bvo.title }">
		</div>

		<div class="mb-3">
			<label for="writer" class="form-label">작성자</label> <input type="text"
				name="writer" class="form-control" id="writer" placeholder="Writer"
				value="${bvo.writer }" readonly="readonly" style="background-color: gray;">
		</div>
		<div class="mb-3">
			<label for="regAt" class="form-label">등록일</label>
			<input type="text" name="regAt" class="form-control" id="regAt"
				placeholder="Wirter" value="${bvo.regAt }" readonly="readonly" style="background-color: gray;">
		</div>
		<div class="mb-3">
			<label for="readCount" class="form-label">조회수</label>
			<input type="text" name="readCount" class="form-control" id="readCount"
				placeholder="Wirter" value=" ${bvo.readCount }" readonly="readonly" style="background-color: gray;">
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">내용</label>
			<textarea class="form-control" name="content" id="content" rows="3">${bvo.content }</textarea>
		</div>
		
		<!-- 파일 -->
		<c:set value="${BoardDTO.flist }" var="flist" />
		
		<div class="mb-3">
		<label for="content" class="form-label">파일</label>
		<ul class="list-group list-group-flush">
			<c:forEach items="${flist }" var="fvo">
				<li class="list-group-item">
					<c:choose>
					
						<c:when test="${fvo.fileType == 1 }">
							<div>
								<img alt="" src="/upload/${fvo.saveDir }/${fvo.uuid}_th_${fvo.fileName}">
							</div>
						</c:when>
						
						<c:otherwise>
							<div>
								<!-- 일반 파일 표시할 아이콘 -->
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-earmark-arrow-down" viewBox="0 0 16 16">
  <path d="M8.5 6.5a.5.5 0 0 0-1 0v3.793L6.354 9.146a.5.5 0 1 0-.708.708l2 2a.5.5 0 0 0 .708 0l2-2a.5.5 0 0 0-.708-.708L8.5 10.293V6.5z"/>
  <path d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2zM9.5 3A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5v2z"/>
</svg>
							</div>
						</c:otherwise>
						
					</c:choose>
						<div class="ms-2 me-auto">
							<div class="fw-bold">${fvo.fileName }</div>
							<span class="badge text-bg-dark">${Math.round(fvo.fileSize/1024.0)}KB</span>
							<button type="button" data-uuid="${fvo.uuid }" class="btn btn-outline-danger file-x">x</button>
						</div>
				</li>			
			</c:forEach>
		</ul>
</div>

		<!-- 파일 추가 등록 라인 -->
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
		
		<br>
		<hr>
		
		<a href="/board/list">
			<button type="button" class="btn btn-secondary">List</button>
		</a>

		<button type="submit" class="btn btn-success">modify</button>

		<a href="/board/remove/${bvo.bno }">
			<button type="button" class="btn btn-danger">remove</button>
		</a>
		
		

	</div>
</form>


<script src="/resources/js/boardModify.js"></script> 

<script src="/resources/js/boardRegister.js"></script> 






<jsp:include page="../layout/footer.jsp"></jsp:include>