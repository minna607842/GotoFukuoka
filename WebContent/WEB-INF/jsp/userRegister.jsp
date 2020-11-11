<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="model.Users" %>
<%
//リクエストスコープに保存されたメッセージを取得
String message = (String) request.getAttribute("message");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<h1>ユーザー登録</h1>

<form action="/gotoFukuoka/Register" method = "post" target="_parent">
ユーザー名		：<input type = "text" name = "new_user"><br>
パスワード		：<input type = "password" name = "new_password"><br>
パスワード確認	:<input type = "password" name = "check_password"><br>
<input type ="submit" value="登録">
</form>
<%
if (message != null){ %>
	<p><%= message %></p>
<% }%>

<a href= "/gotoFukuoka/contents.jsp">トップへ</a>
</body>
</html>