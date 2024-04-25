package com.parzival_backend.Parzival.User;

import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
        String defaultUsername = "Parzival";
        String defaultPassword = new BCryptPasswordEncoder().encode("123");

        if(userModel.isPresent()) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userModel.get().getUsername())
                    .password(userModel.get().getPassword())
                    .roles(String.valueOf(userModel.get().getRole()))
                    .build();
        }
        else if(Objects.equals(username, defaultUsername)) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(defaultUsername)
                    .password(defaultPassword)
                    .roles(String.valueOf(Role.ADMIN))
                    .build();
        }
        return null;
    }
    public void createUser(UserDto userDto){
        UserModel userModel = new UserModel(userDto);
        String encodedPassword = new BCryptPasswordEncoder().encode(userModel.getPassword());
        userModel.setPassword(encodedPassword);
        userRepository.save(userModel);
    }
}
