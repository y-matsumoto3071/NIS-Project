package jp.co.nexus.lf.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.nexus.lf.entity.Account;
import jp.co.nexus.lf.entity.UserAccount;
import jp.co.nexus.lf.repository.AccountRepository;

/*
 * DB認証のためのアカウント関連のサービスクラス
 * ユーザ情報の検索、ユーザ登録、ユーザの権限の確認など行う
 */

@Service
public class UserAccountService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * usernameを受け取って、アカウントの各種情報を持つUserDetailsオブジェクトを返すメソッド
	 * おそらくログイン時に使用
	 * 登録されているアカウントのユーザ名に重複がない前提の処理
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//ユーザ名が未入時の警告
		if (username == null || "".equals(username)) {
			throw new UsernameNotFoundException("Username is empty");
		}

		//入力ユーザ名で検索
		Account ac = accountRepository.findByUsername(username);

		//登録されていないユーザの場合、ユーザが未発見の例外処理を発生させる
		if (ac == null) {
			throw new UsernameNotFoundException("User not found: " + username);
		}
		//アカウント停止されているユーザの場合、ユーザが未発見の例外処理を発生させる
		if (!ac.isEnabled()) {
			throw new UsernameNotFoundException("User not found: " + username);
		}

		//ユーザのアカウント情報を持つインスタンス生成（アカウント情報+権限を付与し生成）
		UserAccount user = new UserAccount(ac,getAuthorities(ac));

		/*テスト用にコンソールに表示
		System.out.println("ロードユーザ名：" + user.getUsername());
		System.out.println("ロードパスワード：" + user.getPassword());
		System.out.println("使用可能?：" + user.isEnabled());
		*/

		return user;
	}

	/*
	 * accountオブジェクトを受け取り、該当アカウントが持つロール（権限のリスト）を返すメソッド
	 * ユーザアカウントのインスタンス生成時に権限を付与するため使用
	 */
	private Collection<GrantedAuthority> getAuthorities(Account account){

		if(account.isAdmin()){
			return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
		}else{
			return AuthorityUtils.createAuthorityList("ROLE_USER");
		}

	}

	/*
	 * アドミンユーザをDBに登録するメソッド
	 * @Transactionalアノテーションを付与することで管理され、トランザクションの開始、コミット、ロールバックは自動で行われる
	 */
	@Transactional
	public void registerAdmin(String username, String password) {
		//ユーザ名、パスワード（エンコード後）、管理者権限有で登録
		Account user = new Account(username, passwordEncoder.encode(password),true);
		accountRepository.save(user);
	}

	/*
	 * ユーザをDBに登録するメソッド
	 * @Transactionalアノテーションを付与することで管理され、トランザクションの開始、コミット、ロールバックは自動で行われる
	 */
	@Transactional
	public void registerUser(String username, String password) {
		Account user = new Account(username, passwordEncoder.encode(password),false);
		accountRepository.save(user);
	}

	/*
	 * パスワードを更新するメソッド
	 * 未作成
	 */
	@Transactional
	public void updaterPassword(String username, String password) {
		Account user = accountRepository.findByUsername(username);
		//ユーザ名、パスワード（エンコード後）、管理者権限有で登録
		user.setPassword(passwordEncoder.encode(password));
		accountRepository.save(user);
	}

}