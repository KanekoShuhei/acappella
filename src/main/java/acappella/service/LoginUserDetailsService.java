package acappella.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acappella.domain.LoginUser;
import acappella.domain.User;
import acappella.repository.UserRepository;



/**
 * LoginUserDetailsを作成するサービスクラス.
 * 
 * @author hiroki.mae
 *
 */
@Service
@Transactional
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.loadByEmail(email);
		if (user == null) {
			throw new RuntimeException("そのメールアドレスは登録されていません");
		}

		Collection<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		if (user.isSystemManager()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return new LoginUser(user, authorities);
	}
}
