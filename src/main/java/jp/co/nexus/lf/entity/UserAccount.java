package jp.co.nexus.lf.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
 *
 * ユーザーアカウントクラス（UserDetailsインタフェースを実装）
 * ユーザ名、パスワード、権限情報を保存、各情報の出力
 */

public class UserAccount implements UserDetails {

	private static final long serialVersionUID = -256740067874995659L;//シリアルバージョン、生成しなおしたほうがいいかもしれない
	private Account user;//アカウント情報を格納するAccountクラスオブジェクト
	private Collection<GrantedAuthority> authorities;//「ROLE_ADMIN」や「ROLE_USER」などのロール情報を保持するリスト

	protected UserAccount(){}
	public UserAccount(Account account,Collection<GrantedAuthority> authorities){
		this.user = account;
		this.authorities = authorities;
	}

	/*
	 * 該当アカウントの権限を取得
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return this.authorities;
	}

	/*
	 * 該当アカウントのパスワードを取得
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/*
	 * 該当アカウントのユーザ名を取得
	 */
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	/*
	 * アカウント期限切れかを判定
	 * true：期限切れではない、false：期限切れ
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;//とりあえずtrueを返している
	}

	/*
	 * アカウントのロックされているかを判定
	 * true：ロックされてない、false：ロックされている
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;//とりあえずtrueを返している
	}

	/*
	 * 資格情報期限切かを判定
	 * true：期限切れではない、false：期限切れ
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;//とりあえずtrueを返している
	}

	/*
	 * 使用可能なアカウントかを返す
	 */
	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

}