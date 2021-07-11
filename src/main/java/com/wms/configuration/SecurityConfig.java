package com.wms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.wms.auth.CustomAuthenticationProvider;
import com.wms.auth.filter.CustomAuthenticationFilter;
import com.wms.auth.filter.CustomAuthorizationFilter;
import com.wms.bean.enu.UserRole;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/account/reg").permitAll()
                .antMatchers("/api/account/val").permitAll()
                .antMatchers("/api/account/sub").hasAnyRole(
                		UserRole.SELLER.asAuth(),
                		UserRole.STORAGE.asAuth())
                .antMatchers("/api/account/**").hasAnyRole(
                		UserRole.SELLER.asAuth(),
                		UserRole.SELLER_SUB.asAuth(),
                		UserRole.STORAGE.asAuth(),
                		UserRole.STORAGE_SUB.asAuth())
                .antMatchers("/api/item/**").hasAnyRole(
                		UserRole.SELLER.asAuth(),
                		UserRole.STORAGE.asAuth())
                .and()
                .addFilter(new CustomAuthenticationFilter(authenticationManager()))
                .addFilter(new CustomAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
