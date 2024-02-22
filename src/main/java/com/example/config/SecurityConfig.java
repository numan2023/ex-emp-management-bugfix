package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.constant.UrlConst;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      // loginページは("/")のオリジナルのログインページを使用する
      // ログイン成功後にリダイレクトするページは("/employee/showList")
        // その他認証が不要なページは("/toInsert")の管理者登録ページ
        // その他のページは全てログイン認証が必要
        // bootstrapのcss,jsファイルは全てのユーザーがアクセス可能  
      http
            .authorizeHttpRequests(
                authorize -> authorize
                .requestMatchers(UrlConst.AUTH_WHITELIST).permitAll()
            .anyRequest().authenticated())
            .formLogin(
              login -> login
              // 自作のログインページを使用
                .loginPage("/")
                .loginProcessingUrl("/login")
                .usernameParameter("mailAddress")
                .passwordParameter("password")
                .defaultSuccessUrl("/employee/showList")
            );
        return http.build();
    }
}