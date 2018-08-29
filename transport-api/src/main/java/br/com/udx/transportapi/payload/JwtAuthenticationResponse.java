package br.com.udx.transportapi.payload;

public class JwtAuthenticationResponse {

	private String userName;
	private String accessToken;
	private String tokenType = "Bearer";
	private String roleName;

	public JwtAuthenticationResponse(String accessToken, String userName, String roleName) {
		this.accessToken = accessToken;
		this.userName = userName;
		this.roleName = roleName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
