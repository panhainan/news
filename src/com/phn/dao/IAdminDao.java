package com.phn.dao;

import com.phn.po.Admin;

/**
 * @author phn
 * @TODO This interface is used to process the relative operation between manager and database administrator table
 * @date 2015-4-25
 */
public interface IAdminDao {
	/**
	 * @TODO The method is used to  perform the login of administrator that get the password and return by the name
	 * @param adminName
	 * @return Admin (adminPass)
	 */
	Admin getByName(String adminName);
}
