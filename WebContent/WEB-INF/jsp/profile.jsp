<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="model.*" %>
        <%@ page import="dao.*" %>

   <%

   Users user = (Users)session.getAttribute("selectUser");
   List<Articles> articlesList =(ArrayList<Articles>)session.getAttribute("userArticleList");

   %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/gotoFukuoka/CSS/style.css">
</head>
<title>マイページ</title>

<center><table width="80%">
<tr>
<td width="150px"><img src="<%=user.getImg() %>" alt="アイコン" width="150px" height="150px"></td>
<td><h1 style="text-align:right"><%= user.getUser_id() %>さん</h1></td>
<td width="200px"><p style="text-align:right"><a href="/gotoFukuoka/Config">プロフィール<br>編集</a></p></td>
</tr>
<tr>
<td colspan="4"><%=user.getText() %></td>
</tr>
</table>
</center>

</center>
<p class="example1"><%= user.getUser_id() %>さんの記事一覧</p>

<% if(articlesList == null){%>
	<p>まだ投稿がありません</p>
<% }else if(articlesList != null){%>

<form action="/gotoFukuoka/Edit" method="get" >
<%--////////////////////////////////////テーブル開始////////////////////////////////////////////////////////////////// --%>
<TABLE border="2" width="100%">

<TR bgcolor="#fad3cf">
	<th colspan="3">一覧</th>
</TR>
<TR bgcolor="#fad3cf">
	<th colspan="2">タイトル</th>
	<th colspan="1">編集チェック</th>
</TR>
<TR bgcolor="#fad3cf">

	<th width="30%" rowspan="3">投稿画像<br>(クリックでオリジナルサイズ)</th>
	<th width="20%">エリア</th>
	<th width="30%">カテゴリ</th>
</TR>
<TR bgcolor="#fad3cf">
	<th colspan="2">内容</th>

</TR>
<TR bgcolor="#fad3cf">
	<th width="50%">いいね</th>
	<th width="50%">投稿日</th>
</TR>

<%-- データ入力部分 --%>
<%for(Articles list : articlesList){%>

	<%-- タイトル・編集用ラジオボタン --%>
<tr>

	<td colspan="2">
	<% if(list.getAddress().length()!=0){ %>
	<a href="<%= list.getAddress() %>" target="_blank"><%=list.getTitle() %></a>
	<%}else{ %>
	<%=list.getTitle() %>
	<%} %>
	</td>
	<td>
		<input type="radio" name="edit" value="edit,<%=list.getArcticle_id()%>"> 編集　<input type="radio" name="edit" value="delete,<%=list.getArcticle_id()%>"> 削除
	</td>
</tr>


	<%-- 画像・エリア・カテゴリ --%>
<tr>
	<td rowspan="3"><a href="<%=list.getImg() %>" target="_blank"><img src="<%=list.getImg() %>" alt="画像"width="200"></a></td>
	<td height="30px"><%=list.getArea() %></td>
	<td><%=list.getCategory() %>
	</td>
</tr>

	<%-- テキスト --%>
<tr>
	<td colspan="2"><%=list.getText() %></td>
</tr>

	<%-- 投稿日・いいね --%>
<tr>
	<td height="30px"><%=list.getTorokubi() %></td>
	<td>
	<% if(user==null){%>
	💛
	<%}else{ %>
	<a href="/gotoFukuoka/GoodAdd?user_id=<%=user.getUser_id()%>&article_id=<%=list.getArcticle_id() %>">💛</a>
	<% }%>
	<%=list.getGood() %></td>

</tr>


<%
}}
%>
</TABLE>
<p style="text-align: right"><input type="submit" value="編集"></p>
<form>
<%--////////////////////////////////////テーブル終わり////////////////////////////////////////////////////////////////// --%>

<div class="top">
	<a href="/gotoFukuoka/contents.jsp">    TOPへ</a>
	<a href="/gotoFukuoka/insertArticle.jsp"> 記事投稿</a>
</div>

</body>
</html>