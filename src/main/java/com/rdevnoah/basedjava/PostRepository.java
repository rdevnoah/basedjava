package com.rdevnoah.basedjava;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
    Page<Post> findByTitleContains(String title, Pageable pageable);

    long countByTitleContains(String title);

    List<Post> findByTitleStartsWith(String title);

    List<Post> findByTitleNamedQuery(String title);

    @Query("SELECT p FROM Post as p WHERE p.title=?1")
    List<Post> findByTitleNamedQuerySort(String title, Sort sort);
}
