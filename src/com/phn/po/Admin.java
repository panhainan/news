package com.phn.po;

/**
 * @author phn
 * @TODO JavaBean Admin :  it is the manager of news
 * @date 2015-4-25
 */
public class Admin {
	private String adminName;
	private String adminPass;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	@Override
	public String toString() {
		return "Admin \n[adminName=" + adminName + ", adminPass=" + adminPass
				+ "]";
	}

}
