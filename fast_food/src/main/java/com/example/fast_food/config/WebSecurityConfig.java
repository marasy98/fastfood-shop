package com.example.fast_food.config;

import com.example.fast_food.jwt.JwtAuthenticationFilter;
import com.example.fast_food.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountService accountService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager Bean
        return super.authenticationManagerBean();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(accountService) // Cung cáp userservice cho spring security
                .passwordEncoder(ApplicationConfig.passwordEncoder()); // cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/login","/api/register","/api/image/{fileName}","/api/imageAccount/{fileName}").permitAll() // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
                .antMatchers("/api/tutorials").hasAnyRole("USER","ADMIN")
                .antMatchers("/api/checkJWT_USER","/api/product/{id}","/api/getInfoAccount/{id}","/api/updateImageProfile").hasRole("USER")
                .antMatchers("/api/checkJWT_ADMIN","/api/addProduct","/api/getCategory","/api/deleteCategory/{id}","/api/addCategory","/api/updateCategory").hasRole("ADMIN")
                .anyRequest().authenticated(); // Tất cả các request khác đều cần phải xác thực mới được truy cập

        // Thêm một lớp Filter kiểm tra jwt
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//,"/api/getCategory","/api/upfile","/api/upProduct","/api/{fileName}","/api/{name}","/api/image/{name}","/api/editProduct","/api/test","/api/product/{id}","/api/addCategory","/api/deleteCategory/{id}","/api/addCartItem"
    }
}
