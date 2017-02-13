package com.codeup.models;

import javax.persistence.*;

/**
 * Created by V-Rod on 2/8/17.
 */
@Entity
@Table(name="posts")
public class Post {

    // Table primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 2000)
    private String body;

    // will define your foreign key
    // I will user a convention `the_other_table_name_id`
    @ManyToOne
    @JoinColumn(name = "user_id") // define at the table level (foreign key)
    private User user;  //owner, author

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



}
