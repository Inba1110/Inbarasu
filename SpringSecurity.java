package net.enjoy.springboot.registrationlogin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/index2").permitAll()
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/StdDashboard").permitAll()
                                .requestMatchers("/StdNotification").permitAll()
                                .requestMatchers("/StdAccount").permitAll()
                                .requestMatchers("/StdTeacher").permitAll()
                                .requestMatchers("/TeacherDashboard").permitAll()
                                .requestMatchers("/ExploreTeacher").permitAll()
                                .requestMatchers("/schedule").permitAll()
                                .requestMatchers("/css/**").permitAll()   // Allow access to CSS files
                                .requestMatchers("/js/**").permitAll()    // Allow access to JS files
                                .requestMatchers("/images/**").permitAll() // Allow access to images
                                // .requestMatchers("/std-login").permitAll()
                                // .requestMatchers("/std-dashboardNew").permitAll()
                                .requestMatchers("/users").hasRole("ADMIN")
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                
                                .loginProcessingUrl("/login")
                                
                                .defaultSuccessUrl("/StdDashboard")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}