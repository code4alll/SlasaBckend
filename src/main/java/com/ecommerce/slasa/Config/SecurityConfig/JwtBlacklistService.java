package com.ecommerce.slasa.Config.SecurityConfig;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JwtBlacklistService {
	@Autowired
	JwtTokenUtil tokenService;
    private final Map<String, LocalDateTime> blacklist = new ConcurrentHashMap<>();

    public void addToBlacklist(String token) {
        LocalDateTime expiryTime = tokenService.extractExpiryTime(token);
        if (expiryTime != null) {
            blacklist.put(token, expiryTime);
        }
    }

    public boolean isBlacklisted(String token) {
        LocalDateTime expiryTime = blacklist.get(token);
        if (expiryTime == null) {
            return false;
        }
        if (expiryTime.isBefore(LocalDateTime.now())) {
            blacklist.remove(token); // Cleanup expired token on access
            return false;
        }
        return true;
    }

    public void cleanExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        Iterator<Map.Entry<String, LocalDateTime>> iterator = blacklist.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, LocalDateTime> entry = iterator.next();
            if (entry.getValue().isBefore(now)) {
                iterator.remove();  // Remove expired tokens
            }
        }
    }
}
