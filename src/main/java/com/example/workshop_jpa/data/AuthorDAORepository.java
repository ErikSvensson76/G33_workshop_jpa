package com.example.workshop_jpa.data;

import com.example.workshop_jpa.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class AuthorDAORepository implements AuthorDAO{

    private final EntityManager em;

    @Autowired
    public AuthorDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(Integer integer) {
        return em.find(Author.class, integer);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Author> findAll() {
        return em.createQuery("SELECT a FROM Author a", Author.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Author create(Author author) {
        em.persist(author);
        return author;
    }

    @Override
    @Transactional
    public Author update(Author author) {
        return em.merge(author);
    }

    @Override
    @Transactional
    public void delete(Integer integer) {
        Author author = findById(integer);
        if(author != null){
            em.remove(author);
        }
    }
}
