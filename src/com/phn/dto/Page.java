package com.phn.dto;

/**
 * @author phn
 * 
 */
public class Page {

	private int currentPage; // 当前页数
	private int pageSize; // 每页显示的记录数
	private int totalPages; // 总页数
	private int allRecords; // 总记录数

	private boolean isFirstPage; // 是否是第一页
	private boolean isFinalPage; // 是否是最后一页
	private boolean hasPreviousPage; // 是否有上一页
	private boolean hasNextPage; // 是否有下一页

	/**
	 * @TODO 初始化分页信息
	 * @param allRecords
	 * @param currentPage
	 * @param pageSize
	 * @param totalPages
	 */
	public void init(int allRecords, int currentPage, int pageSize,
			int totalPages) {
		this.allRecords = allRecords;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalPages = totalPages;
		if (this.currentPage == 1) {
			this.isFirstPage = true;
		}
		if (this.currentPage == this.totalPages) {
			this.isFinalPage = true;
		}
		if (this.currentPage > 1) {
			this.hasPreviousPage = true;
		}
		if (this.currentPage < this.totalPages) {
			this.hasNextPage = true;
		}
	}

	/**
	 * @TODO 计算总页数
	 * @param pageSize每页的记录数
	 * @param allRecords总记录数
	 * @return 总页数
	 */
	public int calculateTotalPage(final int pageSize, final int allRecords) {
		int total = (allRecords % pageSize == 0) ? allRecords / pageSize
				: allRecords / pageSize + 1;
		if (total == 0) {
			total = 1;
		}
		return total;
	}

	/**
	 * @TODO 计算当前页的开始记录数
	 * @param pageSize每页记录数
	 * @param currentPage当前第几页
	 * @return 当前页开始记录数
	 */
	public int calculateCurrentPageStartRecord(int pageSize, int currentPage) {
		int startRecord = pageSize * (currentPage - 1);
		return startRecord;
	}

	/**
	 * @date 2015-4-12
	 * @TODO 判断当前页是否为0
	 * @param currentPage当前页
	 * @param totalPages总页数
	 * @return 判断当前页的结果
	 */
	public int judgeCurrentPageIsLegal(int currentPage, int totalPages) {
		final int judgeCurrentResult = (currentPage <= 0 || currentPage > totalPages) ? 0
				: currentPage;
		return judgeCurrentResult;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getAllRecords() {
		return allRecords;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public boolean isFinalPage() {
		return isFinalPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	@Override
	public String toString() {
		return "Pages [currentPage=" + currentPage + ", pageSize=" + pageSize
				+ ", totalPages=" + totalPages + ", allRecords=" + allRecords
				+ ", isFirstPage=" + isFirstPage + ", isFinalPage="
				+ isFinalPage + ", hasPreviousPage=" + hasPreviousPage
				+ ", hasNextPage=" + hasNextPage + "]";
	}

}
