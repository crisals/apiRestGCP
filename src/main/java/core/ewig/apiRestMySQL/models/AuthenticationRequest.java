package core.ewig.apiRestMySQL.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cristian Alarcón
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

	/**
	 * String with system username.
	 */
	private String userName;

	/**
	 * String with system password.
	 */
	private String password;

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

}
