package com.example.hibernate.model.dao;


import java.util.List;

import com.example.hibernate.model.Account;
import com.example.hibernate.model.Empleado;
import com.example.hibernate.model.util.GenericDaoHibernate;

public class AccountDaoHibernate extends GenericDaoHibernate<Account, Integer> implements IAccountDao {
	

	public AccountDaoHibernate() {
		super();
	}

	@Override
	public List<Account> findAccountsByEmpno(Integer empno) {
	
		return getSession().createSelectionQuery("select a from Account a join a.employees e where e.empno = ?1", Account.class)
				.setParameter(1, empno).getResultList();
	}

	@Override
	public List<Empleado> findTitularesByAccountId(Integer accountNo) {
		return getSession().createSelectionQuery("select a.employees from Account a where a.accountno = ?1", Empleado.class)
		.setParameter(1, accountNo)
		.getResultList();
	}

	
}
