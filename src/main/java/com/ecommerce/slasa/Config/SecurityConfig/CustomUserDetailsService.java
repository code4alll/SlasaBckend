package com.ecommerce.slasa.Config.SecurityConfig;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.slasa.Model.UserModel;
import com.ecommerce.slasa.Repository.UserRepo;



@Service
@CacheConfig(cacheNames = "users")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepository; // Assume UserRepository is your repository interface

    public CustomUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Cacheable(key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user details from the database
        UserModel userEntity = userRepository.findByusername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        

        return User.withUsername(userEntity.getUsername())
            .password(userEntity.getPassword())
            .authorities(userEntity.getAuthorities())
            .build();
    }
}

