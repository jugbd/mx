package com.bazlur.meetup.extended.dao;

import com.bazlur.meetup.extended.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByGithub(String github);
}
