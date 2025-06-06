package com.example.hibernate.model.servicio;

import java.util.List;

import com.example.hibernate.model.Author;
import com.example.hibernate.model.Book;
import com.example.hibernate.model.dao.AuthorDaoHibernate;
import com.example.hibernate.model.dao.BookDaoHibernate;
import com.example.hibernate.model.dao.IAuthorDao;
import com.example.hibernate.model.dao.IBookDao;
import com.example.hibernate.model.util.exceptions.InstanceNotFoundException;

public class BookServicio implements IBookServicio {

    private IBookDao bookDao;

    private IAuthorDao authorDao;

    public BookServicio() {
        this.bookDao = new BookDaoHibernate();
        this.authorDao = new AuthorDaoHibernate();
       
    }

    @Override
    public Author findAuthorById(int authorId) throws InstanceNotFoundException {
        return this.authorDao.executarDentroTransaccion(() -> {
            return this.authorDao.find(authorId);
        });

    }

  
   

    @Override
    public List<Book> getBooksByAuthorId(Integer authorId) {

        return this.bookDao.executarDentroTransaccion(() -> {
            return this.bookDao.findBooksByAuthorId(authorId);
        });
    }

  


    @Override
    public Author addAuthorToBook(int bookId, Author author) {

        return bookDao.executarDentroTransaccion(() -> {

            authorDao.create(author);

            Book book = bookDao.find(bookId);

            book.addAuthor(author);

            //Actualizamos o lado propietario
            bookDao.update(book);
            return author;

        });
    }

    @Override
    public List<Author> getAutoresByBookId(int bookId) {
        return this.bookDao.executarDentroTransaccion(() -> {
            return bookDao.findAuthorsByBookId(bookId);
        });
    }

}
