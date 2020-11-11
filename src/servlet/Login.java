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
import model.LoginLogic;
import model.Users;



@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");

		//Userインスタンス（ユーザー情報）の生成
		Users user = new Users(user_id, password);

		//ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin=loginLogic.execute(user);

		//ログイン成功時の処理
		if (isLogin) {

			//ログインしたらユーザーが登録した記事の情報を取得する
			ArticlesDAO articlesdao= new ArticlesDAO();
			ArrayList<Articles> userInsertList=(ArrayList<Articles>)articlesdao.se(user_id);
			user.setUserInsertList(userInsertList);

			//アイコンの情報・プロフィールテキストを取得
			UsersDAO userdao = new UsersDAO();
			ArrayList<Users> userinfo=(ArrayList<Users>)userdao.select(user_id);
			String text="";
			String img="";

			for(Users users:userinfo) {
				text= users.getText();
				img= users.getImg();
			}
			user.setText(text);
			user.setImg(img);

			//開発用////////////////////////////////////////////////////////////////////////////////
			System.out.println("ユーザーの投稿リストを取得できているか");
			for(Articles a:userInsertList) {
				System.out.println(a.getArcticle_id());
			}
			System.out.println("リストをユーザーのjavabeansに登録できているか");

			for(Articles a:user.getUserInsertList()) {
				System.out.println(a.getArcticle_id());
			}
			/////////////////////////////////////////////////////////////////////////////////////////



		//ユーザー情報をセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", user);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/Access?pathAction=loginResult");
		dispatcher.forward(request, response);
	}
}
