package com.jy.study.dataaccess.h2.repository;

import com.jy.study.dataaccess.h2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
