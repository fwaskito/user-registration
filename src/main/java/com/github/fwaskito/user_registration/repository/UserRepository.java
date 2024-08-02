package com.github.fwaskito.user_registration.repository;

import com.github.fwaskito.user_registration.dto.UserDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    UserDTO findByName(String name);
}
