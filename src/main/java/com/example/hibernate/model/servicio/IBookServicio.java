package com.example.hibernate.model.servicio;

import java.util.List;

import com.example.hibernate.model.Author;
import com.example.hibernate.model.Book;
import com.example.hibernate.model.util.exceptions.InstanceNotFoundException;

public interface IBookServicio {

    public Author findAuthorById(int authorId) throws InstanceNotFoundException; 

    public List<Book> getBooksByAuthorId(Integer authorId); 

   

    public List<Author> getAutoresByBookId(int accId);

    public Author addAuthorToBook(int bookId, Author author);

}
