<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="../layout/header.jsp"></jsp:include>

<style type="text/css">
a {
	text-decoration: none;
}

input {
	background-color: gray;
}
</style>

<div class="container-md">
	<c:set value="${BoardVO }" var="bvo" />
	<c:set value="${CommentVO }" var="cvo" />
	<h2>게시판</h2>
	<br>

	<!-- 파일 -->
	<c:set value="${BoardDTO.bvo }" var="bvo"></c:set>


	<div class="mb-3">
		<label for="title" class="form-label">bno</label> <input type="text"
			name="title" class="form-control" id="title" placeholder="Title"
			value="${bvo.bno }" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="title" class="form-label">제목</label> <input type="text"
			name="title" class="form-control" id="title" placeholder="Title"
			value="${bvo.title }" readonly="readonly">
	</div>

	<div class="mb-3">
		<label for="writer" class="form-label">작성자</label> <input type="text"
			name="writer" class="form-control" id="writer" placeholder="Wirter"
			value="${bvo.writer }" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="content" class="form-label">등록일 </label> <input
			type="text" name="writer" class="form-control" id="writer"
			placeholder="Wirter" value="${bvo.regAt }" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="readCount" class="form-label">조회수 </label> <input
			type="text" name="readCount" class="form-control" id="readCount"
			placeholder="Wirter" value="${bvo.readCount }" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="content" class="form-label">내용</label>
		<textarea class="form-control" name="content" id="content" rows="3"
			readonly="readonly">${bvo.content }</textarea>
	</div>

	<!-- 파일 표시 라인 -->
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
							<div class="fw-bold"> <a href="/upload/${fvo.saveDir }/${fvo.uuid}_${fvo.fileName}" download>${fvo.fileName }</a> </div>
							<span class="badge text-bg-dark">${Math.round(fvo.fileSize/1024.0)}KB</span>
						</div>
				</li>			
			</c:forEach>
		</ul>
</div>



	<!-- 댓글 등록 라인 -->
	<div class="input-group mb-3">
		<span class="input-group-text" id="cmtWriter">test</span> <input
			type="text" id="cmtText" placeholder="댓글 작성.." class="form-control"
			aria-label="Recipient's username" aria-describedby="button-addon2">
		<button type="button" class="btn btn-outline-success" id="cmtAddBtn">댓글
			등록</button>
	</div>

	<!-- 댓글 표시 라인 -->
	<div class="accordion" id="accordionExample">
		<div class="accordion-item">
			<h2 class="accordion-header">
				<button class="accordion-button" type="button"
					data-bs-toggle="collapse" data-bs-target="#collapseOne"
					aria-expanded="true" aria-controls="collapseOne">cno,
					writer, reg_date</button>
			</h2>
			<div id="collapseOne" class="accordion-collapse collapse show"
				data-bs-parent="#accordionExample">
				<div class="accordion-body">
					<strong>댓글</strong>
				</div>
			</div>
		</div>
	</div>

	<!-- 댓글 더보기 버튼 -->
	<div>
		<button type="button" id="moreBtn" data-page="1"
			class="btn btn-secondary" style="visibility: hidden">MORE+</button>
	</div>

	<!-- Modal창 -->
	<div class="modal" id="myModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">writer</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="input-group mb-3">
						<input type="text" class="form-control" id="cmtTextMod">
						<button type="button" class="btn btn-primary" id="cmtModBtn">Post</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<br> <a href="/board/list">
		<button type="button" class="btn btn-secondary">List</button>
	</a> <a href="/board/modify/${bvo.bno }">
		<button type="button" class="btn btn-success">수정 페이지로 이동</button>
	</a>
</div>

<script>
	let bnoVal = `<c:out value="${BoardDTO.bvo.bno}"/>`;
	console.log(bnoVal);
</script>

<script type="text/javascript" src="/resources/js/boardComment.js"></script>
<script>
	spreadCommentList(bnoVal);
</script>

<script type="text/javascript">
	const isMod = `<c:out value = "${isMod}"/>`;
	if (isMod == 1) {
		alert("수정 성공")
	}
</script>









<jsp:include page="../layout/footer.jsp"></jsp:include>