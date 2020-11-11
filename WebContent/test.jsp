<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.*,dao.*,java.util.*" %>

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
<title>Insert title here</title>
</head>
<body>
<TABLE border="2" width="100%">
<TR bgcolor="#cccccc">
<th colspan="3">一覧</th>
</TR>
<TR bgcolor="#cccccc">
<th width="70%"colspan="2">タイトル</th>
<th width="30%"colspan="1">投稿者</th>
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
	<td colspan="2">テキスト</td>
</tr>


<tr>
	<td>登録日</td>
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