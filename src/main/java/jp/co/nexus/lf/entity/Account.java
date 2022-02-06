package jp.co.nexus.lf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * アカウント情報のクラス
 * データテーブルaccountと対応している
 */
@Entity//エンティティクラス（データテーブルとの対応）を表すアノテーション
@Table(name = "CERTIFICATION")//テーブル名
public class Account {

	protected Account(){}
	public Account(String username,String password,boolean isAdmin){
		setId(0L);
		setUsername(username);
		setPassword(password);
		setEnabled(true);
		setAdmin(isAdmin);
	}

	/*
	 * フィールドはaccountデータテーブルの各設定に合わせたアノテーションを付ける
	 */
	@Id//主キー
	@GeneratedValue(strategy=GenerationType.IDENTITY)//自動採番（AUTO_INCREMENT）
	@Column(nullable = false, unique = true)//カラム（設定：null不可、ユニークキー）
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
    private boolean enabled;

	@Column(nullable = false)
    private boolean admin;

	//gettersetter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}
}