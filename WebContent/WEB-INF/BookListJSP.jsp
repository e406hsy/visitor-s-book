<%@ page import="server.vo.Book"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta content="text/html" charset="UTF-8">
<title>방명록</title>
</head>
<body>
	<h1>방명록</h1>
	<jsp:useBean id="books" scope="request" class="java.util.ArrayList"
		type="java.util.ArrayList<server.vo.Book>"></jsp:useBean>
	<%
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		for (Book book : books) {
	%>
	<div id="<%=book.getId()%>">
		<p class="email">
			이메일 :
			<%=book.getEmail()%>
		</p>
		<p class="gen_time">
			작성시간 :
			<%=format1.format(book.getGen_time())%></p>
		<%
			if (book.getChange_time() != null) {
		%>
		<p class="mod_time">
			수정시간 :
			<%=format1.format(book.getChange_time())%></p>
		<%
			}
		%>
		<p class="content">
			본문내용 :
			<%=book.getContent()%></p>
		<button onclick="modify(<%=book.getId()%>)">수정</button>
	</div>
	<br>
	<%
		}
	%>
	<h1>방명록 작성하기</h1>
	<form id="addBook" action="saver" method="post">
		<p>이메일 :</p>
		<input type="email" name="email" style="width: 500px">
		<p>비밀번호 :</p>
		<input type="password" name="password" style="width: 500px">
		<p>본문내용 :</p>
		<input type="text" name="content" style="width: 500px; height: 300px"><br>
		<input type="submit" value="등록">
	</form>
</body>
<script type="text/javascript">
	function modify(id) {
		var div = document.getElementById(id);
		var email = div.getElementsByClassName("email")[0];
		var gen_time = div.getElementsByClassName("gen_time")[0];
		div.innerHTML = email.outerHTML
				+ gen_time.outerHTML
				+ "<form id="+id+" action=\"modifyDB\" method=\"post\">"
				+ "<p>본문내용 :</p><input type=\"text\" name=\"content\" style=\"width:500px; height:300px\"><br>"
				+ "<p>비밀번호</p><input type=\"password\" name=\"password\" style=\"width:500px\">"
				+ "<button onclick=\"modifier("+id+")\">수정</button></form>";
	}
	function modifier(id){
		var div = document.getElementById(id);
		var form = document.getElementsByTagName("form")[0];
		var input = document.createElement('input');//prepare a new input DOM element
	    input.setAttribute('name', "id");//set the param name
	    input.setAttribute('value', id);//set the value
	    input.setAttribute('type', "text");//set the type, like "hidden" or other
	    form.appendChild(input);
	}
</script>
</html>