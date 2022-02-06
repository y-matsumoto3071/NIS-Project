package jp.co.nexus.lf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.nexus.lf.entity.InputForm;
import jp.co.nexus.lf.entity.UserAccount;
import jp.co.nexus.lf.service.UserAccountService;

/**
 * LoginController.java
 * ログイン機能に関するアプリケーション制御を行うクラス
 *
 * @author 松本 雄樹
 *
 */
@Controller
public class LoginController {

	@Autowired
	UserAccountService userAccountService;

	@RequestMapping(value = "/login")
	public String index(Model model) {
		model.addAttribute("iserror",false);
		return "lf/LF010";
	}

	@RequestMapping(value = "/login-error")
	public String loginError(Model model) {
		model.addAttribute("iserror",true);
		return "lf/LF010";
	}

	@RequestMapping(value = "/invalidSession")
	public String invalidSession(Model model, RedirectAttributes attr) {
		System.out.println("セッションが無効です。ログアウトします...");
		return "redirect:/logout";
	}

	@GetMapping(value = "/login-setting")
	public String loginSetting(Model model) {

		// ログインしているユーザー名を取得
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserAccount)principal).getUsername();

		model.addAttribute("username", username);

		return "lf/LF020";
	}

	@PostMapping(value = "/change-password")
	public String changePassword(@ModelAttribute InputForm inputForm,Model model) {

		userAccountService.updaterPassword(inputForm.getUsername(), inputForm.getNewPassword());

		return "redirect:/login-setting";
	}
}
