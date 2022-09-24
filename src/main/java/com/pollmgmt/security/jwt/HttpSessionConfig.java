//package com.pollmgmt.security.jwt;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
////import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
//import org.springframework.session.web.http.HttpSessionIdResolver;
//
////see: https://docs.spring.io/spring-session/docs/current/reference/html5/#httpsession-rest
//@Configuration
////Override HttpSession's Filter, in this instance Spring Session is backed by Redis.
////@EnableRedisHttpSession
//public class HttpSessionConfig {
//
//// Default connection configuration, to localhost:6739.
//@Bean
//public LettuceConnectionFactory connectionFactory() {
// return new LettuceConnectionFactory();
//}
//
//// Tell Spring to use HTTP headers, X-Auth-Token.
//@Bean
//public HttpSessionIdResolver httpSessionIdResolver() {
// return HeaderHttpSessionIdResolver.xAuthToken();
//}
//}