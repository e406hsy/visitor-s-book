<%@ page import="server.vo.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>

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
<%
SimpleDateFormat format1 = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
ArrayList<Book> books = (ArrayList<Book>)request.getAttribute("books");
for (Book book : books){
%>
<p> 이메일 : <%= book.getEmail() %> </p>
<p> 작성시간 : <%= format1.format(book.getGen_time()) %></p>
<% 
if (book.getChange_time() != null) {
%>
<p>수정시간 : <%=format1.format(book.getChange_time()) %></p>
<% } %>
<p>본문내용 : <%=book.getContent() %></p>
<br>
<% } %>
<h1>방명록 작성하기</h1>
<form action="saver" method="post">
<p>이메일</p><input type="email" name="email" style="width:500px">
<p>비밀번호</p><input type="password" name="password" style="width:500px">
<p>본문</p><input type="text" name="content" style="width:500px; height:300px"><br>
<input type="submit" value="등록"></form>
</body>
</html>