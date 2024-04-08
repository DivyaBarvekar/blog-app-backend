//package com.dbarvekar.blog.security;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JWTAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    UserDetailsService userDetailsService;
//
//    @Autowired
//    JWTTokenHelper jwtTokenHelper;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String requestToken = request.getHeader("Authorization");
//        String userName = null;
//        String token = null;
//
//        if(request != null && requestToken.startsWith("Bearer")){
//            token = requestToken.substring(7);
//
//            try{
//                userName = jwtTokenHelper.getUserNameFromToken(token);
//            }catch (IllegalArgumentException e){
//                System.out.println("Unable to get JWT token");
//            }catch (ExpiredJwtException e){
//                System.out.println("JWT token expired");
//            }catch (MalformedJwtException e){
//                System.out.println("invalid JWT token");
//            }
//
//        }else{
//            System.out.println("JWT token does not begin with Bearer");
//        }
//
//        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//            if(jwtTokenHelper.validateToken(token, userDetails)){
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }else{
//                System.out.println("invalid JWT token");
//            }
//        }else{
//            System.out.println("username is null");
//        }
//
//        filterChain.doFilter(request,response);
//
//    }
//}
