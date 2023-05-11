package com.ecommerce.agroproducts.authentication;
import com.ecommerce.agroproducts.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
               // .antMatchers(HttpMethod.POST, "/api/v1/create-user").hasAuthority("addUser")
                .antMatchers(HttpMethod.POST, "/api/v1/add-comment").hasAuthority("addUser")
                .antMatchers(HttpMethod.POST, "/api/v1/add-reply").hasAuthority("addUser")
                .antMatchers(HttpMethod.GET, "/api/v1/all-users").hasAuthority("addUser")
                .antMatchers(HttpMethod.GET, "/api/v1/filterTickets").hasAuthority("addUser")
                .antMatchers(HttpMethod.GET, "/api/v1/getByTicketId/**").hasAuthority("addUser")
                .antMatchers(HttpMethod.GET,"/api/v1/get-notifications").hasAuthority("addUser")
                .antMatchers(HttpMethod.GET, "/api/v1/get-tickets").hasAuthority("addUser")
                .antMatchers(HttpMethod.GET, "/api/v1/get-daily-payment").hasAuthority("addUser")
                .antMatchers(HttpMethod.GET, "/api/v1/filter-daily-payment/**").hasAuthority("addUser")
                .antMatchers(HttpMethod.GET, "/api/v1/getLoans/**").hasAnyAuthority("addUser", "ViewTransaction", "viewClientTransactions")
                .antMatchers(HttpMethod.GET, "/qsend-api/v1/transactions/findByTransactionReference/**").hasAnyAuthority("ViewAllTransactions", "ViewTransaction", "viewClientTransactions")
                .antMatchers(HttpMethod.GET, "/qsend-api/v1/transactions/findByClient/**").hasAnyAuthority("ViewAllTransactions", "viewClientTransactions")
                .antMatchers("/api/v1/auth/token", "/api/v1/users/**","/api/v1/add-Reminder","/api/v1/daily-payment", "/qsend-api/v1/country/**").permitAll().anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).
                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
