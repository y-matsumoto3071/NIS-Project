package jp.co.nexus.erm.model;

/**
 * 顧客管理機能で使用する顧客情報を管理するクラス
 * ClientEditForm.java
 *
 * @author 松本 雄樹
 *
 */
public class ClientEditForm {
	/** 顧客ID */
	private String client_id;
	
	/** 顧客社名 */
	private String client_name;
	
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

}
