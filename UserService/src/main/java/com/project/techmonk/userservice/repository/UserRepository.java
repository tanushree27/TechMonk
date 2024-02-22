package com.project.techmonk.userservice.repository;

import com.project.techmonk.userservice.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, String> {
    Optional<UserDetails> findByUsername(String userName);
    List<UserDetails> findAllByEmailIn(List<String> emails);
}
