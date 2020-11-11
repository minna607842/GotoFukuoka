<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*,model.*" %>
<%
//スコープの取得
	//ログインユーザーの情報
	  Users loginUser=(Users) session.getAttribute("loginUser");
	// エラーメッセージをリクエストスコープから取得
	String msg = (String) request.getAttribute("msg");
	//編集中データの取得
	Users temporaryUsers= (Users)session.getAttribute("temporary");


%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>設定</title>
</head>
<body>
	<h1>プロフィール編集</h1>
	<form action="/gotoFukuoka/Config" method="post" enctype="multipart/form-data">
		<table border="0" width="600px">
			<tr>
				<td bgcolor="#fad3cf" width="200px"><h3>アイコンの設定</h3></td>
				<td ><td>
				<td bgcolor="#ffffff" width="380px">アイコン画像はアップロードされた画像を<br>150px*150pxで強制的に縮小して表示します</td>
			</tr>
		</table>

		  <br>
		現在のアイコン:<br>
		<img src="<%=loginUser.getImg() %>" alt="アイコン" width="150px" height="150px"><br>

		<br>
		<input type="file" name="icon" accept="image/*"><br>
		<br>

		<table border="0" width="600px">
			<tr>
				<td bgcolor="#fad3cf" width="200px"><h3>プロフィール文変更</h3></td>
				<td ><td>
				<td bgcolor="#ffffff" width="380	px">テキストは300文字以内で入力してください<br></td>
			</tr>
		</table>


		<% if(msg != null){ %>
			<p style="color:red"><%= msg %></p>
		<% }%>
		<%-- 編集中データがあればそちらを取得、無ければ元からのデータを取得 --%>
		<textarea name="text" rows="5" cols="40">
		<% if(temporaryUsers==null){%>
		<%= loginUser.getText().trim()%>
		<%  }else{%>
		<%= temporaryUsers.getText().trim() %>
		<% }%>
		</textarea><br>

		<input type="submit" value="確認画面へ進む">
	</form>
</body>
</html>