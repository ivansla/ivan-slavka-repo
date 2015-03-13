package ivan.slavka.utils.beans;

import ivan.slavka.utils.enums.LoginTypeEnum;

public class LoginBean {

	private LoginTypeEnum loginType;
	private String username;

	public LoginTypeEnum getLoginType() {
		return this.loginType;
	}
	public void setLoginType(LoginTypeEnum loginType) {
		this.loginType = loginType;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
