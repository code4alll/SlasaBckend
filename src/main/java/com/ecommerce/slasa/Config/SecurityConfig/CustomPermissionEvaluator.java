//package com.Ulink.Ecommerce.SecurityConfig;
//
//import org.springframework.security.access.PermissionEvaluator;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//
//import java.io.Serializable;
//
//public class CustomPermissionEvaluator implements PermissionEvaluator {
//
//    @Override
//    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
//        if ("seller".equals(permission)) {
//            User user = (User) authentication.getPrincipal();
//            // Check if the user has the role "ROLE_SELLER"
//            return user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SELLER"));
//        }
//        return false;
//    }
//
//    @Override
//    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
//        throw new UnsupportedOperationException();
//    }
//}
