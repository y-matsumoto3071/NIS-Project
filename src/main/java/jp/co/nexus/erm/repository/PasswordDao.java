package jp.co.nexus.erm.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * パスワード情報の登録・検索・編集・削除に関するSQLを作成して実行するクラス
 * @author 本間 洋平
 * @version 21/01/01 コメントの追加、メソッドの追加 担当者：松本雄樹
 */
@Repository
public class PasswordDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 適用期間中のパスワード情報を抽出してListを返す
	 * @param なし
	 * @return list 適用期間中のパスワード情報
	 */
	public List<Map<String, Object>> searchPassword() {

		// SQL文作成
		String sql = "SELECT password_id, password_body from password "
				+ "WHERE curdate() BETWEEN startdate AND enddate";

		// クエリを実行
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		// 取得したリストを返す
		return list;
	}

	/**
	 * 指定したパスワード種別で、適用期間中のパスワードを返す
	 * @param type パスワード種別
	 * @return pass 抽出したパスワードのMap
	 */
	public Map<String, Object> getPassword(int type) {

		Map<String, Object> map = null;

		// SQL文作成
		String sql = "SELECT password_body from password "
				+ "WHERE password_type = ? "
				+ "AND curdate() BETWEEN startdate AND enddate";

		// ？の箇所を置換するデータの配列を定義
		Object[] param = { type };

		try {
			// クエリを実行
			map = jdbcTemplate.queryForMap(sql, param);

		} catch(IncorrectResultSizeDataAccessException ie) {
			ie.printStackTrace();
			System.out.println("パスワードの取得に失敗しました。\n"
					+ "システム管理者に問い合わせてください。");
		}

		return map;
	}
}