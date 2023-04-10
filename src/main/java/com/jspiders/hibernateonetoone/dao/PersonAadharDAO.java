package com.jspiders.hibernateonetoone.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



import com.jspiders.hibernateonetoone.dto.AadharDTO;
import com.jspiders.hibernateonetoone.dto.PersonDTO;

public class PersonAadharDAO {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	
	private static void openConnection() {
		
		factory=Persistence.createEntityManagerFactory("aadhar");
		manager=factory.createEntityManager();
		transaction=manager.getTransaction();
	}
	
	private static void closeConnection() {
		
			if (factory!=null) {
				factory.close();
			}
			if (manager!=null) {
				manager.close();
			}
			if (transaction.isActive()) {
				transaction.rollback();
			}
	
	}
	
	public static void main(String[] args) {
		try {
			
			openConnection();
			
			transaction.begin();
			
			AadharDTO aadhar=new AadharDTO();
			aadhar.setId(1);
			aadhar.setAadhar_no(123456789012l);
			aadhar.setDate_of_issue("15-Nov-2020");
			manager.persist(aadhar);
			
			PersonDTO person=new PersonDTO();
			person.setId(1);
			person.setName("Bharat");
			person.setCity("Pune");
			person.setContact(7020202020l);
			person.setAadhar(aadhar);
			manager.persist(person);
			
			transaction.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection();
		}
	}
}
