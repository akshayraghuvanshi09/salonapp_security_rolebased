package com.bezkoder.springjwt.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAccessTokenResponse {
	private String accessToken;
	private String refreshToken;
	private String type = "Bearer";

	public JwtAccessTokenResponse(String accesstoken, String refreshToken) {
		super();
		this.accessToken = accesstoken;
		this.refreshToken = refreshToken;

	}

}
