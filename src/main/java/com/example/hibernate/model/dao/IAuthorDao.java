package com.example.hibernate.model.dao;
import com.example.hibernate.model.Author;
import com.example.hibernate.model.util.IGenericDao;

public interface IAuthorDao extends IGenericDao<Author, Integer>{
    public Author findBy(String firstName, String lastName, String middleName);
}
