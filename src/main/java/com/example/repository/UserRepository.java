package com.example.repository;

import com.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
//    UserEntity findNameAndId(String name,Long id,String email );
//    List<UserEntity> findByName(String name);
//    List<UserEntity> findByEmail(String email);
}
