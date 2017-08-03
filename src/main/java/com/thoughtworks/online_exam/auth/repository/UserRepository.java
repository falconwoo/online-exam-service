package com.thoughtworks.online_exam.auth.repository;

import com.thoughtworks.online_exam.auth.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Query(nativeQuery = true,
            value = "SELECT u.* FROM USER u WHERE email=:email LIMIT 1")
    UserModel findUserByEmail(@Param("email") String email);

    @Query(nativeQuery = true,
            value = "SELECT u.* FROM USER u WHERE email=:email and password=:password LIMIT 1")
    UserModel findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
