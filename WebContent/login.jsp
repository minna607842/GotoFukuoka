<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.Map" %>
<%
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body bgcolor="#ffffff" text="#666666"link="006666" vlink="666600" alink="660066">
<h1>ログイン</h1>
<form action="/gotoFukuoka/Login" method = "post" target="_parent">
ユーザー名：<input type = "text" name = "user_id"><br>
パスワード：<input type = "password" name = "password"><br>
<input type ="submit" value="ログイン">
</form>
<a href= "/gotoFukuoka/Register">ユーザー登録</a>
</body>
</html>