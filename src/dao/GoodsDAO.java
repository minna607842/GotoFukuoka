package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Goods;

/*
 * いいねに関するＤＡＯクラス
 */
public class GoodsDAO {

	 private final String url = "jdbc:postgresql://localhost:5432/gotofukuoka";
	    private final String user = "postgres";
	    private final String passWord = "test";

	    /********************************************************************************
		 * 「いいね」テーブルから記事IDを検索し、いいねを集計して検索結果を返します。
		 ********************************************************************************/
	    public int select(Goods goods) {
	        Connection con = null;
	        PreparedStatement st = null;
	        ResultSet rs = null;
	        int result = 0;//結果格納用


			try {
		         /* JDBCドライバの定義 */
		         Class.forName("org.postgresql.Driver");

		         /* PostgreSQLへの接続 */
		         con = DriverManager.getConnection(url, user, passWord);

		         /* SELECT文の準備 */
		         String sql = "select count(*) ";
		         sql += "from goods ";
		         sql += "where article_id= "+goods.getArticle_id()+"; ";
		         st = con.prepareStatement(sql);

		         /* SELECT文の実行 */
		         rs = st.executeQuery();
		         rs.next();
		         result = rs.getInt("count");
		         /////テスト用
		         System.out.println(result);



			} catch(Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			} finally {
		         /* PostgreSQLとの接続を切断 */
				if(rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {}
				}

				if(st != null) {
					try {
						st.close();
					} catch (SQLException e) {}
				}

				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {}
				}
			}

	        return result;
	    }

	    /********************************************************************************
		 * 「いいね」テーブルにいいねを追加します
		 ********************************************************************************/

	    public int insert(Goods goods) {
	        Connection con = null;
	        PreparedStatement st = null;
	        int rs = 0;//更新件数
	        //String型の記事ＩＤをint型に変換
	        int articleId=Integer.parseInt(goods.getArticle_id());
	        System.out.println(articleId);


			try {
		         /* JDBCドライバの定義 */
		         Class.forName("org.postgresql.Driver");

		         /* PostgreSQLへの接続 */
		         con = DriverManager.getConnection(url, user, passWord);

		         /* INSERT文の準備 */
		         String sql = "";
		         sql = "INSERT INTO goods(user_id, article_id) ";
		         sql += "VALUES(?, ?);";

		         st = con.prepareStatement(sql);
		         st.setString(1, goods.getUser_id());
		         st.setInt(2, articleId);
		         System.out.println("いいねテーブル追加のＳＱＬ準備完了");

		         /* SELECT文の実行 */
		         rs = st.executeUpdate();
		         System.out.println("結果："+rs);
		         System.out.println("追加作業実行完了");



			} catch(Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			} finally {
		        /* PostgreSQLとの接続を切断 */
				if(st != null) {
					try {
						st.close();
					} catch (SQLException e) {}
				}

				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {}
				}
			}
	        return rs;
	    }
	    /********************************************************************************
		 * 「いいね」テーブルからユーザーidを元に、レコードを削除します
		 ********************************************************************************/

	    public int delete(String id) {
	        /* PostgreSQLへの接続情報 */
	        Connection con = null;
	        PreparedStatement st = null;
	        int delCnt = 0;		// 削除件数

			try {
		         /* JDBCドライバの定義 */
		         Class.forName("org.postgresql.Driver");

		         /* PostgreSQLへの接続 */
		         con = DriverManager.getConnection(url, user, passWord);

		         /* SELECT文の準備 */
		         String sql = "";
		         sql = "DELETE FROM Goods ";
		         sql += "Where user_id = ?;";

		         st = con.prepareStatement(sql);
		         st.setString(1, id);

		         /* DELETE文の実行 */
		         delCnt = st.executeUpdate();


			} catch(Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			} finally {
		         /* PostgreSQLとの接続を切断 */
				if(st != null) {
					try {
						st.close();
					} catch (SQLException e) {}
				}

				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {}
				}
			}

			return delCnt;
	    }

	    /**
	     * 検索結果をインスタンスで返します。
	     */
	    public Goods makeResultData(ResultSet rs) throws Exception {

	    	// 全検索結果を格納するインスタンスを準備


	    	Goods aRecords = new Goods(Integer.toString(rs.getInt("article_id")),rs.getString("user_id"));

	    	return aRecords;
	    }


}
