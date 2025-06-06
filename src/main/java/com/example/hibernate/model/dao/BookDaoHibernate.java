package com.example.hibernate.model.dao;


import java.util.List;

import com.example.hibernate.model.Author;
import com.example.hibernate.model.Book;
import com.example.hibernate.model.util.GenericDaoHibernate;

public class BookDaoHibernate extends GenericDaoHibernate<Book, Integer> implements IBookDao {
	

	public BookDaoHibernate() {
		super();
	}

	@Override
	public List<Book> findBooksByAuthorId(Integer authorId) {
	
		return getSession().createSelectionQuery("select b from Book b join b.authors aut where aut.id = ?1", Book.class)
				.setParameter(1, authorId).getResultList();
	}

	@Override
	public List<Author> findAuthorsByBookId(Integer bookId) {
		return getSession().createSelectionQuery("select b.authors from Book b where b.bookId = ?1", Author.class)
		.setParameter(1, bookId)
		.getResultList();
	}

	
}
