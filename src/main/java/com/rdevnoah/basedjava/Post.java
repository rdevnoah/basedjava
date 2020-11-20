package com.rdevnoah.basedjava;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

@NamedQueries({
        @NamedQuery(name = "Post.findByTitleNamedQuery", query = "SELECT p from Post as p where p.title=?1"),
})
public class Post {
    @Id @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                '}';
    }
}
