package com.example.constant;

public class UrlConst {

  // 認証不要ページの設定
  public static final String[] AUTH_WHITELIST = {
    "/",
    "/toInsert",
    "/insert",
    "/css/**",
    "/js/**",
    "/img/**",
    "/webjars/**"
  };
}
