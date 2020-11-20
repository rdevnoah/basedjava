package com.rdevnoah.basedjava;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
    Page<Post> findByTitleContains(String title, Pageable pageable);

    long countByTitleContains(String title);

    List<Post> findByTitleStartsWith(String title);

    List<Post> findByTitleNamedQuery(String title);
}
