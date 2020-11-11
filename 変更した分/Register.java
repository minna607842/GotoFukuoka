package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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



@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ユーザー登録画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String new_user = request.getParameter("new_user");
		String new_password = request.getParameter("new_password");
		String check_password = request.getParameter("check_password");

		//エラーメッセージ
		String message="";
		//フォワード先
		String path="";


		//登録処理
		//
		if(new_user.length() != 0 && new_password.length() != 0) {
			//これから登録するユーザー名がすでに登録済みか調べる。
			//登録されていなければ、登録処理を行う。

			UsersDAO usersdao = new UsersDAO();
			List<Users> list=usersdao.select(new_user);

			for(Users users:list) {
				if(new_user.equals(users.getUser_id())) {
					//idがすでに登録されている場合
					message += "そのユーザーは既に登録されています。";
				}
				if(new_user.equals(check_password)){
					//何もしない
				}else {
					//パスワードとパスワード確認ボックスの内容が一致しないとき
					message +="パスワードが一致していません。もう一度入力してください";
				}
			}
			request.setAttribute("message", message);
			String msg = (String) request.getAttribute("message");

			if(msg.length()==0) {
				//idの登録がない場合（登録可能な場合）登録
				int result=usersdao.insert(new_user, new_password);

				//登録が完了したらログイン状態にする
				Users user = new Users(new_user,new_password);

				//ログインしたらユーザーが登録した記事の情報を取得する
				ArticlesDAO articlesdao= new ArticlesDAO();
				ArrayList<Articles> userInsertList=(ArrayList<Articles>)articlesdao.se(new_user);
				user.setUserInsertList(userInsertList);

				//アイコンの情報・プロフィールテキストを取得
				UsersDAO userdao = new UsersDAO();
				ArrayList<Users> userinfo=(ArrayList<Users>)userdao.select(new_user);
				String text="";
				String img="";

				for(Users users:userinfo) {
					text= users.getText();
					img= users.getImg();
				}
				user.setText(text);
				user.setImg(img);

				//ユーザー情報をセッションスコープに保存
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", user);

				//登録完了メッセージをリクエストスコープに保存
				request.setAttribute("message", "登録が完了しました。");
				path="/Access?pathAction=loginResult";
			}

		}else {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("message", "ユーザー名、パスワードは必須項目です。");
		}

		//フォワード処理

		RequestDispatcher d = request.getRequestDispatcher("/Access?pathAction=loginResult");
		d.forward(request, response);
	}





}



