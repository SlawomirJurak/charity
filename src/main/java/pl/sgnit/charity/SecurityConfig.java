package pl.sgnit.charity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.sgnit.charity.service.AppUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin@charity.pl")
                .password(passwordEncoder().encode("admin1234"))
                .roles("ADMIN");
        auth.userDetailsService(appUserDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/donation").authenticated()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/user/postRegister").permitAll()
                .antMatchers("/user/activate/**").permitAll()
                .antMatchers("/user/{id:[\\d+]}").authenticated()
                .antMatchers("/user/change/{id:[\\d+]}").authenticated()
                .antMatchers("/user/changePassword/{id:[\\d+]}").authenticated()
                .antMatchers("/user/**").hasAuthority("ROLE_ADMIN")
                .and().formLogin()
                .loginPage("/login")
                .and().logout().logoutSuccessUrl("/");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AppUserDetailsService appUserDetailsService() {
        return new AppUserDetailsService();
    }
}
