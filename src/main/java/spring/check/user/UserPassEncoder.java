package spring.check.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPassEncoder {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String passwordEncoder(String Password){
        return encoder.encode(Password);
    }

    public boolean passwordMatches(String Password, String encodePassword) {
        return encoder.matches(Password, encodePassword);
    }
}
