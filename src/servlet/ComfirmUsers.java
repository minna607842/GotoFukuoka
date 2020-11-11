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

import dao.UsersDAO;
import model.Users;

@WebServlet("/ComfirmUsers")
public class ComfirmUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * プロフィールの確認画面から「登録」ボタンを押したときに呼ばれる、
	 * データベース登録を行うメソッド
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Users temporaryUser = (Users) session.getAttribute("temporary");

		// ログイン情報を取得
		Users loginUser = (Users) session.getAttribute("loginUser");
		String user_id = loginUser.getUser_id();

		// データベースに保存
		UsersDAO uDao = new UsersDAO();
		int result=0;//結果

		//画像の有無で分岐
		
		if(temporaryUser.getImg()==null) {
			result=uDao.profileUpdate(loginUser.getUser_id(),temporaryUser.getText(),"");

			System.out.println("プロフ更新結果:"+result);
			//更新が成功していたら
			if(result != 0) {
				ArrayList<Users> user=(ArrayList<Users>)uDao.select(loginUser.getUser_id());
				Users u= user.get(0);
				loginUser.setText(u.getText());
			}
		}else {
			result=uDao.profileUpdate(loginUser.getUser_id(),temporaryUser.getText(),temporaryUser.getImg());
			//更新が成功していたら
			if(result != 0) {
				ArrayList<Users> user=(ArrayList<Users>)uDao.select(loginUser.getUser_id());
				Users u= user.get(0);
				loginUser.setText(u.getText());
				loginUser.setImg(u.getImg());
			}
		}

		// セッションスコープから"temporary"を削除
		session.removeAttribute("temporary");

//		//投稿後にユーザーが投稿した記事全てを取得（マイページ表示のため）
//		ArticlesDAO articlesdao= new ArticlesDAO();
//		ArrayList<Articles> userInsertList=(ArrayList<Articles>)articlesdao.se(user_id);
//		loginUser.setUserInsertList(userInsertList);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
