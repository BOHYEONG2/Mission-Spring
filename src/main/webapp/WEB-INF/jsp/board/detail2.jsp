<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#section {
		
	}
</style>
</head>
<body>
	<div id="section" align="center">
	<h2>게시판 상세 페이지</h2>
	
	<table border="1" style="width: 80%">
		<tr>
			<th width="25%">번호</th>
			<td>${ board.no }</td>
		</tr>
		<tr>
			<th width="25%">제목</th>
			<td>${ board.title }</td>
		</tr>
		<tr>
			<th width="25%">글쓴이</th>
			<td>${ board.writer }</td>
		</tr>
		<tr>
			<th width="25%">내용</th>
			<td>${ board.content }</td>
		</tr>
		<tr>
			<th width="25%">조회수</th>
			<td>${ board.viewCnt }</td>
		</tr>
		<tr>
			<th width="25%">등록일</th>
			<td>${ board.regDate }</td>
		</tr>
	</table>
	<button onclick="location.href='${pageContext.request.contextPath}/board'">목 록</button>
	<button onclick="location.href=''">수 정</button>
	<button onclick="location.href=''">삭 제</button>
	</div>
</body>
</html>













