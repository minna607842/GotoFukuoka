package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Articles;


/*
 * 記事に関するＤＡＯクラス
 */

public class ArticlesDAO {

    private final String url = "jdbc:postgresql://localhost:5432/gotofukuoka";
    private final String user = "postgres";
    private final String password = "test";

	/********************************************************************************
	 * 「記事」テーブルから記事名とカテゴリでデータを検索し、検索結果を返します。
	 ********************************************************************************/
    public List<Articles> select(String title, String category,String orderBy) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Articles> aRecords = null;

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(url, user, password);

	         /* SELECT文の準備 */
	         String sql = "SELECT * ";
	         sql += "FROM articles ";
	         sql += "WHERE title like ? ";
	         sql += "AND category like ? ";
	         sql += "ORDER BY "+orderBy+";";

	         st = con.prepareStatement(sql);

	         st.setString(1, "%" + title + "%");
	         st.setString(2, "%" + category + "%");

	         System.out.println(sql);

	         /* SELECT文の実行 */
	         rs = st.executeQuery();

	         /* 結果をリストに移し替える */
	         aRecords = makeResultData(rs);

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

        return aRecords;
    }

    /********************************************************************************
	 * 「記事」テーブルからuser_idでデータを検索し、検索結果を返します。
	 ********************************************************************************/
    public List<Articles> se(String user_id) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Articles> aRecords = null;

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(url, user, password);

	         /* SELECT文の準備 */
	         String sql = "SELECT * ";
	         sql += "FROM articles ";
	         sql += "WHERE user_id = ? ";
	         sql += "ORDER BY good DESC; ";

	         st = con.prepareStatement(sql);
	         st.setString(1,user_id);



	         /* SELECT文の実行 */
	         rs = st.executeQuery();

	         /* 結果をリストに移し替える */
	         aRecords = makeResultData(rs);

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

        return aRecords;
    }

	/********************************************************************************
	 * 「記事」テーブルに記事を追加します
	 ********************************************************************************/


    public int insert( String title,
    					String category,
    					String text,
    					String user_id,
    					String img ) {
        Connection con = null;
        PreparedStatement st = null;
        int insCnt = 0;		// 更新件数

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(url, user, password);

	         /* INSERT文の準備 */
	         String sql = "";
	         sql = "INSERT INTO Articles(title, category, text, user_id, torokubi, good, img) ";
	         sql += "VALUES(?, ?, ?, ?,CURRENT_TIMESTAMP,DEFAULT, ?);";

	         st = con.prepareStatement(sql);
	         st.setString(1, title);
	         st.setString(2, category);
	         st.setString(3, text);
	         st.setString(4, user_id);
	         st.setString(5, img);


	         /* INSERT文の実行 */
	         insCnt = st.executeUpdate();

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

        return insCnt;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////更新箇所
	/********************************************************************************
	 * 「記事」テーブルの情報を更新します
	 * String型の記事idとint型の最新版のいいね数を引数に受け取ってデータをアップデートする
	 ********************************************************************************/


    public int goodUpdate(String article_id,int goodCount) {
        Connection con = null;
        PreparedStatement st = null;
        int insCnt = 0;		// 更新件数

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(url, user, password);

	         /* INSERT文の準備 */
	         String sql = "";
	         sql = "Update articles  ";
	         sql += "set good="+goodCount+" ";
	         sql += "where article_id="+article_id+";";

	         st = con.prepareStatement(sql);

	         /* INSERT文の実行 */
	         insCnt = st.executeUpdate();

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

        return insCnt;
    }

	/********************************************************************************
	 * 「記事」テーブルにから記事idを元にを削除します
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
	         con = DriverManager.getConnection(url, user, password);

	         /* SELECT文の準備 */
	         String sql = "";
	         sql = "DELETE FROM Articles ";
	         sql += "Where article_id = ?;";

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
     * 検索結果をリストで返します。
     */
    public ArrayList<Articles> makeResultData(ResultSet rs) throws Exception {

    	// 全検索結果を格納するリストを準備
    	ArrayList<Articles> aRecords = new ArrayList<Articles>();

    	while(rs.next()) {
    		// 1行分のデータを読込む
    		int arcticle_id = rs.getInt("article_id");
    		String title = rs.getString("title");
    		String category = rs.getString("category");
    		String text = rs.getString("text");
    		String user_id = rs.getString("user_id");
    		String torokubi = rs.getString("torokubi");
    		int good = rs.getInt("good");
    		String img = rs.getString("img");
    		String address=rs.getString("address");
    		String area=rs.getString("area");

    		// 1行分のデータを格納するインスタンス
    		Articles aRecord = new Articles(arcticle_id,
    										title,
    										area,
    										category,
    										text,
    										address,
    										user_id,
    										torokubi,
    										good,
    										img);

            // リストに1行分のデータを追加する
            aRecords.add(aRecord);
    	}
    	return aRecords;
    }

}
