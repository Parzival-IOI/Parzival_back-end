package com.parzival_backend.Parzival.User;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {
    Optional<UserModel> findUserByUsername(String name);
}
