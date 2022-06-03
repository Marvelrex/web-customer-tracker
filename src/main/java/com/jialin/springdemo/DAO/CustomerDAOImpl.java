package com.jialin.springdemo.DAO;

import java.util.List;

import com.jialin.springdemo.Utility.SortUtils;
import com.jialin.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Customer> getCustomers(int sortField) {
		String field = "lastName";
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		switch (sortField){
			case SortUtils.FIRST_NAME:
				field = "firstName";
				break;
			case SortUtils.LAST_NAME:
				field = "lastName";
				break;
			case SortUtils.EMAIL:
				field = "email";
				break;
		}
		// create a query sort by last name
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer customer " +
						"order by " + field, Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}

	@Override
	public void addCustomer(Customer customer) {
		if (customer == null || customer.getFirstName() == null ||customer.getLastName()==null){return;}
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();

		return currentSession.get(Customer.class,theId);
	}

	@Override
	public void deleteCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.delete(getCustomer(theId));
	}

	@Override
	public List<Customer> searchCustomer(String name) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Customer>customers;
		if (name!=null && name.trim().length()>0){
			customers = currentSession.createQuery("from Customer where lower(firstName) like :Name or lower(lastName) like:Name ",Customer.class);
			customers.setParameter("Name",name);
		}else {
			customers = currentSession.createQuery("from Customer ",Customer.class);
		}


		return customers.getResultList();
	}

}






