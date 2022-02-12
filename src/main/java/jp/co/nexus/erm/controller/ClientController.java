/**
 *
 */
package jp.co.nexus.erm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.nexus.erm.model.Client;
import jp.co.nexus.erm.model.ClientEditForm;
import jp.co.nexus.erm.service.ClientService;
import jp.co.nexus.erm.service.PasswordService;

/**
 * ClientController.java
 * 顧客情報管理機能に関するアプリケーション制御を行うクラス
 *
 * @author 本間 洋平
 * @version 21/01/01 コメントの追加　担当者：松本雄樹
 * @version 21/01/18 バグ修正(CE-010-010 顧客編集画面遷移)　担当者：松本雄樹
 *
 */
@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService clientService;

	@Autowired
	PasswordService passwordService;

	/**
	 * CL-010-010 顧客一覧画面遷移
	 * 顧客情報一覧画面に遷移する
	 */
	@GetMapping("/list")
	public String clientList(Model model) {

		// 画面遷移先を顧客情報一覧画面に指定
		String res = "client/client_list";

		List<Map<String, Object>> list = clientService.searchActive();
		model.addAttribute("client_list", list);

		return res;
	}

	/**
	 * CD-010-010 顧客削除処理
	 * ★論理削除時に実行
	 * 以下の時はエラーを発出
	 * ・パスワード未入力
	 * ・チェックボックス未選択
	 * ・パスワード誤入力
	 */
	@PostMapping("/delete")
	public String deleteClient(@RequestParam(name = "selectCheck", required = false) String[] c_id,
			@RequestParam(name = "adminPW", defaultValue = "") String adminPW,
			RedirectAttributes attr) {

		// 画面遷移先を顧客情報一覧画面へのリダイレクトに指定
		String res = "redirect:/client/list";

		// エラーメッセージを格納する変数をインスタンス化
		String attributeValue = new String();

		String active_pw = passwordService.getPassword(1);

		// パスワードが未入力の場合
		if (adminPW.equals("")) {
			attributeValue = "パスワードを入力してください。";

		// 削除対象が選択されていない場合
		} else if (c_id == null) {
			attributeValue = "削除する顧客を選択してください。";

		// 正規入力されている場合
		} else if (adminPW.equals(active_pw)) {
			//論理削除実行
			int result = clientService.deleteClient(c_id);
			attributeValue = result + "件削除しました。";

		// 上記条件に合致しない場合、パスワード誤入力と判定
		} else {
			attributeValue = "パスワードが間違っています。";

		}

		attr.addFlashAttribute("Result", attributeValue);

		return res;
	}

	/**
	 * CC-010-010 顧客情報登録画面遷移
	 * CE-010-010 顧客編集画面遷移
	 */
	@GetMapping("/edit")
	public String editClient(@RequestParam(name = "id", defaultValue = "") Integer c_id,
			Model model) {

		// 画面遷移先を顧客情報登録画面に指定
		String res = "client/client_edit";

		Client client = null;

		// 顧客情報登録画面遷移時の処理
		if (c_id == null) {
			client = new Client();
			
		// 顧客編集画面遷移時の処理
		} else {
			//選択された顧客情報を抽出
			client = clientService.searchClient(c_id);
			
			// 不正パラメータアクセス時の処理
			if (client == null) {
				res = "redirect:/client/edit";
			}
			
		}

		//編集画面に顧客名を表示
		model.addAttribute("client", client);

		return res;
	}

	/**
	 * CC-010-020_顧客新規登録内容DB登録
	 * CE-010-020_顧客編集内容DB登録
	 */
	@PostMapping("/regist")
	public String createClient(@ModelAttribute ClientEditForm clientEditForm,
			Model model, RedirectAttributes attr) {
		
		// フォームから顧客IDを取得
		String c_id = clientEditForm.getClient_id();
		
		// 画面遷移先を顧客情報一覧画面へのリダイレクトに指定
		String res = "redirect:/client/list";

		// 編集中かどうかを判定するフラグ
		boolean isUpdating = !(c_id.equals(""));

		// 無効な入力値かどうかを判定するフラグ
		boolean invalid = false;

		// エラーメッセージを格納する変数をインスタンス化
		String attributeValue = new String();

		try {
			// 編集時はUPDATE、新規登録時はINSERTを実行
			if (isUpdating) {
				clientService.editClient(clientEditForm);
				attributeValue = "更新が完了しました。";
				
			} else {
				clientService.registClient(clientEditForm);			
				attributeValue = "登録が完了しました。";
				
			}
			
		} catch (DuplicateKeyException e) {
			invalid = true;
			attributeValue = "社名が重複しています。";
			
		} catch (Exception e) {
			invalid = true;
			attributeValue = "不明なエラー";
			e.printStackTrace();
			
		}

		// 入力値が無効な場合は編集画面へリダイレクト
		if (invalid) {
			if (isUpdating) {
				res = "redirect:/client/edit?id=" + c_id;
			} else {
				res = "redirect:/client/edit";
			}
			
		}
		
		attr.addFlashAttribute("Result", attributeValue);
		return res;
	}

}
