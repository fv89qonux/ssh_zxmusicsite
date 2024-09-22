package com.comm;

import java.util.List;


public class Page {

	
	private int pageNo;
	
	private int pageSize;
	
	private int rowcounts;
	
	private int pageCount;
	
	
	private List datas;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRowcounts() {
		return rowcounts;
	}

	public void setRowcounts(int rowcounts) {
		this.rowcounts = rowcounts;
	}

	public List getDatas() {
		return datas;
	}

	public void setDatas(List datas) {
		this.datas = datas;
	}
	
	

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Page(){

	}

	public void finalize() throws Throwable {

	}

}