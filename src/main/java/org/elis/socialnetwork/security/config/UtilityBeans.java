package org.elis.socialnetwork.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UtilityBeans {
    @Bean
    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }
}
