package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Access")
public class Access extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//indexページのiframeの内容の分岐先準備
		String path ="";

		//ifame分岐
			//パラメータの受け取り
		request.setCharacterEncoding("UTF-8");
		String pathAction = request.getParameter("pathAction");

		if(pathAction == null) {
			path="";

			//top（contents.jspへ）
		}else if(pathAction.equals("top")) {
			path="/gotoFukuoka/contents.jsp";

			//ログイン画面へ
		}else if(pathAction.equals("login")) {
			path="/gotoFukuoka/login.jsp";

			//マイページへ
		}else if(pathAction.equals("mypage")) {

			path="/gotoFukuoka/myPage.jsp";

			//投稿ページへ
		}else if(pathAction.equals("post")) {

			path="/gotoFukuoka/insertArticle.jsp";

			//ログアウトページへ
		}else if(pathAction.equals("logout")) {

			//セッションスコープを破棄
			HttpSession session = request.getSession();
			session.invalidate();

			path="/gotoFukuoka/logout.jsp";

		}else if(pathAction.equals("loginResult")) {
			path="/gotoFukuoka/loginResult.jsp";
		}

		//セッションスコープに入力

		HttpSession session = request.getSession();
		session.setAttribute("path",path );
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
