package com.akartkam.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SessionFactory1 {
	
	private SessionFactory sessionFactory;

	
	public SessionFactory1() {

	}

	@Autowired
	public SessionFactory1(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session currentSession() { // Извлекает текущий
		return sessionFactory.getCurrentSession(); // сеанс из фабрики
	}

}
