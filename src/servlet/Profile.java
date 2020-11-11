package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ArticlesDAO;
import dao.UsersDAO;
import model.Articles;
import model.Users;


@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");

////////開発用//////////////////////////////////////////
		System.out.println("受け取ったユーザーＩＤ"+userId);
////////開発用//////////////////////////////////////////


			//セッションスコープの準備
		HttpSession session= request.getSession();

		//指定ユーザーの情報を取得
		UsersDAO usersdao= new UsersDAO();
		ArrayList<Users> list =(ArrayList<Users>)usersdao.select(userId);

		for(Users u:list) {
			if(u.getUser_id().equals(userId)) {
				Users selectUser= u;
				//セッションスコープに保存
				session.setAttribute("selectUser", selectUser);

				////////開発用//////////////////////////////////////////
				System.out.println(selectUser.getUser_id());
				////////////////////////////////////////////////////////////
			}

		}



		//指定ユーザーの投稿記事を取得
		ArticlesDAO articledao=new ArticlesDAO();
		ArrayList<Articles> userArticleList = (ArrayList<Articles>)articledao.se(userId);
		//セッションスコープに保存
		session.setAttribute("userArticleList", userArticleList);

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
