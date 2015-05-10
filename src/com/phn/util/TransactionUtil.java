package com.phn.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author phn
 * @TODO This class is a tool class that used to operate the related affairs
 * @date 2015-4-25
 */
public class TransactionUtil {
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	private static DataSource ds;

	static {
		try {
			ds = DBUtil.getDataSource();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}


	public static Connection connection() {
		Connection conn = tl.get(); // 从ThreadLoacl中获取，如果没有再从DataSource中获取
		if (conn == null) {
			try {
				conn = ds.getConnection();
				tl.set(conn); // 存到ThreadLoacl中
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static void start() {
		try {
			Connection conn = tl.get();
			if (conn == null) { // 如果ThreadLoacl中没有，就从DataSource中获取
				conn = ds.getConnection();
				tl.set(conn); // 存入
			}
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void rollback() { // 回滚事务，在service层try下dao层，在catch处调用rollbakc方法
		try {
			Connection conn = tl.get();
			if (conn != null) {
				conn.rollback();
				System.out.println("事务回滚了");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void commit() { // 在finally里调用提交commint方法
		try {
			Connection conn = tl.get();
			if (conn != null){
				conn.commit();
				System.out.println("事务提交了");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void release() {
		try {
			Connection conn = tl.get();
			if (conn != null) {
				conn.close();
				tl.remove();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}