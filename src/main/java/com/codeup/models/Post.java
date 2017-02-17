package com.codeup.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Column(nullable = false, length = 2000)
    @NotBlank(message = "Description cannot be empty.")
    @Size(min = 5, message = "Description must be at least 5 characters long.")
    private String body;

    @Column
    private String image;


    //-------------------CONSTRUCTORS----------------//

    // will define your foreign key
    // I will user a convention `the_other_table_name_id`
    @ManyToOne
    @JoinColumn(name = "user_id") // define at the table level (foreign key)
    @JsonManagedReference
    private User user;  //owner, author

    public Post(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post() {

    }


    //--------- GETTERS AND SETTERS -------------//

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
