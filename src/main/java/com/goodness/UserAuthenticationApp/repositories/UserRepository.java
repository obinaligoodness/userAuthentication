package com.goodness.UserAuthenticationApp.repositories;

import com.goodness.UserAuthenticationApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
}
