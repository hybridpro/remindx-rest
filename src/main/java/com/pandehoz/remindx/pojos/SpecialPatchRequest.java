package com.pandehoz.remindx.pojos;

import java.util.List;

public class SpecialPatchRequest {

	private String op;
	
	private String field;
	
	private List<String> value;

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}
	
	
}
