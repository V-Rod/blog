package com.codeup.repositories;

import com.codeup.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by V-Rod on 2/10/17.
 */
@Repository
public interface UsersRepository extends CrudRepository<User, Long> {

}
