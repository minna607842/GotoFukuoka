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
import model.Articles;
import model.Users;


@WebServlet("/ComfirmArticle")
public class ComfirmArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 記事の確認画面から「登録」ボタンを押したときに呼ばれる、
	 * データベース登録を行うメソッド
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Articles article = (Articles) session.getAttribute("article");

		// ★入力値チェックはいらなそう？★

		// ログイン情報を取得
		Users loginUser = (Users) session.getAttribute("loginUser");
		String user_id = loginUser.getUser_id();

		// データベースに保存
		ArticlesDAO aDao = new ArticlesDAO();
		if(article.getImg()==null) {
			aDao.insert(article.getTitle(),  article.getArea(),article.getCategory(), article.getText(), article.getAddress(), user_id, "/gotoFukuoka/img/default.png");
		}else {
			aDao.insert(article.getTitle(), article.getArea(), article.getCategory(), article.getText(), article.getAddress(), user_id, article.getImg());
		}

		// セッションスコープから投稿一時データ"article"を削除
		session.removeAttribute("article");

		//投稿後にユーザーが投稿した記事全てを取得（マイページ表示のため）
		ArticlesDAO articlesdao= new ArticlesDAO();
		ArrayList<Articles> userInsertList=(ArrayList<Articles>)articlesdao.se(user_id);
		loginUser.setUserInsertList(userInsertList);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/complete.jsp");
		dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ★doPostメソッドはいらなそう★

	}

}
