package com.akartkam.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class App {
	 
	
	public static void main(String[] args) throws Exception {

		

		ApplicationContext appContext = new ClassPathXmlApplicationContext("SpringDBExample.xml");
     
		
		DataSource ds = (ComboPooledDataSource) appContext.getBean("dataSource");
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
		conn =ds.getConnection();
		stmt =conn.prepareStatement("SELECT * FROM ITEM");
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
				"SELECT * FROM ITEM IT WHERE IT.ITEM_ID = ?",
				new RowMapper<Item>(){
				public Item mapRow(ResultSet rs, int rowNum)
				throws SQLException{
					Item item = new Item();
					item.setId (rs.getLong(1));
					item.setName(rs.getString(2));
					item.setVersion(rs.getInt(3));
				return item;
				}
				},
				1
				);
		System.out.println(item);
		System.out.println(item.getBids());
	}
}