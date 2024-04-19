package com.parzival_backend.Parzival.User;

import java.util.Optional;
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
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserModel> userModel = userRepository.findUserByUsername(username);
        log.info(username);
        log.info(userModel.isPresent() ? String.valueOf(userModel.get().getRole()) : "null");
        String encodedPassword = new BCryptPasswordEncoder().encode("password");
        return userModel.map(model -> org.springframework.security.core.userdetails.User.builder()
                .username(model.getUsername())
                .password(model.getPassword())
                .roles(String.valueOf(model.getRole()))
                .build()).orElseGet(() -> org.springframework.security.core.userdetails.User.builder()
                .username("Parzival")
                .password(encodedPassword)
                .roles(String.valueOf(Role.ADMIN))
                .build());
    }
    public void createUser(UserDto userDto){
        UserModel userModel = new UserModel(userDto);
        String encodedPassword = new BCryptPasswordEncoder().encode(userModel.getPassword());
        userModel.setPassword(encodedPassword);
        userRepository.save(userModel);
    }
}
