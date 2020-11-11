<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="model.*" %>
        <%@ page import="dao.*" %>

   <%
   //セッションスコープからユーザー情報の取得
   Users loginUser=(Users) session.getAttribute("loginUser");
   List<Articles> articlesList = (ArrayList<Articles>) loginUser.getUserInsertList();
   %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/gotoFukuoka/CSS/style.css">
</head>
<title>マイページ</title>

<center>
<!-- 表題用のテーブル（アイコン。プロフィール等 -->
<table border="0" width="800px">
	<tr>
		<!-- アイコン、ID,編集遷移リンク -->
		<td rowspan="2" width="150px"><img src="<%=loginUser.getImg() %>" alt="アイコン" width="150px" height="150px"></td>
		<td height="50px"><h1 style="text-align:center"><%= loginUser.getUser_id() %>さん</h1></td>
		<td rowspan="2" width="70px"><p style="text-align:right"><a href="/gotoFukuoka/Config">設定</a></p></td>
	</tr>
	<tr>
		<!-- プロフィールテキスト -->
		<td><%=loginUser.getText() %></td>
	</tr>
</table>
</center>

</center>
<p class="example1"><%= loginUser.getUser_id() %>さんの記事一覧</p>

<% if(articlesList == null){%>
	<p>まだ投稿がありません</p>
<% }else if(articlesList != null){%>

<form action="/gotoFukuoka/Edit" method="get" >
<%--////////////////////////////////////テーブル開始////////////////////////////////////////////////////////////////// --%>
<TABLE border="2" width="100%">

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
		<% if(loginUser==null){%>
		💛
		<%}else{ %>
		<a href="/gotoFukuoka/GoodAdd?user_id=<%=loginUser.getUser_id()%>&article_id=<%=list.getArcticle_id() %>">💛</a>
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