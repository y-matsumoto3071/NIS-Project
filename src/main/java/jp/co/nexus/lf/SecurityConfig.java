package jp.co.nexus.lf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
 * セキュリティの設定クラスSecurityConfig
 * WebSecurityConfigurerAdapterを継承し、各種設定を上書き（OverRide）する
 * 例外が起きた場合はスロー宣言で例外をExceptionクラスに投げる
 * （セキュリティ関連なので例外はすべてException送りにしてエラーを発生させる）
 */

@Configuration //設定用のクラスを表すアノテーション
@EnableWebSecurity //SpringSecurityを有効にするアノテーション
public class SecurityConfig extends WebSecurityConfigurerAdapter{


	/*
	 * ユーザー固有のデータを読み込むインターフェースUserDetailsServiceをDI
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	/*
	 * アクセス認可の設定を行うconfigureAuthenticationManagerをDI
	 */
	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)//UserDetailesServiceの指定
		.passwordEncoder(passwordEncoder());//パスワードエンコーダーの指定
	}

	/*
	 * パスワードを暗号化するオブジェクトを返すクラスを@BeanでDIコンテナに登録
	 */
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	/*
	 * Web全般のセキュリティ設定
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
	}

	/*
	 * 認証するユーザー情報をデータベースからロードする処理
	 * 認証マネージャー生成ツールAuthenticationManagerBuilder
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)//UserDetailesServiceの指定
		.passwordEncoder(passwordEncoder());//パスワードエンコーダーの指定
	}

	/*
	 * Http通信のセキュリティ設定
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//認可の設定
		http.authorizeRequests()
		//認証無しでアクセスできるURLを設定
		.antMatchers("/css/**", "/img/**", "/js/**").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/logout").permitAll()

		//管理者権限のあるアカウントだけがアクセスできるURLの設定
		.and()
		.authorizeRequests().mvcMatchers("/employee/*").hasAuthority("ROLE_ADMIN")
		.and()
		.authorizeRequests().mvcMatchers("/client/*").hasAuthority("ROLE_ADMIN")
//		.and()
//		.authorizeRequests().mvcMatchers("/master/*").hasAuthority("ROLE_ADMIN")
		//上記以外は認証が必要にする設定
		.anyRequest().authenticated();

		//ログインの設定
		http.formLogin()
		//ログインフォームのパスを設定
		.loginPage("/login")
		//ログイン成功時のリダイレクトするURL設定
		.defaultSuccessUrl("/menu")
		//認証失敗時にforwardするURLを設定
		.failureForwardUrl("/login-error")
		//認証成功時にforwardするURLを設定
//		.successForwardUrl("/menu")
		//認証成功時に呼ばれるハンドラクラスを設定
//		.successHandler(successHandler)
		//認証失敗時にリダイレクトするURLを設定
//		.failureUrl("/menu")
		//認証失敗時に呼ばれるハンドラクラスを設定
//		.failureHandler(failureHandler)
		;

		//ログアウトの設定(ログアウトページは準備してないので、アクセス時にログアウトになる？)
		http.logout()
		// ログアウト処理
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		// ログアウト成功時の遷移先
		.logoutSuccessUrl("/login")
		// ログアウト時に削除するクッキー名
		.deleteCookies("JSESSIONID")
		// ログアウト時のセッション破棄を有効化
		.invalidateHttpSession(true)
		;

		//CSRFの無効化（有効だとPOST処理などが例外処理に引っかかる）
		http.csrf().disable();

		//セッションの設定
		http.sessionManagement()
		// セッションが無効な時の遷移先
		.invalidSessionUrl("/invalidSession");

	}

}
