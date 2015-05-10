package com.phn.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * @author phn
 * @TODO This class is a tool class that used to operate the database connection
 * @date 2015-4-25
 */
public class DBUtil {

	/**
	 * @TODO This static method is used to get the data source
	 * @return DataSource
	 */
	public static DataSource getDataSource() {
		DataSource ds = null;
		try {
			InputStream in = TransactionUtil.class.getClassLoader()
					.getResourceAsStream("db.properties");
			Properties props = new Properties();
			props.load(in);
			ds = BasicDataSourceFactory.createDataSource(props);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	/**
	 * @TODO  This static method is the main method that used to test the data source
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getDataSource());
	}

}
