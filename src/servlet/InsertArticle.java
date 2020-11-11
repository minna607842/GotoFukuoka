package servlet;

import java.io.IOException;
import java.util.Date;

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
import model.ImgLogic;

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
		String area = request.getParameter("area");
		String category = request.getParameter("category");
		String text = request.getParameter("text");
		String address = request.getParameter("address");
			//画像用のパラメータ
		//Part part = request.getPart("img");

		// 画像ファイルの書込＆データベースに保存するパスの作成
		String img1 = null;
		Part part = request.getPart("img");

		ImgLogic imgLogic=new ImgLogic();
		/****
		 * 画像ファイルの書き込みとデータベースに保存するパスの作成
		 * 画像添付の有無で書き込みの有無を分岐した上で
		 * 画像のパス+名前を返す
		 ***/
		//String img=imgLogic.execute(part);

		// 一意のファイル名とするために、
		// 元ファイルの名前と、現在時刻を取得
		String name = this.getFileName(part);
		Date now = new Date();

		if(name.isEmpty()) {
			// 画像の投稿がない場合、
			// 何もしない
		}else {
			// 画像の投稿がある場合、
			// 画像の書き込み
		String img = getServletContext().getRealPath("img") + "/" + now.getTime()+ name ;

		part.write(img);
		img1 = "/gotoFukuoka/img/" + now.getTime()+ name ;
		}

		//URLの記入がなかった場合、nullから変換
		if(address == null) {
			address = "";
		}

		// 記事インスタンスを生成し、セッションスコープに保存
		// 入力されたデータを仮に保存する（確認画面から修正された場合に内容を反映させるため）
		Articles article = new Articles(title, category, area, address, text, img1);
		HttpSession session = request.getSession();
		session.setAttribute("article", article);


		// 未入力チェック
			//エラーメッセージ準備
			String msg="";
		if (title == null || title.equals("")) {
			msg +="タイトルは必須項目です<br>";
		}

		if (category.equals("--選択してください--")) {
			msg +="カテゴリを選択してください<br>";
		}

		if (area.equals("--選択してください--")) {
			msg +="エリアを選択してください<br>";
		}

		if (text == null || text.equals("")) {
			msg +="内容は必須項目です<br>";
		}

		if (text.length() > 200) {
			msg +="内容は200文字以内で入力してください<br>現在の入力値は" + text.length() + "文字です";
		}


		// 入力値チェックの結果により、フォワード先を変更
			//フォワード先の準備
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
