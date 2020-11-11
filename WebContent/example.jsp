<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="model.*" %>
        <%@ page import="dao.*" %>

   <%
   Users loginUser=(Users) session.getAttribute("loginUser");
   List<Articles> list = (ArrayList<Articles>) loginUser.getUserInsertList();


   /*
   //テスト用ログイン
   loginUser= new Users("ichiro001","ichiro");
 	ArticlesDAO articlesdao=new ArticlesDAO();
	list= new ArrayList<Articles>();
 	list.add(new Articles(1,"福岡市について","観光地","あいうえお","ichiro001","9/12",23,"/gotoFukuoka/img/dazaifu.jpg"));
*/
   %>


<!DOCTYPE html>
<html lang="jp" dir="ltr">
<head>
<meta charset="UTF-8">
<title>マイページ</title>

<style type="text/css">
body{
text-align: center;
}

p.example1{
color:#7b6459;
 background: #fad3cf;
  box-shadow: 0px 0px 0px 5px #fad3cf;
  border: dashed 2px white;
  padding: 0.2em 0.5em;
}
span.example2{text-align: left;
			font-size: xx-large;
			font-weight: 700;
			 position: relative;/*相対位置*/
  padding-left: 1.2em;/*アイコン分のスペース*/
  line-height: 1.4;/*行高*/
  color: #7b6459;/*文字色*/}
 span.example2:before {
  font-family: "Font Awesome 5 Free";
  content: &#x2740;/*アイコンのユニコード*/
  font-weight: 900;
  position: absolute;/*絶対位置*/
  font-size: 1em;/*サイズ*/
  left: 0;/*アイコンの位置*/
  top: 0;/*アイコンの位置*/
  color: #ff938b; /*アイコン色*/
}
div.left:{text-align: left;}
span.example3{color:red;
			font-size:medium;}
span.example4{color:#00a497;
 			font-size:xx-large;}
span.example5{color:black;
				}
span.example6{width:300px;}


.welcome{
display: inline-block;
  position: relative;
  height: 60px;
  line-height: 60px;
  text-align: center;
  padding: 7px 0;
  font-size: 18px;
  background: #f57a78;
  color: #FFF;
  box-sizing: border-box;

}
.welcome h1{
	margin: 0;
  padding: 0 30px;
  border-top: dashed 2px rgba(255, 255, 255, 0.5);
  border-bottom: dashed 2px rgba(255, 255, 255, 0.5);
  line-height: 42px;
}

.welcome:before, .welcome:after {
  position: absolute;
  content: '';
  width: 0px;
  height: 0px;
  z-index: 1;

}

.welcome:before {
  /*左の山形*/
  top: 0;
  left: 0;
  border-width: 30px 0px 30px 15px;
  border-color: transparent transparent transparent #fff;
  border-style: solid;

}

.welcome:after {
  /*右の山形*/
  top: 0;
  right: 0;
  border-width: 30px 15px 30px 0px;
  border-color: transparent #fff transparent transparent;
  border-style: solid;
}

a{
    font-size: 18px;
    margin: 8px;
    text-decoration: underline;
    color: #f3587c;
}

.top {
  display:inline-block;
  text-align:center;
  width:100%;
}

</style>
</head>
<body>


<center><table width="80%">
<tr>
<td width="150px"><img src="<%=loginUser.getImg() %>" alt="アイコン" width="150px" height="150px"></td>
<td><h1 style="text-align:right"><%= loginUser.getUser_id() %>さん</h1></td>
<td width="200px"><p style="text-align:right"><a href="/gotoFukuoka/Config">プロフィール<br>編集</a></p></td>
</tr>
<tr>
<td colspan="4"><%=loginUser.getText() %></td>
</tr>
</table>
</center>
<p class="example1"><%= loginUser.getUser_id() %>さんの記事一覧</p>

<% if(list == null){%>
	<p>まだ投稿がありません</p>
<% }else if(list != null){%>

<% for(Articles articles:list) {%>

		<div>
            <span class="example2"> <%= articles.getTitle() %></span>
            <span class="example3">  <%= articles.getTorokubi()  %> </span>
           <span class="example4"> <%= articles.getGood() %> </span>

		</div>
            <br>


       <span class="example6"></span> <img src="<%=  articles.getImg()%>" width="200"></span>


           <span class="example5"> <%= articles.getText() %> </span>


              <br><br>


<%} }%>
<div class="top">
	<a href="/gotoFukuoka/contents.jsp">    TOPへ</a>
	<a href="/gotoFukuoka/insertArticle.jsp"> 記事投稿</a>
</div>

</body>
</html>