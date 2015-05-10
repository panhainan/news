package com.phn.dao;


import org.junit.Test;

import com.phn.dao.impl.AdminDaoImpl;
import com.phn.po.Admin;

/**
 * @author phn
 * @TODO 
 * @date 2015-4-25
 */
public class AdminDaoImplTest {

	private static IAdminDao adminDao  = new AdminDaoImpl();
	/**
	 * Test method for {@link com.phn.dao.impl.AdminDaoImpl#getByName(java.lang.String)}.
	 */
	@Test
	public void testGetByName() {
		String adminName = "phn";
		Admin admin = adminDao.getByName(adminName);
		System.out.println(admin);
	}

}
