package com.p5k.bacao.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorModel {

	private String clazzName;
	private String errorCode;
	private String violateField;
	private String violateMsg;

}
