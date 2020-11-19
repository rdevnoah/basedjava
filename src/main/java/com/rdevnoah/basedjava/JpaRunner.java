package com.rdevnoah.basedjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @Autowired
    PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Post post = new Post();

        post.setTitle("this is post title...");

        Comment comment = new Comment();
        comment.setTitle("this is comment title...");
        post.addComment(comment);

        Comment comment2 = new Comment();
        comment2.setTitle("this is comment title2....");
        post.addComment(comment2);

        postRepository.save(post);

        postRepository.findAll().forEach(System.out::println);
    }
}
