package com.hongru.common.util;

import java.util.Iterator;
import java.util.List;


public class RetInfo {
	private int result = -1;
	private String retCode;
	private String retMsg;
	private Object object;
	@SuppressWarnings("rawtypes")
	private List list;

	public int getResult() {
		return this.result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getRetCode() {
		return this.retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return this.retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public Object getObject() {
		return this.object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}

	@SuppressWarnings("rawtypes")
	public void setList(List list) {
		this.list = list;
	}

	@SuppressWarnings("unchecked")
	public String toString() {
		char sep = '\n';
		StringBuffer paramBuffer = new StringBuffer();
		paramBuffer.append("result = ").append(this.result).append(sep);
		paramBuffer.append("retCode = ").append(this.retCode).append(sep);
		paramBuffer.append("retMsg = ").append(this.retMsg).append(sep);

		paramBuffer.append("object = ")
				.append(this.object == null ? "" : this.object.toString())
				.append(sep);
		Iterator<Object> i$;
		if (this.list != null)
			for (i$ = this.list.iterator(); i$.hasNext();) {
				Object object = i$.next();
				paramBuffer.append("list = ").append(object.toString())
						.append(sep);
			}
		else {
			paramBuffer.append("list = ");
		}
		return paramBuffer.toString();
	}
}
