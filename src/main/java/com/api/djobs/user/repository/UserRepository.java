package com.api.djobs.user.repository;

import com.api.djobs.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findByNickname(String nickname);
//
//    @Query("select u.id from User u where u.oauthId=:oauthId")
//    Long findUserId(@Param("oauthId") String oauthId);
//
//    User findByOauthId(String oauthId);
//
//    boolean existsByusername(String username);

}