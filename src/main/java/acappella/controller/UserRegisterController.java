package acappella.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import acappella.controller.form.UserForm;
import acappella.domain.User;
import acappella.service.UserRegisterService;



/**
 * ユーザ登録をするコントローラクラス.
 * 
 * @author hiroki.mae
 *
 */
@Controller
@RequestMapping("/user_register")
public class UserRegisterController {

	@ModelAttribute
	public UserForm setUpUserForm() {
		return new UserForm();
	}

	@Autowired
	private UserRegisterService userRegisterService;
	
	private StandardPasswordEncoder encoder = new StandardPasswordEncoder();
	
	/**
	 * 新規ユーザ登録フォームへ遷移.
	 * 
	 * @return 新規登録画面
	 */
	@RequestMapping("/form")
	public String toRegisterForm() {
		return "user/user_register";
	}

	/**
	 * 新規ユーザ登録をする.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/register")
	public String register(@Validated UserForm form, BindingResult result) {

		String encodedPassword = passwordCheck(form, result);

		User user = userRegisterService.loadByEmail(form.getEmail());
		if (user != null) {
			result.rejectValue("email", null, "そのメールアドレスはすでに使われています");
		}

		// 入力値エラーがあれば登録フォームに戻す
		if (result.hasErrors()) {
			return toRegisterForm();
		}

		User newUser = new User();
		BeanUtils.copyProperties(form, newUser);
		newUser.setPassword(encodedPassword);

		userRegisterService.save(newUser);

		return "redirect:/loginform";
	}

	/**
	 * 入力されたパスワードと確認用パスワードが合致するか判定.
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	public String passwordCheck(UserForm form, BindingResult result) {

		String password = form.getPassword();
		String confirmationPassword = form.getConfirmationPassword();
		String encodedPassword = encoder.encode(password);

		if (!(password.equals(confirmationPassword))) {
			result.rejectValue("password", null, "パスワードと確認用パスワードが合致しません");
		}

		return encodedPassword;
	}
}
