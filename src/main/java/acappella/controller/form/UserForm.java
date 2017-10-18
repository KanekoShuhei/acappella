package acappella.controller.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * ユーザを表すフォームクラス.
 * @author hiroki.mae
 *
 */
public class UserForm {
	
	/** ユーザID*/
	private String id;
	
	/** ユーザ氏名*/
	@NotBlank(message="名前を入力してください")
	@Size(message="名前が長すぎます", max= 16)
	private String name;
	
	/** メールアドレス*/
	@NotBlank(message="メールアドレスを入力してください")
	@Email(message="アドレスが不正です")
	private String email;
	
	/** パスワード*/
	@Size(min = 8,max = 16,message="半角英数8~16文字以内で入力してください")
	@Pattern(regexp="[a-zA-Z0-9]*",message="半角英数8~16文字以内で入力してください")
	private String password;
	
	/** 確認用パスワード*/
	@Size(min = 8,max = 16,message="半角英数8~16文字以内で入力してください")
	@Pattern(regexp="[a-zA-Z0-9]*",message="半角英数8~16文字以内で入力してください")
	private String confirmationPassword;
	
	
	/** getter/setter*/
	public Long getLongId() {
		return Long.parseLong(this.id);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getConfirmationPassword() {
		return confirmationPassword;
	}
	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}
	
	
}