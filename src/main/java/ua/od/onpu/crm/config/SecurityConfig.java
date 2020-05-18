package ua.od.onpu.crm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(
                        "/api/children/**",
                        "/api/contacts/**",
                        "/api/customers/**",
                        "/api/deals/**",
                        "/api/expeditions/**",
                        "/api/payments/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.GET, "/api/employees/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .and().logout().logoutSuccessUrl("/login");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{bcrypt}$2y$12$4ZJh/6rKkvwJVcz4eV1uh.ns8q0U0bVgkvAWzjDPjXlBEMRvQBZc6")
                .roles("ADMIN")
                .and()
                .withUser("manager")
                .password("{bcrypt}$2y$12$AD0/Suz8az.7EWmhxQZui.YMBM8Pq4B/c9CYg06I/h/s03c/330Km")
                .roles("MANAGER");
    }
}
