<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.*,model.*,dao.*" %>

 <%

 //iframeの表示内容を分岐する準備
 String path ="";

//スコープ類
	//ログイン中のユーザー情報を取得
Users loginUser = (Users) session.getAttribute("loginUser");
	//iframeの表示ページのパスを取得
path =(String)session.getAttribute("path");

//pathがnullの場合はiframeはtopの検索画面に設定
if(path==null){
	path="/gotoFukuoka/contents.jsp";
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Go to Fukuoka</title>
</head>
<body bgcolor="#FDEFFO" text="#555555" link="006666" vlink="666600" alink="660066">
<p style="text-align:center">
<table border="0" align="center" width="100%">
<tr>
	<td width="20%"><a href="/gotoFukuoka/Access?pathAction=top"><img src="/gotoFukuoka/img/material/logo.png" alt="ロゴ"></a></td>
	<td >
		<%if(loginUser == null){ %>
	<%	}else if(loginUser != null){ %>
		<p style="text-align:right"><img src="<%=loginUser.getImg() %>" alt="アイコン" width="70px" height="70px"></p>
	<% }%>
	</td>
	<td width="15%">
		<%if(loginUser == null){ %>
	<%	}else if(loginUser != null){ %>
		<p style="text-align:left"><%=loginUser.getUser_id() %>さん<br>ログイン中</p>
	<% }%>
	</td>
	<td width="1000px">
	<p style="text-align:right">
	<%if(loginUser == null){ %>
		<a href="/gotoFukuoka/Access?pathAction=login"><img src="/gotoFukuoka/img/material/login.png" alt="ログイン"></a>
	<%	}else if(loginUser != null){ %>
		<a href="/gotoFukuoka/Access?pathAction=post"><img src="/gotoFukuoka/img/material/insert.png" alt="投稿"></a>
		<a href="/gotoFukuoka/Access?pathAction=mypage"><img src="/gotoFukuoka/img/material/mypage.png" alt="マイページ"></a>
		<a href="/gotoFukuoka/Access?pathAction=logout"><img src="/gotoFukuoka/img/material/logout.png" alt="ログアウト"></a>
	<% }%>
	</p>
	</td>
</tr>
</table>

<iframe src="<%=path%>" width="100%" height="1000"></iframe>

<%  //iframe表示場所初期化
	path=""; %>
<p>Team.SHUSHI presents</p>
</body>
</html>


