package jp.co.nexus.lf.entity;

/**
 * InputForm.java
 * パスワード変更画面で入力された内容を保持するモデルクラス
 *
 * @author 松本 雄樹
 *
 */
public class InputForm {

	private String username;
	private String newPassword;
	private String oldPassword;
	private String confirmPassword;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
