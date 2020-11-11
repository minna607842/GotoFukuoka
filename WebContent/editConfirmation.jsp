<%@page import="model.*,dao.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    //リクエストスコープを取得
    	String editMode = (String) request.getAttribute("editmode");
		String articleId = (String) request.getAttribute("articleId");
		System.out.println(articleId);

	//編集該当記事を取得
	ArticlesDAO articlesdao= new ArticlesDAO();
	ArrayList<Articles> articleList=(ArrayList<Articles>)articlesdao.select(articleId);
	Articles article=articleList.get(0);

    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集の確認</title>
</head>
<body>
<%-- 編集モードが編集（更新）の時 --%>
<form action="/gotoFukuoka/Edit" method="post" enctype="multipart/form-data">
	<%-- 隠しパラメータ―で記事ＩＤと編集モード（更新）を送信 --%>
<input type="hidden" name="articleId" value="<%=articleId%>">
<input type="hidden" name="editMode" value="<%=editMode%>">

	<%if(editMode.equals("edit")) {%>
		<h1>編集</h1>
		タイトル：<input type="text" name="title" value="<%= article.getTitle() %>">
		<br><br>
		エリア：<select name="area">
<option value="--選択してください--" >--選択してください--</option>
<option value="福岡市"> 福岡市</option>
<option value="太宰府・宗像・糟屋郡" >太宰府・宗像・糟屋郡</option>
<option value="北九州市" >北九州市</option>
<option value="北九州市周辺" >北九州市周辺</option>
<option value="筑豊" >筑豊</option>
<option value="久留米・筑後" >久留米・筑後</option>
<option value="糸島">糸島</option>
</select>
<br><br>


		カテゴリ：<select name="category">
<%--！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
カテゴリに変更があった場合は必ず編集すること
 ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！--%>
					<option value="">--カテゴリ--</option>
					<option value="自然・公園・庭園">自然・公園・庭園</option>
					<option value="レストラン・バー">レストラン・バー</option>
					<option value="カフェ">カフェ</option>
					<option value="ショッピング">ショッピング</option>
					<option value="美術館・博物館">美術館・博物館</option>
					<option value="レジャー施設">レジャー施設</option>
					<option value="観光名所">観光名所</option>
					<option value="神社/寺院/教会/モニュメント">神社/寺院/教会/モニュメント</option>
					<option value="展望台・タワー">展望台・タワー</option>
					<option value="イベント・祭り">イベント・祭り</option>
					<option value="歴史・史跡">歴史・史跡</option>
					<option value="ツアー">ツアー</option>
					<option value="その他">その他</option>
	</select>
		<br><br>

		内容：<br>
<textarea name="text" rows="5" cols="40"><%= article.getText() %></textarea>
		<br><br>

		URL：
<input type="text" name="address" value="<%= article.getAddress() %>" style="width:300px">
<br><br>
		<br><br>

		画像：
<input type="file" name="img" accept="image/*"><br>



	<p>以上の内容で編集を確定しますか？</p>
	<input type="submit" value="更新">
	<a href="/gotoFukuoka/myPage.jsp">戻る</a>
</form>

<%-- 編集モードが削除（更新）の時 --%>
		<%}else if(editMode.equals("delete")) { %>
				<h1>削除内容確認</h1>
		タイトル：<%= article.getTitle() %>
		<br><br>

		エリア：<%= article.getArea() %>
		<br><br>

		カテゴリ：<%= article.getCategory() %>
		<br><br>

		内容：<br>
		<%= article.getText() %>
		<br><br>

		URL：<%= article.getAddress() %>
		<br><br>

		画像：<br>
		<% if(article.getImg() == null){%>
			なし
		<%	}else{%>
		<img src="<%= article.getImg() %>" alt="画像" width="200">
		<%} %>
<form action="/gotoFukuoka/Edit" method="post">
	<%-- 隠しパラメータ―で記事ＩＤを送信 --%>
<input type="hidden" name="articleId" value="<%=articleId%>">
<input type="hidden" name="editMode" value="<%=editMode%>">
<p>以上の内容を削除しますか？</p>
<input type="submit" value="削除確定">

</form>
<a href="/gotoFukuoka/Mypage.jsp">戻る</a>

<%} %>


<br>

<a href="/gotoFukuoka/contents.jsp">TOPに戻る</a>

</body>
</html>