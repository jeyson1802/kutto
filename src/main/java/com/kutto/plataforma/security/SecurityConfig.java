package com.kutto.plataforma.security;

import com.kutto.plataforma.exception.MyAuthenticationSuccessHandler;
import com.kutto.plataforma.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    private String[] resources = new String[] {
            "/js/**",
            "/css/**",
            "/img/**",
            "/js/**",
            "/language/**",
            "/fonts/**",
            "/assets/**",
            "/favicon.png",
            "/manifest.json"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(resources)
                    .permitAll()
                    .antMatchers("/", "/productos", "/producto","/reserva","/buscar", "/adopcion", "/forbidden", "/login")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                .formLogin()
                    .loginProcessingUrl("/signin")
                    .loginPage("/login").permitAll()
                    .successHandler(new MyAuthenticationSuccessHandler())
                    .usernameParameter("nombreUsuario")
                    .passwordParameter("password")
                    .and()
                .exceptionHandling().
                    accessDeniedPage("/forbidden")
                    .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                    .and()
                .sessionManagement()
                    .maximumSessions(1)
                    .expiredUrl("/login?expiredSession")
                    .and()
                .invalidSessionUrl("/login?logout")
                    .and()
                .csrf()
                    .disable();
    }
}
