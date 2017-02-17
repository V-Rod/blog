package com.codeup;

import com.codeup.services.UserDetailsLoader;
import com.codeup.services.UserWithRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// configuration class to determine which pages will require authentication, and which pages are available to anyone.
// We'll also define the login page, as well as the hashing algorithm we'll use to store passwords.
/**
 * Created by V-Rod on 2/13/17.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = UserWithRoles.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsLoader userDetails;

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/posts") // user's home page, it can be any URL
                    .permitAll() // Anyone can go to the login page
                .and()
                    // non logged-in users
                    .authorizeRequests()
                    .antMatchers("/", "/logout") // anyone can see the home and logout page
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/login?logout") // append a query string value
                .and()
                    // restricted area
                    .authorizeRequests()
                    // only authenticated users can create posts and this is where you add the pages that you want protected
                    .antMatchers("/posts/create")  //"/posts/edit"
                    .authenticated()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }



}