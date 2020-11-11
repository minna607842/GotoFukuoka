<%@page import="java.util.List"%>
<%@page import="model.Articles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <% // 投稿確認画面の「修正」から飛ぶ場合、初期値に既入力内容をいれる
    Articles article = (Articles) session.getAttribute("article");

    String title = "";
    String area = "--選択してください--";
    String category = "--選択してください--";
    String text = "";
    String address = "";
    if(article != null){
    	title = article.getTitle();
    	area = article.getArea();
    	category = article.getCategory();
    	text = article.getText();
    	address = article.getAddress();
    }

    // 入力値チェックNGの場合、
    // エラーメッセージをリクエストスコープから取得
    String msg = (String) request.getAttribute("msg");

    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>記事投稿</title>
</head>
<body>

<h1>投稿画面</h1>

<% if(msg != null){ %>
	<p style="color:red"><%= msg %></p>
<% }%>

<form action="/gotoFukuoka/InsertArticle" method="post" enctype="multipart/form-data">

タイトル：
<input type="text" name="title" value="<%= title %>">
<br><br>

　エリア：
<select name="area">
<option value="--選択してください--" <% if(area.equals("--選択してください--")){ %>selected<% } %>>--選択してください--</option>
<option value="福岡市" <% if(area.equals("福岡市")){ %>selected<% } %>>福岡市</option>
<option value="太宰府・宗像・糟屋郡" <% if(area.equals("太宰府・宗像・糟屋郡")){ %>selected<% } %>>太宰府・宗像・糟屋郡</option>
<option value="北九州市" <% if(area.equals("北九州市")){ %>selected<% } %>>北九州市</option>
<option value="北九州市周辺" <% if(area.equals("北九州市周辺")){ %>selected<% } %>>北九州市周辺</option>
<option value="筑豊" <% if(area.equals("筑豊")){ %>selected<% } %>>筑豊</option>
<option value="久留米・筑後" <% if(area.equals("久留米・筑後")){ %>selected<% } %>>久留米・筑後</option>
<option value="糸島" <% if(area.equals("糸島")){ %>selected<% } %>>糸島</option>
</select>
<br><br>


カテゴリ：
<select name="category">
<option value="--選択してください--" <% if(category.equals("--選択してください--")){ %>selected<% } %>>--選択してください--</option>
<option value="自然・公園・庭園" <% if(category.equals("自然・公園・庭園")){ %>selected<% } %>>自然・公園・庭園</option>
<option value="レストラン・バー" <% if(category.equals("レストラン・バー")){ %>selected<% } %>>レストラン・バー</option>
<option value="カフェ" <% if(category.equals("カフェ")){ %>selected<% } %>>カフェ</option>
<option value="ショッピング" <% if(category.equals("ショッピング")){ %>selected<% } %>>ショッピング</option>
<option value="美術館・博物館" <% if(category.equals("美術館・博物館")){ %>selected<% } %>>美術館・博物館</option>
<option value="レジャー施設" <% if(category.equals("レジャー施設")){ %>selected<% } %>>レジャー施設</option>
<option value="観光名所" <% if(category.equals("観光名所")){ %>selected<% } %>>観光名所</option>
<option value="神社/寺院/教会/モニュメント" <% if(category.equals("神社/寺院/教会/モニュメント")){ %>selected<% } %>>神社/寺院/教会/モニュメント</option>
<option value="展望台・タワー" <% if(category.equals("展望台・タワー")){ %>selected<% } %>>展望台・タワー</option>
<option value="イベント・祭り" <% if(category.equals("イベント・祭り")){ %>selected<% } %>>イベント・祭り</option>
<option value="歴史・史跡" <% if(category.equals("歴史・史跡")){ %>selected<% } %>>歴史・史跡</option>
<option value="ツアー" <% if(category.equals("ツアー")){ %>selected<% } %>>ツアー</option>
<option value="その他" <% if(category.equals("その他")){ %>selected<% } %>>その他</option>
</select>
<br><br>


内容：<br>
<textarea name="text" rows="5" cols="40"><%= text %></textarea>
<br>※200文字以内
<br><br>

URL（公式サイト等）：<br>
<input type="text" name="address" value="<%= address %>" style="width:300px">
<br><br>

画像：
<input type="file" name="img" accept="image/*"><br>

<br>
<input type="submit" value="確認画面へ進む">
</form>

</body>
</html>