package com.salon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
	 private String message;
	 private Integer status;

	  public MessageResponse(String message) {
	    this.message = message;
	  }

	 
}
