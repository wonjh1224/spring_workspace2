
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">
a {
	text-decoration: none;
}
</style>

<jsp:include page="../layout/header.jsp"></jsp:include>
<br>
<!-- 검색라인 -->
<div style="width: 1200px; margin: 0 auto;">
	<form action="/board/list" method="get">
		<div class="input-group mb-3">

			<div style="width: 400px; border-radius: 5px; margin-right: 20px;" >
				<select name="type" class="form-select" id="inputGroupSelect02">
					<option ${ph.pgvo.type == null ? 'selected' : '' }>Choose..</option>
					<option value="t" ${ph.pgvo.type == 't' ? 'selected' : '' }>제목</option>
					<option value="w" ${ph.pgvo.type == 'w' ? 'selected' : '' }>작성자</option>
					<option value="c" ${ph.pgvo.type == 'c' ? 'selected' : '' }>내용</option>
					<option value="tc" ${ph.pgvo.type == 'tc' ? 'selected' : '' }>제목&내용</option>
					<option value="tw" ${ph.pgvo.type == 'tw' ? 'selected' : '' }>제목&작성자</option>
					<option value="wc" ${ph.pgvo.type == 'wc' ? 'selected' : '' }>작성자&내용</option>
					<option value="twc" ${ph.pgvo.type == 'twc' ? 'selected' : '' }>전체</option>
				</select>
			</div>

			<input type="text" class="form-control me-2" name="keyword"
				value="${ph.pgvo.keyword }" placeholder="Search.."
				style="border-radius: 5px;"> <input type="hidden"
				name="pageNo" value="1"> <input type="hidden" name="qty"
				value="10">
			<button class="btn btn-secondary" type="submit">
				검색 <span
					class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
					${ph.totalCount } <span class="visually-hidden">unread messages</span>
				</span>
			</button>

		</div>
	</form>
</div>


<table class="table">
	<thead>
		<tr>
			<th scope="col">#</th>
			<th scope="col">제목</th>
			<th scope="col">작성자</th>
			<th scope="col">댓글수</th>
			<th scope="col">파일수</th>
			<th scope="col">조회수</th>
			<th scope="col">등록일</th>
		</tr>
	</thead>
	<c:forEach items="${list }" var="bvo">
		<tr>
			<th scope="row">${bvo.bno }</th>
			<td><a href="/board/detail/${bvo.bno }">${bvo.title } </a></td>
			<td>${bvo.writer }</td>
			<td>${bvo.cmtQty }</td>
			<td>${bvo.hasFile }</td>
			<td>${bvo.readCount }</td>
			<td>${bvo.regAt }</td>
		</tr>
	</c:forEach>
	</tbody>
</table>


<!-- 페이지네이션 -->
<div style="margin: 0 auto; width: 700px;">
	<nav aria-label="Page navigation example">
		<ul class="pagination">

			<!-- 이전 -->
			<li class="page-item ${(ph.prev eq false)? 'disabled' : ''}"><a
				class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>

			<!-- 페이지 번호 -->
			<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
				<li class="page-item ${ph.pgvo.pageNo == i ? 'active' : '' }"><a
					class="page-link" href="/board/list?pageNo=${i }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">
					${i }</a></li>
			</c:forEach>

			<!-- 다음 -->
			<li class="page-item ${(ph.next eq false)? 'disabled' : ''}"><a
				class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>

			<li class="page-item"><a class="page-link" href="/board/list">전체보기</a></li>

		</ul>
	</nav>
</div>

<script type="text/javascript">
	const isDel = `<c:out value = "${isDel}"/>`;
	if (isDel == 1) {
		alert("삭제 성공");
	}
</script>

<jsp:include page="../layout/footer.jsp"></jsp:include>