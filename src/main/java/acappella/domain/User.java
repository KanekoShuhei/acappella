package acappella.domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * ユーザを表すドメインクラス.
 * 
 * @author hiroki.mae
 *
 */
public class User {

	/** ユーザID */
	private Long id;
	/** ユーザ氏名 */
	private String name;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String telephone;
	/** 管理者の是非 */
	private boolean isSystemManager;
	/** 最終ログイン日時 */
	private Timestamp lastLoginDateTime;
	/** いいねした動画リスト */
	private List<Video> likeVideoList;

	public User() {
		super();
	}

	public User(String name, String email, String password, String address, String telephone) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.telephone = telephone;
	}

	public Integer getIntId() {
		return Integer.parseInt(String.valueOf(this.id));
	}

	/** 以降getter/setter */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public boolean isSystemManager() {
		return isSystemManager;
	}

	public void setSystemManager(boolean isSystemManager) {
		this.isSystemManager = isSystemManager;
	}

	public Timestamp getLastLoginDateTime() {
		return lastLoginDateTime;
	}

	public void setLastLoginDateTime(Timestamp lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
	}

	public List<Video> getLikeVideoList() {
		return likeVideoList;
	}

	public void setLikeVideoList(List<Video> likeVideoList) {
		this.likeVideoList = likeVideoList;
	}

}
