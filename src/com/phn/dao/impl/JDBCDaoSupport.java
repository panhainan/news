package com.phn.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.phn.util.TransactionUtil;

/**
 * @author phn
 * @param <T>
 * @date 2015-4-25
 * @TODO
 */
public class JDBCDaoSupport<T> {

	public Connection getConnection() {
		Connection conn = TransactionUtil.connection();
		return conn;
	}

	/**
	 * @date 2015-4-9
	 * @TODO 执行插入语句
	 * @param insertSql
	 * @param params
	 * @return 插入的数据的id
	 */
	public int executeInsert(String insertSql, Object... params) {
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int insertId = 0;
		try {
			pstm = conn.prepareStatement(insertSql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			setParams(pstm, params);
			pstm.executeUpdate();
			rs = pstm.getGeneratedKeys();
			while (rs.next()) {
				insertId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("**Error**:caused at " + this.getClass() + "."
					+ new Throwable().getStackTrace()[0].getMethodName()
					+ "()::" + e.getMessage());
		}
		return insertId;
	}

	/**
	 * @date 2015-4-9
	 * @TODO 执行更新语句或者删除语句
	 * @param updateSql
	 * @param params
	 * @return 数据库受影响的行数
	 */
	public int executeUpdateAndDelete(String updateSql, Object... params) {
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		int updateResult = 0;
		try {
			pstm = conn.prepareStatement(updateSql);
			setParams(pstm, params);
			updateResult = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("**Error**:caused at " + this.getClass() + "."
					+ new Throwable().getStackTrace()[0].getMethodName()
					+ "()::" + e.getMessage());
		}
		return updateResult;
	}

	/**
	 * @date 2015-4-10
	 * @TODO 通过数据库标识字段id查找
	 * @param sql
	 * @param id
	 * @param obj
	 * @return T
	 */
	public T executeGet(String sql, Class<T> obj, Object id) {
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		T o = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setObject(1, id);
			rs = pstm.executeQuery();
			if (rs.next()) {
				o = obj.newInstance();
				o = setTableToEntity(obj, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * @date 2015-4-10
	 * @TODO 根据参数params获取list
	 * @param sql
	 * @param c
	 * @param params
	 * @return List
	 */
	public List<T> executeList(String sql, Class<T> c, Object... params) {
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<T> listObjects = null;
		T o = null;
		try {
			pstm = conn.prepareStatement(sql);
			setParams(pstm, params);
			rs = pstm.executeQuery();
			listObjects = new ArrayList<T>();
			while (rs.next()) {
				o = c.newInstance();
				o = setTableToEntity(c, rs);
				listObjects.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return listObjects;
	}

	/**
	 * @date 2015-4-11
	 * @TODO 获取总数
	 * @param sql
	 * @param params
	 * @return
	 */
	public int getCountRow(String sql, Object... params) {
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int countRow = 0;
		try {
			pstm = conn.prepareStatement(sql);
			setParams(pstm, params);
			rs = pstm.executeQuery();
			if (rs.next()) {
				countRow = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return countRow;
	}

	/**
	 * @date 2015-4-10
	 * @TODO 将数据库中查询出来的结果集ResultSet转化为实体,注意：目前此方法还不适应byte
	 * @param c
	 * @param rs
	 * @return T
	 */
	private T setTableToEntity(Class<T> c, ResultSet rs) {
		T o = null;
		try {
			o = c.newInstance();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columCount = rsmd.getColumnCount();
			for (int i = 0; i < columCount; i++) {
				String columName = rsmd.getColumnName(i + 1);
				Class<?> paramType = c.getDeclaredField(columName).getType();
				StringBuilder sb = new StringBuilder(columName);
				String columnName = sb.toString();
				sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
				sb.insert(0, "set");
				Method md = c.getMethod(sb.toString(),paramType);
				if("boolean".equals(paramType.toString())){
					int temp = (Integer) rs.getObject(columnName);
					if(temp!=0){
						md.invoke(o,new Object[]{ true});
					}else{
						md.invoke(o,new Object[]{ true});
					}
				}else{
					md.invoke(o,new Object[]{  rs.getObject(columnName)});
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * @date 2015-4-9
	 * @TODO 设置SQL语句中的？参数
	 * @param pstm
	 * @param params
	 * @throws SQLException
	 */
	private void setParams(PreparedStatement pstm, Object[] params)
			throws SQLException {
		if (params == null | params.length == 0)
			return;
		for (int i = 0; i < params.length; i++) {
			Object param = params[i];
			pstm.setObject(i + 1, param);
		}
	}

}
