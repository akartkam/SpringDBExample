package com.akartkam.app;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class App {
	 
	
	public static void main(String[] args) throws Exception {

		

		ApplicationContext appContext = new ClassPathXmlApplicationContext("SpringDBExample.xml");
     
		DataSource ds = (ComboPooledDataSource) appContext.getBean("dataSource");
		//CustomerBo customer = (CustomerBo) appContext.getBean("customerBo");
		//customer.addCustomer();
		
		//customer.addCustomerReturnValue();
		
		//customer.addCustomerThrowException();
		
		//customer.addCustomerAround("mkyong");
		

		
	   // Performer eddie = (Performer) appContext.getBean("eddie");
	   // eddie.perform();
	   // ((Contestant)eddie).receiveAward();
	    

	}
}