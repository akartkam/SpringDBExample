package com.akartkam.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.dao.annotation.


public class App {
	 
	
	public static void main(String[] args) throws Exception {

		

		ApplicationContext appContext = new ClassPathXmlApplicationContext("SpringDBExample.xml");
     
		
		DataSource ds = (ComboPooledDataSource) appContext.getBean("dataSource");
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
		conn =ds.getConnection();
		stmt =conn.prepareStatement("SELECT * FROM MKYONGDB.CE_ITEM");
		ResultSet rs = stmt.executeQuery();
		if (rs.next()){		
		System.out.println(rs.getString(2));
		}
		} catch(SQLException e){
		// dosomething...notsurewhat,though
		} finally{
		try {
		if (stmt!=null){
		stmt.close();
		}
		if (conn!=null){
		conn.close();
		}
		} catch(SQLException e){
		// I'mevenlesssureaboutwhattodohere
		}
		}
		//Jdbc Template
		SimpleJdbcTemplate jdbcTemplate = (SimpleJdbcTemplate) appContext.getBean("jdbcTemplate");
		Item item = jdbcTemplate.queryForObject(
				"SELECT * FROM MKYONGDB.CE_ITEM IT WHERE IT.CE_ITEM_ID = ?",
				newParameterizedRowMapper<Item>(){
				public SpittermapRow(ResultSetrs,introwNum)
				throws SQLException{
				Spitter spitter=newSpitter();
				spitter.setId(rs.getLong(1));
				spitter.setUsername(rs.getString(2));
				spitter.setPassword(rs.getString(3));
				spitter.setFullName(rs.getString(4));
				return spitter;
				}
				},
				id
				);
	}
}