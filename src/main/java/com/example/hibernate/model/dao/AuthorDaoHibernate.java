package com.example.hibernate.model.dao;


import com.example.hibernate.model.Author;
import com.example.hibernate.model.util.GenericDaoHibernate;
public class AuthorDaoHibernate extends GenericDaoHibernate<Author, Integer> implements IAuthorDao {
	

	public AuthorDaoHibernate() {
		super();
	}

	@Override
	public Author findBy(String firstName, String lastName, String middleName) {
		return (Author) this.getSession().createSelectionQuery(
				"select a from Author a where a.firstName like ?1 and a.lastName like ?2 and (a.middelName is null or a.middleName like ?2)",
				Author.class);
	}

	
}
