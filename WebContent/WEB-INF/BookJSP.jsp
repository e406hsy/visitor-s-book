<?xml version="1.0" encoding="UTF-8"?>
<%@ page import="server.vo.Book"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="book" scope="request" class="server.vo.Book"
		type="server.vo.Book"></jsp:useBean>
<%
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
%>
<book>
<email>${requestScope.book.email}</email>
<gen_time><%=format1.format(book.getGen_time())%></gen_time>
<%
	if (book.getChange_time() != null) {
%>
<change_time><%=format1.format(book.getChange_time())%></change_time>
<% 
	}
%>
<content>${requestScope.book.content}</content>
<id>${requestScope.book.id}</id>
</book>