package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Articles;

/**
 * 記事を投稿するためのサーブレット
 */
@WebServlet("/InsertArticle")
@MultipartConfig(location = "C:/temp")
public class InsertArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* 記事投稿画面にアクセス
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/insertArticle.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * フォームに入力された記事情報を、セッションスコープに保存し、
	 * 確認画面に表示させるメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String text = request.getParameter("text");


		// 画像ファイルの書込＆データベースに保存するパスの作成
		String img1 = null;
		Part part = request.getPart("img");

		// 先生のお勧めは、Date型のタイムスタンプ
		String name = this.getFileName(part);
		System.out.println(name);

		if(name.isEmpty()) {
			// 画像の投稿がない場合、
			// 何もしない
		}else {
			// 画像の投稿がある場合、
			// 画像の書き込み
		String img = getServletContext().getRealPath("img") + "/" + name;

		part.write(img);
		img1 = "/gotoFukuoka/img/" + name;
		}

		// 記事インスタンスを生成し、セッションスコープに保存
		Articles article = new Articles(title, category, text, img1);
		HttpSession session = request.getSession();
		session.setAttribute("article", article);


		String msg="";
		// 未入力チェック
		if (title == null || title.equals("")) {
			msg +="タイトルは必須項目です<br>";
		}

		if (text == null || text.equals("")) {
			msg +="内容は必須項目です";
		}


		// 入力値チェックの結果により、フォワード先を変更
		String path="";

		if(msg.length()!=0) {
			// 入力値チェックNGの場合、
			// 処理結果メッセージをリクエストスコープに保存し、
			// 投稿画面へフォワード
			request.setAttribute("msg", msg);

			path="/insertArticle.jsp";
		}else {
			// 入力チェックOKの場合、
			// 確認画面へフォワード
			path="/WEB-INF/jsp/confirmation.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);

	}


	// リクエストヘッダーから「画像ファイル名」を取り出すメソッド
	private String getFileName(Part part) {
		String name = null;
		for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
			if (dispotion.trim().startsWith("filename")) {
				name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
				name = name.substring(name.lastIndexOf("\\") + 1);
				break;
			}
		}
		return name;
	}

}
