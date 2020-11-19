package com.rdevnoah.basedjava;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Post post = new Post();
        post.setTitle("this is title...");

        Comment comment = new Comment();
        comment.setTitle("comment title...");
        post.addComment(comment);

        Session session = entityManager.unwrap(Session.class);

        session.save(post);
        //session.save(comment);
    }
}
