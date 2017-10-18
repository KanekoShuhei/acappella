package acappella.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * ログインユーザを持つ詳細情報クラス.
 * @author hiroki.mae
 *
 */
public class LoginUser extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 1L;
	
	/** ログインユーザ*/
	private final User user;
	
	public LoginUser(User user,Collection<GrantedAuthority> authorities){
		super(user.getEmail(),user.getPassword(),authorities);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	
}
