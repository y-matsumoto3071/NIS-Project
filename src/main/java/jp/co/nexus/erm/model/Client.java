package jp.co.nexus.erm.model;

import java.util.Map;

/**
 * 顧客管理機能で使用する顧客情報を管理するクラス
 * Client.java
 *
 * @author 松本 雄樹
 *
 */
public class Client {
	/** 顧客ID */
	private String client_id;

	/** 顧客社名 */
	private String client_name;

	/** 削除フラグ */
	private String deleteflg;

	/** 作成日付 */
	private String createdate;

	/** 更新日付 */
	private String updatedate;

	/** 顧客情報を空文字で初期化 */
	public Client() {
		this.client_id = "";
		this.client_name = "";
		this.deleteflg = "";
		this.createdate = "";
		this.updatedate = "";
	}
	
	/** 顧客情報をmapで初期化
	 * @param map 顧客情報テーブルから抽出したレコード
	 */
	public Client(Map<String, Object> map) {
		this.client_id = map.get("client_id").toString();
		this.client_name = map.get("client_name").toString();
		this.deleteflg = map.get("deleteflg").toString();
		this.createdate = map.get("createdate").toString();
		this.updatedate = map.get("updatedate").toString();
		
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getDeleteflg() {
		return deleteflg;
	}

	public void setDeleteflg(String deleteflg) {
		this.deleteflg = deleteflg;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

}
