package com.rdevnoah.basedjava;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void crudRepository() {
        // Given
        Post post = new Post();
        post.setTitle("hello spring boot common");
        assertThat(post.getId()).isNull();


        // When
        Post newPost = postRepository.save(post);

        // Then
        assertThat(newPost.getId()).isNotNull();

        // When
        List<Post> posts = postRepository.findAll();

        // Then
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts).contains(newPost);

        // When
        Page<Post> page = postRepository.findAll((PageRequest.of(0, 10)));

        // Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        // When
        postRepository.findByTitleContains("spring", PageRequest.of(0, 10));

        // Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        // When
        long spring = postRepository.countByTitleContains("spring");

        // Then
        assertThat(spring).isEqualTo(1);
    }

    @Test
    public void 커스텀레포지토리테스트() {
        Post post = new Post();
        Comment comment = new Comment();
        comment.setTitle("comment title......");

        post.addComment(comment);
        postRepository.save(post);

        List<Post> resultPost = postRepository.findMyPost();

        assertThat(resultPost.size()).isEqualTo(1);

    }

    @Test
    public void 저장테스트() {
        Post post = new Post();
        post.setTitle("jpa noah kim");
        Post savedPost = postRepository.save(post);      // persistent context에 persis 함. 그리고 persist 상태의 객체를 리턴함.

        assertThat(entityManager.contains(post)).isTrue();          // 저장되어, persis화 되었으므로 true
        assertThat(entityManager.contains(savedPost)).isTrue();     // persist화 되어 persistent context에서 가져왔으므로 true
        assertThat(post == savedPost);                       // 두개는 같을수도 있고, 다를 수도 있지만, 여기서는 같은 레퍼런스임.

        Post updatePost = new Post();
        updatePost.setId(post.getId());             // id 값 이미 있는 것이므로
        updatePost.setTitle("updated jpa noah kim");

        // persistent context에 **merge** 됨. 그리고 persist 상태의 객체를 리턴함. 매개변수의 개체는 merge 된 객체이므로 persist 상태는 아님
        Post finalUpdatedPost = postRepository.save(updatePost);

        // persist 상태의 객체를 사용하면 update 가 자동으로 일어난다.
        finalUpdatedPost.setTitle("check updated");

        List<Post> postList = postRepository.findAll();

        postList.forEach(System.out::println);
        assertThat(postList.size()).isEqualTo(1);

    }

}
