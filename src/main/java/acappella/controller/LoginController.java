package acappella.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import acappella.controller.form.LoginUserForm;


/**
 * ログインフォーム画面のコントローラクラス.
 * @author shuhei.kaneko
 *
 */
@Controller
@RequestMapping("/loginform")
public class LoginController {
	
	@ModelAttribute
	public LoginUserForm setUpLoginUserForm(){
		
		LoginUserForm loginUserForm = new LoginUserForm();
		loginUserForm.setEmail("shukonda@gmail.com");
		loginUserForm.setPassword("shuhei1129");
		
		return loginUserForm;
	}

	/**
	 * ログインフォームへ遷移.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("")
	public String toLoginForm(String error,Model model) {

		if("true".equals(error)){
			model.addAttribute("loginErrorMessage", "メールアドレス、またはパスワードが間違っています");
		}
		
		return "user/loginform";
	}
}
