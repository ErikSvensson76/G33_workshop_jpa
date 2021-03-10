package com.example.workshop_jpa.data;

import com.example.workshop_jpa.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;
@Repository
public class AppUserDAORepository implements AppUserDAO{

    private final EntityManager em;

    @Autowired
    public AppUserDAORepository(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findById(Integer id) {
        return em.find(AppUser.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<AppUser> findAll() {
        return em.createQuery("SELECT user FROM AppUser user", AppUser.class)
                .getResultList();
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        em.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {
        return em.merge(appUser);
    }

    @Override
    @Transactional
    public void delete(Integer integer) {
        AppUser appUser = findById(integer);
        if(appUser != null){
            em.remove(appUser);
        }
    }
}
