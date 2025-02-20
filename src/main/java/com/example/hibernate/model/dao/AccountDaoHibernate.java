package com.example.hibernate.model.dao;


import java.util.List;

import com.example.hibernate.model.Account;
import com.example.hibernate.model.util.GenericDaoHibernate;

public class AccountDaoHibernate extends GenericDaoHibernate<Account, Integer> implements IAccountDao {
	

	public AccountDaoHibernate() {
		super();
	}

	@Override
	public List<Account> findAccountsByEmpno(Integer empno) {
	
		return getSession().createSelectionQuery("select a from Account a where a.emp.empno = ?1", Account.class)
				.setParameter(1, empno).getResultList();
	}

	
}
