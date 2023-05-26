package login;
/**
 * 
 * user bean
 */
public class UserInfo {
	/**
	 * user id ,will increase when a new user is adding
	 */
	private int id;
	/**
	 * user name
	 */
	private String userName;
	/**
	 * user password
	 */
	private String password;

	/**
	 * 
	 * @param userName
	 * @param password
	 */
	public UserInfo(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	/**
	 * 
	 * @param id
	 * @param userName
	 * @param password
	 */
	public UserInfo(int id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}

}
