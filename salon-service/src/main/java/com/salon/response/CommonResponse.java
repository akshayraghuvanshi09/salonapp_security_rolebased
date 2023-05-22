package com.salon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommonResponse {
	private String message;
	private Integer statusCode;
	private Object data;

	public CommonResponse(String message, Integer statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

}
