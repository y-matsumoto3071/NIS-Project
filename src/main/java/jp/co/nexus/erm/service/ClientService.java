package jp.co.nexus.erm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import jp.co.nexus.erm.model.Client;
import jp.co.nexus.erm.model.ClientEditForm;
import jp.co.nexus.erm.repository.ClientDao;

/**
 * ClientService.java
 * 顧客管理機能で使用する顧客情報の登録・検索・編集・削除に関する処理を
 * ClientDaoクラスからClientControllerクラスに提供する
 *
 * @author 本間 洋平
 * @version 21/01/01 コメントの追加　担当者：松本雄樹
 *
 */
@Service
public class ClientService{

	@Autowired
	ClientDao clientDao;

	/**
	 * 顧客情報を全件取得してListで返す。
	 * @param なし
	 * @return list 顧客情報全件取得結果のList
	 */
	public List<Map<String, Object>> searchAll() {
		List<Map<String, Object>> list = clientDao.searchAll();
		return list;
	}

	/**
	 * 顧客情報の削除フラグ=0以外を抽出してListで返す。
	 * @param なし
	 * @return list 削除フラグ=0以外の顧客情報抽出結果のList
	 */
	public List<Map<String, Object>> searchActive() {
		List<Map<String, Object>> list = clientDao.searchActive();
		return list;
	}

	/**
	 * 指定された顧客情報を論理削除する。
	 * @param c_id 削除対象の顧客IDのString配列
	 * @return 削除件数
	 */
	public int deleteClient(String[] c_id) {
		// 削除した件数
		int result = 0;

		// TODO: 処理を追加してみましょう

		return result;
	}

	/**
	 * 顧客情報を新規登録する。
	 * @param cef 顧客編集画面の入力フォーム
	 * @return 登録件数
	 */
    public int registClient(ClientEditForm cef) {
		// 実行件数
		int result = 0;

		try {
    		result = clientDao.registClient(cef);
    		
    	} catch (DuplicateKeyException e) {
    		System.out.println(e.getMessage());
    		throw e;
    	}
    	
    	return result;
    }

	/**
	 * 登録された顧客情報を編集する。
	 * @param cef 顧客編集画面の入力フォーム
	 * @return 更新結果
	 */
    public int editClient(ClientEditForm cef) {
		// 実行件数
		int result = 0;

		try {
    		result = clientDao.editClient(cef);
    		
    	} catch (DuplicateKeyException e) {
    		System.out.println(e.getMessage());
    		throw e;
    	}
    	
    	return result;
    }

	/**
	 * 顧客IDで指定された顧客情報を返す。
	 * @param clientId 抽出対象の顧客IDのInteger
	 * @return client 顧客情報インスタンス
	 */
    public Client searchClient(Integer clientId){
    	Map<String, Object> map = clientDao.searchClient(clientId);
    	Client client = null;
    	
    	if (map != null) {
    		client = new Client(map);
    		
    	}
    	return client;
    }

}