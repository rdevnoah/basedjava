package com.rdevnoah.basedjava;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() {
        Comment comment = new Comment();
        comment.setTitle("this is test comment title");
        commentRepository.save(comment);

        List<Comment> commentList = commentRepository.findAll();
        assertThat(commentList.size()).isEqualTo(1);
    }

    @Test
    public void 포함여부테스트() {
        Comment comment = new Comment();
        comment.setTitle("spring data jpa");
        commentRepository.save(comment);

        List<Comment> comments = commentRepository.findByTitleContains("spring");
        assertThat(comments.size()).isEqualTo(1);
    }
}