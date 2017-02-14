package com.codeup.repositories;

import com.codeup.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by V-Rod on 2/10/17.
 */
// repository with a method for finding users by username.
@Repository
public interface UsersRepository extends CrudRepository<User, Long> {

    // select * from user username = ?
    public User findByUsername(String username);  // this method will tell us if the user exists in the database

}
