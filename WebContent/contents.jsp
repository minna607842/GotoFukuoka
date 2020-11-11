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
	<select name="category">
<%--！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
カテゴリ正式に決まった場合は必ず処理を変更すること！！
 ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！--%>
					<option value="">--カテゴリ--</option>
					<option value="観光地">観光地</option>
					<option value="パワースポット">パワースポット</option>
					<option value="飲食店">飲食店</option>
					<option value="土産">土産</option>
					<option value="スイーツ">スイーツ</option>
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

	<h1>投稿一覧</h1>

<TABLE border="2" width="100%">
<TR bgcolor="#cccccc">
<TD colspan="3">一覧</TD>
</TR>
<TR bgcolor="#cccccc">

<TD width="20%" rowspan="2">投稿画像<br>(クリックでオリジナルサイズ)</TD>
<TD width="40%">タイトル</TD>
<TD width="40%">投稿者</TD>

</TR>
<TR bgcolor="#cccccc">
<TD width="40%">いいね</TD>
<TD width="40%">投稿日</TD>
</TR>


<%for(Articles list : articlesList){%>
<tr>
	<th rowspan="3"><a href="<%=list.getImg() %>" target="_blank"><img src="<%=list.getImg() %>" alt="画像"width="200"></a></th>
	<th><%=list.getTitle() %></th>
	<th>
	<%
	//投稿ユーザーの情報を取得
	UsersDAO usersdao = new UsersDAO();
	ArrayList<Users> user=(ArrayList<Users>)usersdao.select(list.getUser_id());
	Users insertUser=user.get(0);
	%>
	<img src="<%=insertUser.getImg() %>" alt="アイコン" width="50px" height="50px">
	<%=list.getUser_id() %>
	</th>
</tr>
<tr>
	<th>
	<% if(loginUser==null){%>
	💛
	<%}else{ %>
	<a href="/gotoFukuoka/GoodAdd?user_id=<%=loginUser.getUser_id()%>&article_id=<%=list.getArcticle_id() %>">💛</a>
	<% }%>
	<%=list.getGood() %></th>
	<th><%=list.getTorokubi() %></th>
</tr>
<tr>
	<th colspan="2"><%=list.getText() %></th>
</tr>

<%
}
%>
</TABLE>



</body>
</html>