package jp.co.nexus.erm.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * ClientDao.java
 * 顧客管理機能で使用する顧客情報の登録・検索・編集・削除に関するSQLを
 * 作成して実行するクラス
 *
 * @author 本間 洋平
 * @version 21/01/01 コメントの追加　担当者：松本雄樹
 *
 */
@Repository
public class ClientDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 顧客情報を全件取得してListで返すSQLを実行する
	 * @param なし
	 * @return list 全件取得結果のList
	 */
	public List<Map<String, Object>> searchAll() {

		// SQL文作成
		String sql = "SELECT * from client";

		// クエリを実行
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		// 取得したリストを返す
		return list;
	}

	/**
	 * 顧客情報の削除フラグ=0以外を抽出してListで返すSQLを実行する
	 * @param なし
	 * @return list 削除フラグ=0以外の顧客情報抽出結果のList
	 */
	public List<Map<String, Object>> searchActive() {

		// SQL文作成
		String sql = "SELECT * from client WHERE deleteflg != 0";

		// クエリを実行
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		// 取得したリストを返す
		return list;
	}

	/**
	 * 指定された顧客情報を論理削除するSQLを実行する
	 * @param c_id 削除対象の顧客IDのString配列
	 * @return result 削除件数
	 */
	public int deleteClient(String[] c_id) {
		// 削除した件数
		int result = 0;

		// TODO: 処理を追加してみましょう

		// 実行件数を返す
		return result;
	}

	/**
	 * 指定された顧客情報を抽出するSQLを実行する。
	 * @param clientId 抽出対象の顧客IDのInteger
	 * @return clt 抽出結果のMap
	 */
	public Map<String, Object> searchClient(Integer clientId) {

		Map<String, Object> clt = null;
		
		// SQL文作成
		String sql = "SELECT * FROM client "
				+ "WHERE client_id = ? "
				+ "AND deleteflg != 0;";

		// ？の箇所を置換するデータの配列を定義
		Object[] param = { clientId };

		try {
			// クエリを実行
			clt = jdbcTemplate.queryForMap(sql, param);
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}

		// 取得したデータを返す
		return clt;
	}

	/**
	 * 指定された顧客情報を抽出するSQLを実行する。
	 * @param search 抽出対象の顧客名のString
	 * @return list 抽出結果のList
	 */
	public List<Map<String, Object>> searchClientList(String search) {
		System.out.println("aadfadfa:" + search);

		// SQL文作成
		String sql = "SELECT * FROM client WHERE client_name LIKE ?";

		// ? の箇所を置換するデータの配列を定義
		Object[] param = { "%" + search + "%" };

		// クエリを実行
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, param);

		// 取得したリストを返す
		return list;
	}

	/**
	 * 顧客情報をINSERTするSQLを実行する。
	 * @param c_name INSERTする顧客名のString
	 * @return INSERT件数
	 */
	public int registClient(String c_name) {

		// 実行結果
		int result = 0;

		// TODO: 処理を追加してみましょう

		// 実行結果を返す
		return result;
	}

	/**
	 * 登録された顧客情報をUPDATEするSQLを実行する
	 * @param c_name UPDATE後の顧客名のString
	 * @param c_id UPDATE対象の顧客IDのString
	 */
	public int editClient(String c_name, String c_id) {
		// 編集した件数
		int result = 0;

		// TODO: 処理を追加してみましょう

		// 実行件数を返す
		return result;
	}
}
