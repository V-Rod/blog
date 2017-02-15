package com.codeup.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by V-Rod on 2/10/17.
 */
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Enter a username")
    private String username;

    @Column(nullable = false)
    @Email(message = "Enter a valid email address")
    @NotBlank(message = "Your email cannot be empty")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Your password cannot be empty")
    @Size(min = 8, message="Your password should have at least 8 characters")
    @JsonIgnore // this is to ignore sensitive information
    private String password;

    @OneToMany(cascade =CascadeType.ALL, mappedBy = "user") // defined at the object level
    @JsonBackReference
    List<Post> posts;  // these are all the posts created by this user

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    // pattern
    // copy constructor -> an alternative to clone
    public User(User user) {
        id = user.id;
        username = user.username;
        password = user.password;
        email = user.email;
        posts = user.posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
