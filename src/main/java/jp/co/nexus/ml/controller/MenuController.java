package jp.co.nexus.ml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * MenuController.java
 * メニュー機能に関するアプリケーション制御を行うクラス
 *
 * @author 松本 雄樹
 *
 */
@Controller
public class MenuController {
	/**
	 * メニュー画面に遷移する
	 */
	@GetMapping("/menu")
	public String menu(Model model) {

		// 画面遷移先をメニュー画面に指定
		String res = "ml/ML010";

		return res;
	}


}
