package com.akartkam.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akartkam.domain.Spitter;
import com.akartkam.domain.Spittle;

@Repository
public class HibernateSpitterDao implements SpitterDao {
	private SessionFactory sessionFactory;

	@Autowired
	public HibernateSpitterDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory; // Конструирует DAO
	}

	private Session currentSession() { // Извлекает текущий
		return sessionFactory.getCurrentSession(); // сеанс из фабрики
	}
  
  public void addSpitter(Spitter spitter) {
	  currentSession().saveOrUpdate(spitter);
  }

  public Spitter getSpitterById(long id) {
    return (Spitter) currentSession().get(Spitter.class, id);
  }

  public void saveSpitter(Spitter spitter) {
	  currentSession().update(spitter);
  }

  public List<Spittle> getRecentSpittle() {
    return currentSession().createQuery("from Splitte").list(); // this isn't right...just a placeholder for now
  }

  public void saveSpittle(Spittle spittle) {
	  currentSession().save(spittle);
  }

  public List<Spittle> getSpittlesForSpitter(
          Spitter spitter) {
    // TODO Auto-generated method stub
    return null;
  }
  

  public Spitter getSpitterByUsername(String username) {
    // TODO Auto-generated method stub
    return null;
  }

  public void deleteSpittle(long id) {
	  currentSession().delete(getSpittleById(id));
  }

  public Spittle getSpittleById(long id) {
    return (Spittle) currentSession().get(Spittle.class, id);
  }
  
  public List<Spitter> findAllSpitters() {
    // TODO Auto-generated method stub
    return null;
  }
}
