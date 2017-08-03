package com.thoughtworks.online_exam.auth.repository;

import com.thoughtworks.online_exam.auth.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Query(nativeQuery = true,
            value = "select u.* from USER u where email=:email limit 1")
    UserModel findUserByEmail(@Param("email") String email);
}
