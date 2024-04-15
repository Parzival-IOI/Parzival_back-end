package com.parzival_backend.Parzival.User;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableMongoRepositories(basePackages = "com.parzival_backend.Parzival.User")
@Slf4j
public class UserService  implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserModel> userModel = userRepository.findUserByUsername(username);
        log.info(username);
        return userModel.map(model -> org.springframework.security.core.userdetails.User.builder()
                .username(model.getUsername())
                .password(model.getPassword())
                .roles(String.valueOf(model.getRole())) // Replace with appropriate roles from userDetails if available
                .build()).orElseGet(() -> org.springframework.security.core.userdetails.User.builder()
                .username("Parzival")
                .password("$2a$12$spcxBSLMNIKYr87ZcLDQ2u6w4qgaR.5XY.fhvvvCdy2vFFCHMBYsy")
                .roles("ADMIN") // Replace with appropriate roles from userDetails if available
                .build());
        // Create and return the Spring Security UserDetails object
    }
    public void createUser(UserModel userModel){
        String encodedPassword = new BCryptPasswordEncoder().encode(userModel.getPassword());
        userModel.setPassword(encodedPassword);
        userRepository.save(userModel);
    }
}
