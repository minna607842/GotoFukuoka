<%@page import="model.Articles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <% Articles article = (Articles) session.getAttribute("article"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投稿内容の確認</title>
</head>
<body>

<h1>内容確認</h1>

タイトル：<%= article.getTitle() %>
<br><br>

エリア：<%= article.getArea() %>
<br><br>

カテゴリ：<%= article.getCategory() %>
<br><br>

内容：<br>
<%= article.getText() %>
<br><br>

URL：<a href="<%= article.getAddress() %>" target=target="_blank"><%= article.getAddress() %></a>
<br><br>

画像：<br>
<% if(article.getImg() == null){%>
	なし
<%	}else{%>
<img src="<%= article.getImg() %>" alt="画像" width="200">
<%} %>

<p>以上の内容で投稿してよろしいですか？</p>

<a href="/gotoFukuoka/InsertArticle">修正</a>
<a href="/gotoFukuoka/ComfirmArticle">投稿</a>

<br>

<a href="/gotoFukuoka/contents.jsp">TOPに戻る</a>

</body>
</html>