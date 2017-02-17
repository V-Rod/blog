package com.codeup.controllers;

import com.codeup.models.User;
import com.codeup.models.UserRole;
import com.codeup.repositories.RolesRepository;
import com.codeup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by V-Rod on 2/13/17.
 */
// Controller for the login page
@Controller
public class AuthenticationController {

    private UsersRepository repository;  //
    private PasswordEncoder encoder;    //

    @Autowired
    public AuthenticationController(UsersRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Autowired
    RolesRepository userRoles;

    @GetMapping("/login")
    public String showLoginForm() {
//        System.out.println(new BCryptPasswordEncoder().encode("codeup"));
        return "login";
    }

    @GetMapping("/register")
    public String showForm(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "users/create";
    }

    @PostMapping("users/create")
        public String registerUser(
                @Valid User user, // create the user from the input values, and apply validations
                Errors validation,
                Model viewModel,
                @RequestParam(name = "password_confirm") String passwordConfirmation
    ) {
        if (!passwordConfirmation.equals(user.getPassword())) {
            validation.rejectValue(
                    "password",
                    "user.password",
                    "Your passwords do not match!"
            );
        }

        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("user", user);
            return "users/create";
        }

        String hashedPassword = encoder.encode(user.getPassword()); // hash the users password
        user.setPassword(hashedPassword);

        User newUser = new User(repository.save(user));  // save the user to the database

        UserRole ur = new UserRole();  // assign roles to the user after it was created
        ur.setRole("ROLE_USER");
        ur.setUserId(newUser.getId());
        userRoles.save(ur);

        viewModel.addAttribute("user", user);
        return "redirect:/login";  // redirect the user to the login page
    }
}