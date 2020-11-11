 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import ="model.*,dao.*,java.util.*" %>

<%
//スコープの取得
	//表示する記事情報の取得
List<Articles> articlesList =(ArrayList<Articles>)request.getAttribute("articlesList");
	//ログイン中のユーザーについて取得
Users loginUser = (Users) session.getAttribute("loginUser");

	//検索が押されていない場合、登録日順で表示
if(articlesList==null){
	ArticlesDAO articlesdao = new ArticlesDAO();
	articlesList=(ArrayList<Articles>)articlesdao.select("","","torokubi asc");
}
	//検索ワードの取得
	String msg =(String)request.getAttribute("selectword");

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>top</title>
</head>
<body bgcolor="#ffffff" text="#666666">
<form  action = "/gotoFukuoka/ArticlesSearch" method="post">
<input style="text-align:center" type="text" name="search">
		<select name="area">
					<option value="">--エリア--</option>
					<option value="福岡市">福岡市</option>
					<option value="太宰府・宗像・糟屋郡">太宰府・宗像・糟屋郡</option>
					<option value="北九州市">北九州市</option>
					<option value="北九州市周辺">北九州市周辺</option>
					<option value="筑豊">筑豊</option>
					<option value="久留米・筑後">久留米・筑後</option>
					<option value="糸島">糸島</option>
	</select>
	<select name="category">
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
	<select name="orderBy">
					<option value="torokubi asc">投稿日昇順</option>
					<option value="torokubi desc">投稿日降順</option>
					<option value="good asc">いいね昇順</option>
					<option value="good desc">いいね降順</option>
	</select>
	<input type="submit" value="検索">

</form>
<%if(msg==null){ %>
<br>
<% }else{%>
検索ワード：<%=msg %>
<% }%>

	<h1>投稿一覧</h1>

<TABLE border="2" width="100%">
<TR bgcolor="#cccccc">
<th colspan="3">一覧</th>
</TR>
<TR bgcolor="#cccccc">
<th colspan="2">タイトル</th>
<th colspan="1">投稿者</th>
</TR>
<TR bgcolor="#cccccc">

<th width="40%" rowspan="3">投稿画像<br>(クリックでオリジナルサイズ)</th>
<th width="30%">エリア</th>
<th width="30%">カテゴリ</th>
</TR>
<TR bgcolor="#cccccc">
<th colspan="3">内容</th>

</TR>
<TR bgcolor="#cccccc">
<th width="50%">いいね</th>
<th width="50%">投稿日</th>
</TR>


<%for(Articles list : articlesList){%>

<tr>
<%-- --%>
	<td rowspan="2"><%=list.getTitle() %> <a href="<%= list.getAddress() %>">ウェブサイト</a></td>
	<td>
	<%
	//投稿ユーザーの情報を取得
	UsersDAO usersdao = new UsersDAO();
	ArrayList<Users> user=(ArrayList<Users>)usersdao.select(list.getUser_id());
	Users insertUser=user.get(0);
	%>
	<img src="<%=insertUser.getImg() %>" alt="アイコン" width="50px" height="50px">
	<%=list.getUser_id() %>
	</td>
</tr>



<tr>
	<td rowspan="3"><a href="<%=list.getImg() %>" target="_blank"><img src="<%=list.getImg() %>" alt="画像"width="200"></a></td>
	<td><%=list.getArea() %></td>
	<td><%=list.getCategory() %>
	</td>
</tr>

<tr>
	<td colspan="2"><%=list.getText() %></td>
</tr>


<tr>
	<td><%=list.getTorokubi() %></td>
	<td>
	<% if(loginUser==null){%>
	💛
	<%}else{ %>
	<a href="/gotoFukuoka/GoodAdd?user_id=<%=loginUser.getUser_id()%>&article_id=<%=list.getArcticle_id() %>">💛</a>
	<% }%>
	<%=list.getGood() %></td>

</tr>


<%
}
%>
</TABLE>

</body>
</html>