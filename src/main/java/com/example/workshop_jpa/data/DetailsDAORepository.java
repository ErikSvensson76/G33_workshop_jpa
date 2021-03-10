package com.example.workshop_jpa.data;

import com.example.workshop_jpa.model.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class DetailsDAORepository implements DetailsDAO{

    private final EntityManager em;

    @Autowired
    public DetailsDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public Details findById(Integer integer) {
        return em.find(Details.class, integer);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Details> findAll() {
        return em.createQuery("SELECT d FROM Details d", Details.class).getResultList();
    }

    @Override
    @Transactional
    public Details create(Details details) {
        em.persist(details);
        return details;
    }

    @Override
    @Transactional
    public Details update(Details details) {
        return em.merge(details);
    }

    @Override
    @Transactional
    public void delete(Integer integer) {
        Details details = findById(integer);
        if(details != null){
            em.remove(details);
        }

    }
}
