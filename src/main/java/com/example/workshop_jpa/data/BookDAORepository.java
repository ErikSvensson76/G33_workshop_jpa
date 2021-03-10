package com.example.workshop_jpa.data;

import com.example.workshop_jpa.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class BookDAORepository implements BookDAO{

    private final EntityManager em;

    @Autowired
    public BookDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(Integer integer) {
        return em.find(Book.class, integer);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Book> findAll() {
        return em.createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Book create(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    @Transactional
    public Book update(Book book) {
        return em.merge(book);
    }

    @Override
    @Transactional
    public void delete(Integer integer) {
        Book book = findById(integer);
        if(book != null){
            em.remove(book);
        }
    }
}
