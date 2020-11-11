<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <% Users temporary = (Users) session.getAttribute("temporary"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>変更内容の確認</title>
</head>
<body>

<h1>内容確認</h1>


アイコン：<br>
<% if(temporary.getImg() == null){%>
	なし
<%	}else{%>
<img src="<%= temporary.getImg() %>" alt="画像" width="200" height="200">
<%} %>
<br>
プロフィール文:<br>
<%= temporary.getText() %>

<p>以上の内容で投稿してよろしいですか？</p>

<a href="/gotoFukuoka/Config">修正</a>
<a href="/gotoFukuoka/ComfirmUsers" target="_parent">投稿</a>

<br>

<a href="/gotoFukuoka/contents.jsp">TOPに戻る</a>

</body>
</html>