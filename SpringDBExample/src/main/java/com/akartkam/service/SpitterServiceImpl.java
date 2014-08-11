package com.akartkam.service;

import static java.lang.Math.*;
import static java.util.Collections.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.akartkam.domain.Spitter;
import com.akartkam.domain.Spittle;
import com.akartkam.persistence.SpitterDao;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class SpitterServiceImpl implements SpitterService {
  
  @Autowired
  private SpitterDao spitterDao; 
  //<start id="java_addSpittle" /> 
  @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
  public void saveSpittle(final Spittle spittle) {
    /*txTemplate.execute(new TransactionCallback<Void>() {
      public Void doInTransaction(TransactionStatus txStatus) {
        try {
        spitterDao.saveSpittle(spittle);
        } catch (RuntimeException e) {
          txStatus.setRollbackOnly();
          throw e;
        }
        return null;
      }      
    });*/
	  spitterDao.saveSpittle(spittle);
  }
  //<end id="java_addSpittle" />


  public List<Spittle> getRecentSpittles(int count) {
    List<Spittle> recentSpittles = 
        spitterDao.getRecentSpittle();
    
    reverse(recentSpittles);
    
    return recentSpittles.subList(0, 
            min(49, recentSpittles.size()));
  }
  
  @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
  public void saveSpitter(final Spitter spitter) {
    /*txTemplate.execute(new TransactionCallback<Void>() {
      public Void doInTransaction(TransactionStatus txStatus) {
        if(spitter.getId() == null) {
          spitterDao.addSpitter(spitter);
        } else {
          spitterDao.saveSpitter(spitter);
        }

        return null;
      }
    });*/
	  spitterDao.addSpitter(spitter);
  }
  
  public Spitter getSpitter(long id) {
    // TODO Auto-generated method stub
    return null;
  }
  
  public Spitter getSpitter(String username) {
    return spitterDao.getSpitterByUsername(username);
  }

  public void startFollowing(Spitter follower, Spitter followee) {
    // TODO Auto-generated method stub
    
  }
  
  public List<Spittle> getSpittlesForSpitter(
          Spitter spitter) {
    return spitterDao.getSpittlesForSpitter(spitter);
  }
 
  @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
  public void deleteSpittle(long id) {
    spitterDao.deleteSpittle(id);
  }
  
  public Spittle getSpittleById(final long id) {
	  
	/* return (Spittle) txTemplate.execute(new TransactionCallback<Spittle>() {
	      public Spittle doInTransaction(TransactionStatus txStatus) {
	    	  Spittle spittle;
	        try {
	        	 spittle = spitterDao.getSpittleById(id);
	        } catch (RuntimeException e) {
	          txStatus.setRollbackOnly();
	          throw e;
	        }
			return spittle;
	      }      
	    });*/
	   return spitterDao.getSpittleById(id);
	  }	  
   

  
  //private SpitterDao spitterDao;
  public void setSpitterDao(SpitterDao spitterDao) {
    this.spitterDao = spitterDao;
  }
  
  private TransactionTemplate txTemplate;
  public void setTransactionTemplate(TransactionTemplate txTemplate) {
    this.txTemplate = txTemplate;
  }

  public List<Spittle> getSpittlesForSpitter(
          String username) {
    // TODO Auto-generated method stub
    return null;
  }
  
  public List<Spitter> getAllSpitters() {
    return spitterDao.findAllSpitters();
  }

}
