package com.example.workshop_jpa;

import com.example.workshop_jpa.data.*;
import com.example.workshop_jpa.model.AppUser;
import com.example.workshop_jpa.model.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class CommandLine implements CommandLineRunner {

    private final AppUserDAO appUserDAO;
    private final AuthorDAO authorDAO;
    private final BookDAO bookDAO;
    private final BookLoanDAO bookLoanDAO;
    private final DetailsDAO detailsDAO;


    @Autowired
    public CommandLine(AppUserDAO appUserDAO, AuthorDAO authorDAO, BookDAO bookDAO, BookLoanDAO bookLoanDAO, DetailsDAO detailsDAO) {
        this.appUserDAO = appUserDAO;
        this.authorDAO = authorDAO;
        this.bookDAO = bookDAO;
        this.bookLoanDAO = bookLoanDAO;
        this.detailsDAO = detailsDAO;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Details details = new Details(0, "erik.svensson@lexicon.se", "Erik Svensson", LocalDate.parse("1976-09-11"));
        AppUser appUser = new AppUser(0, "terminator", "hastalavista666", LocalDate.now(), details);

        AppUser persisted = appUserDAO.create(appUser);

        System.out.println(persisted);
        System.out.println(persisted.getUserDetails());

    }
}
