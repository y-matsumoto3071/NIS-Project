package jp.co.nexus.lf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.nexus.lf.entity.Account;

/*
 * Accountエンティティを操作するインターフェース
 * accountデータテーブルからユーザ名とパスワードを取得する
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {

	public Account findByUsername(String username);

}