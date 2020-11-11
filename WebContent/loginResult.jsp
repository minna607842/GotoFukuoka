<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="model.Users" %>
<%
Users loginUser = (Users) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン結果画面</title>
</head>
<body>
<h1>GoToFukuokaログイン</h1>
<% if (loginUser != null){%>
<p>ログインに成功しました</p>
<p>ようこそ<%= loginUser.getUser_id() %>さん</p>
<a href="/gotoFukuoka/Access?pathAction=top" target="_top">TOPへ戻る</a>
<a href = "/gotoFukuoka/myPage.jsp">マイページへ</a>
<% } else{%>
<p>ログインに失敗しました</p>
<a href = "/gotoFukuoka/login.jsp">ログイン画面へ</a>
<a href = "/gotoFukuoka/contents.jsp">トップへ</a>
<% } %>
</body>
</html>