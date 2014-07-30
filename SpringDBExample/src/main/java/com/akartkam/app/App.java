package com.akartkam.app;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import com.akartkam.domain.AddressEntity;
import com.akartkam.domain.Spitter;
import com.akartkam.domain.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@Repository
public class App {
	 
	private static final String SPITTER_XML = "./spitter-jaxb.xml";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Session currentSession() { // Извлекает текущий
		return sessionFactory.openSession(); // сеанс из фабрики
	}
	
	public static void main(String[] args) throws  JAXBException, IOException {

	
		

		ApplicationContext appContext = new ClassPathXmlApplicationContext("SpringDBExample.xml");
     
		App app = (App) appContext.getBean(App.class);
		
		
		/*DataSource ds = (ComboPooledDataSource) appContext.getBean("dataSource");
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
		JdbcTemplate jdbcTemplate = (JdbcTemplate) appContext.getBean("jdbcTemplate");
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
		
		Spitter spitter = new Spitter();
		spitter.setId(1L);
		spitter.setEmail("akartkam@gmail.com");
		spitter.setFullName("akartkam");
		spitter.setUsername("akartkam");
		spitter.setPassword("password");
		
		JAXBContext context = JAXBContext.newInstance(Spitter.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    
	 // Write to System.out
	    m.marshal(spitter, System.out);

	    // Write to File
	    m.marshal(spitter, new File(SPITTER_XML));*/
 
		app.currentSession().beginTransaction();
		
		AddressEntity addr = new AddressEntity("Street", "123456", "City");
		
		
		User user = new User("username", "password");
		user.setShippingAddress(addr);
		addr.setUser(user);
		app.currentSession().save(user);
		app.currentSession().save(addr);		
		
		app.currentSession().getTransaction().commit();
		
		app.currentSession().close();
	
	}
}