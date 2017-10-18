package acappella.controller.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * ログインフォームの入力情報を持つフォームクラス.
 * @author hiroki.mae
 *
 */
public class LoginUserForm {

	/** メールアドレス*/
	@NotBlank(message="メールアドレスを入力してください")
	@Email(message="メールアドレスを入力してください")
	private String email;
	
	/** パスワード*/
	@Size(min = 8,max = 16,message="半角英数8~16文字以内で入力してください")
	@Pattern(regexp="[a-zA-Z0-9]*",message="半角英数8~16文字以内で入力してください")
	private String password;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
