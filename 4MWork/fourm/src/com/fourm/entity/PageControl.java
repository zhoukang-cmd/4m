package com.fourm.entity;

/**
 * 分页
 */
public class PageControl {
	private int currPage; // 当前页
	private int pageSize = 5;// 每页记录数

	private int pageCount;// 页数
	private int currLine;// 当前行
	private int prePage;// 上一页
	private int nextPage;// 下一页
	// 核心处理器啊

	/**
	 * 
	 * @param page
	 * @param toLine
	 * @param pageSize
	 * @return
	 */
	public PageControl pageFormat(int page, int toLine, int pageSize) {
		PageControl pc = new PageControl();
		// 从数据库读取总行数
		int totalLine = toLine;
		// 计算总页数
		int pageCount = (int) Math.ceil(((double) totalLine / (double) pageSize));
		// 修正页码
		if (page < 1)
			page = 1;

		if (page > pageCount)
			page = pageCount;
		// 计算已经翻过的行数的最后一行行码，因为在数据库，是从该行码开始读数据的。
		int currLine = (page - 1) * pageSize;

		int prePage = page - 1;
		int nextPage = page + 1;

		pc.setCurrPage(page);
		pc.setPrePage(prePage);
		pc.setNextPage(nextPage);
		pc.setPageSize(pageSize);
		pc.setPageCount(pageCount);
		pc.setCurrLine(currLine);

		return pc;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCurrLine() {
		return currLine;
	}

	public void setCurrLine(int currLine) {
		this.currLine = currLine;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

}
