package com.example.wearme_individualproject.security;

import com.example.wearme_individualproject.filter.CustomAuthenticationFilter;
import com.example.wearme_individualproject.filter.CustomAuthorisationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request->new CorsConfiguration().applyPermitDefaultValues());
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/user/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/user/login/**", "/user/signUp/**",
                "/customer/token/refresh/**", "/communityManager/token/refresh/**",
                "/salesManager/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/user/signUp").permitAll();
        http.authorizeRequests().antMatchers(POST, "/user/login").permitAll();
        http.authorizeRequests().antMatchers(GET, "/customer/**").hasAnyAuthority("CUSTOMER");
        http.authorizeRequests().antMatchers(GET, "/salesManager/**").hasAnyAuthority("SALESMANAGER");
        http.authorizeRequests().antMatchers(GET, "/discount/**").hasAnyAuthority("SALESMANAGER");
        http.authorizeRequests().antMatchers(GET, "/product/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/shoppingCart/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/webSocket/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/favouriteItem/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/order/**").hasAnyAuthority("CUSTOMER");
        http.authorizeRequests().antMatchers(GET, "/paymentInfo/**").hasAnyAuthority("CUSTOMER");
        http.authorizeRequests().antMatchers(POST, "/paymentInfo/**").hasAnyAuthority("CUSTOMER");
        http.authorizeRequests().antMatchers(DELETE, "/shoppingCart/deleteItem/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/communityManager/**").hasAnyAuthority("COMMUNITYMANAGER");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000/","http://localhost:8081/" ));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList(
                "Accept", "Origin", "Content-Type", "Depth", "User-Agent", "If-Modified-Since,",
                "Cache-Control", "Authorization", "X-Req", "X-File-Size", "X-Requested-With", "X-File-Name"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
