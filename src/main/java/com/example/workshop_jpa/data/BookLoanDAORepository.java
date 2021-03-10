package com.example.workshop_jpa.data;

import com.example.workshop_jpa.model.BookLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class BookLoanDAORepository implements BookLoanDAO{

    private final EntityManager em;

    @Autowired
    public BookLoanDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public BookLoan findById(Integer integer) {
        return em.find(BookLoan.class, integer);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<BookLoan> findAll() {
        return em.createQuery("SELECT bl FROM BookLoan bl", BookLoan.class)
                .getResultList();
    }

    @Override
    @Transactional
    public BookLoan create(BookLoan bookLoan) {
        em.persist(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan bookLoan) {
        return em.merge(bookLoan);
    }

    @Override
    @Transactional
    public void delete(Integer integer) {
        BookLoan loan = findById(integer);
        if(loan != null){
            em.remove(loan);
        }
    }
}
