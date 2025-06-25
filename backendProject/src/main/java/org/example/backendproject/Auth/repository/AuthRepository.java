package org.example.backendproject.Auth.repository;

import org.example.backendproject.Auth.entity.Auth;
import org.example.backendproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthRepository extends JpaRepository<Auth,Long> {


    boolean existByUser(User user);


    Optional<Auth> findByRefreshToken(String refreshToken);

}
