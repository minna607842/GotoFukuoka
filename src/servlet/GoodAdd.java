package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticlesDAO;
import model.GetGoodLogic;
import model.GoodLogic;
import model.Goods;

/**
 * いいね追加のコントローラ
 */
@WebServlet("/GoodAdd")
public class GoodAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Postリクエストを処理します。
	 * ①記事表示画面で「いいね」画像が押された場合
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータから検索条件の取得、（JSPからuser_id, article_idを取得）
			request.setCharacterEncoding("UTF-8");
			String user_id = request.getParameter("user_id");
			String article_id = request.getParameter("article_id");

			//GoodLogicを呼び出し、GoodsDAOに投げる。
			Goods goods = new Goods(article_id,user_id);
			GoodLogic goodLogic = new GoodLogic();
			goodLogic.goodPlus(goods);

			//いいねの合計を取得して、リクエストスコープに保存
			GetGoodLogic getGoodLogic = new GetGoodLogic();
			int goodCnt = getGoodLogic.execute(goods);

			///////////////////開発用//////////////////////////
			System.out.println("カウント完了");
			System.out.println(goodCnt);
			///////////////////////////////////////////////////

			ArticlesDAO articlesDAO = new ArticlesDAO();
			int result=articlesDAO.goodUpdate(article_id, goodCnt);

			///////////////////開発用//////////////////////////
			System.out.println("更新完了");
			System.out.println(result);
			///////////////////////////////////////////////////
//
//			List<Articles> list= articlesDAO.select("福岡市について", "観光地", "good asc");
//			request.setAttribute("list", list);

			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/contents.jsp");
			dispatcher.forward(request, response);


	}

}
