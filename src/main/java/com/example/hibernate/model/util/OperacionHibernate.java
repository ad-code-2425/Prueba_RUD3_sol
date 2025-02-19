package com.example.hibernate.model.util;

@FunctionalInterface
public interface OperacionHibernate<R> {
    R executar() throws Exception;
}