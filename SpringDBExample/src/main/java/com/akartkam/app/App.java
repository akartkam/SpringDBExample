package com.akartkam.app;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import com.akartkam.domain.AddressEntity;
import com.akartkam.domain.Bid;
import com.akartkam.domain.Category;
import com.akartkam.domain.Item;
import com.akartkam.domain.Spitter;
import com.akartkam.domain.Spittle;
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

import com.akartkam.persistence.SessionFactory1;
import com.akartkam.service.SpitterService;
import com.akartkam.service.SpitterServiceImpl;

import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;


public class App {
	 
	private static final String SPITTER_XML = "./spitter-jaxb.xml";
	
	
	
	/*public Session currentSession() { // Извлекает текущий
		return sessionFactory.openSession(); // сеанс из фабрики
	}*/
	
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
 
		  
		
		Session session = appContext.getBean(SessionFactory1.class).currentSession();
		Transaction tx = session.beginTransaction();
		
		AddressEntity addr = new AddressEntity("Street", "123456", "City");
		User user = new User("username", "password");
		
		user.setShippingAddress(addr);
		addr.setUser(user);
		session.save(user);
		session.save(addr);	
		
		addr = new AddressEntity("Street1", "2345678", "City1");
		
		user = new User("username1", "password1");
		
		user.setShippingAddress(addr);
		addr.setUser(user);
		session.save(user);
		session.save(addr);	
		
		
        tx.commit();
        //session.close();
        
        //
        tx = session.beginTransaction();
        Item item = new Item("Vova");
        Bid bid1 = new Bid(item);
        Bid bid2 = new Bid(item);
        
        item.addBid(bid1);
        item.addBid(bid1);
        
        
        session.save(item);
        tx.commit();
        session.close();
        
        SpitterService spitterServiceImpl = appContext.getBean(SpitterService.class);
        //HibernateTransactionManager tm = appContext.getBean(HibernateTransactionManager.class);
        
        //TransactionTemplate txTemplate = new TransactionTemplate(tm);
        
        //spitterServiceImpl.setTransactionTemplate(txTemplate);
        
        Spitter newSpitter = new Spitter();
        newSpitter.setUsername("testuser");
        newSpitter.setPassword("password");
        newSpitter.setFullName("Michael McTest");
        
        Spittle newSpittle = new Spittle();
        newSpittle.setSpitter(newSpitter);
        newSpittle.setText("Some text");
        newSpittle.setWhen(new Date());
                
        
        spitterServiceImpl.saveSpitter(newSpitter);
        spitterServiceImpl.saveSpittle(newSpittle);
        
        newSpittle = spitterServiceImpl.getSpittleById(4L);
        if (newSpittle != null)
        newSpitter = newSpittle.getSpitter();
        
        //Spitter otherSpitter = spitterServiceImpl.get
        System.out.println(newSpittle);
        System.out.println(newSpitter);
        
        session = appContext.getBean(SessionFactory1.class).currentSession();
		tx = session.beginTransaction();
		
		Category category = new Category("Category");
		Category category1 = new Category("Category1");
		Category category2 = new Category("Category2");
		
		item = new Item("Item");
		Item item1 = new Item("Item1");
		Item item2 = new Item("Item2");
		Item item3 = new Item("Item3");

		/*category.addItem(item);
		category.addItem(item1);
		category1.addItem(item2);
		category2.addItem(item3);
		*/
		category.setItems(Arrays.asList(item, item1));
		category1.setItems(Arrays.asList(item2));
		category2.setItems(Arrays.asList(item3));
		
		session.save(item);
		session.save(item1);
		session.save(item2);
		session.save(item3);
		session.save(category);
		session.save(category1);
		session.save(category2);
		
		
        tx.commit();
        session.close();
	
	}
}