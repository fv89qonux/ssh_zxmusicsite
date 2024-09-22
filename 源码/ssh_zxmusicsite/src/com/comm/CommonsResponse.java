package com.comm;


public class CommonsResponse {
	
	public static final int SUCCESS = 1;
	public static final int FAILE = 0;
	
	
	
	private int code = FAILE;
	
	private String msg;
	
	private Object data;
	
	private Object data2;
	
	private Object data3;
	
	private Object data4;
	
	private int time;
	
	
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getData2() {
		return data2;
	}
	public void setData2(Object data2) {
		this.data2 = data2;
	}
	public Object getData3() {
		return data3;
	}
	public void setData3(Object data3) {
		this.data3 = data3;
	}
	public Object getData4() {
		return data4;
	}
	public void setData4(Object data4) {
		this.data4 = data4;
	}
	
	
	

}
