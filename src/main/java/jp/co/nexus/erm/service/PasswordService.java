package jp.co.nexus.erm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nexus.erm.repository.PasswordDao;

/**
 *
 * @author 本間 洋平
 * @version 21/01/01 コメントの追加、メソッドの追加 担当者：松本雄樹
 *
 */
@Service
public class PasswordService{

	@Autowired
	PasswordDao passwordDao;

	/**
	 * 適用期間中のパスワード情報を抽出してListを返す
	 * @param なし
	 * @return list 適用期間中のパスワード情報
	 */
	public List<Map<String, Object>> searchPassword() {
		List<Map<String, Object>> list = passwordDao.searchPassword();
		return list;
	}

	/**
	 * 指定したパスワード種別で、適用期間中のパスワードを返す
	 * @param type パスワード種別
	 * @return pass 抽出したパスワードのString
	 */
	public String getPassword(int type) {

		String pass = new String();

		Map<String, Object> map = passwordDao.getPassword(type);
		if (map!=null) {
			// パスワードの取得に成功している場合
			pass = map.get("password_body").toString();

		} else {
			// パスワードの取得に失敗している場合
			pass = null;

		}

		// 取得したパスワードを返す
		return pass;
	}
}