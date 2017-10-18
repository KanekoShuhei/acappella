package acappella.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acappella.domain.User;
import acappella.repository.UserRepository;



/**
 * ユーザ登録をするサービスクラス.
 * 
 * @author hiroki.mae
 *
 */
@Service
@Transactional
public class UserRegisterService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * ユーザを一件登録.
	 *
	 * @param user
	 *            ユーザ情報
	 * @return 更新した行数
	 */
	public Integer save(User user) {
		return userRepository.save(user);
	}

	/**
	 * メールアドレスで一件削除.
	 * 
	 * @param email
	 *            メールアドレス
	 * @return 更新した行数
	 */
	public Integer deleteByEmail(String email) {
		return userRepository.deleteByEmail(email);
	}

	/**
	 * メールアドレスで一件検索.
	 * 
	 * @param email
	 *            メールアドレス
	 * @return 検索結果(例外の場合nullを返す)
	 */
	public User loadByEmail(String email) {
		return userRepository.loadByEmail(email);
	}

	/**
	 * ユーザを一件更新.
	 *
	 * @param user
	 *            ユーザ情報
	 */
	public void update(User user) {
		userRepository.update(user);
	}
}
