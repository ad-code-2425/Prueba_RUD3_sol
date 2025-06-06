package com.example.hibernate.model.dao;
import java.util.List;

import com.example.hibernate.model.Author;
import com.example.hibernate.model.Book;
import com.example.hibernate.model.util.IGenericDao;

public interface IBookDao extends IGenericDao<Book, Integer>{
    List<Book> findBooksByAuthorId(Integer empno);

    public List<Author> findAuthorsByBookId(Integer accountNo);
    
}
