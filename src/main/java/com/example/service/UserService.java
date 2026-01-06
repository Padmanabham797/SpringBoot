package com.example.service;

import com.example.entity.UserEntity;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    public  UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User getUser(Long id){
        User user=new User();
        UserEntity userEntity=userRepository.findById(id).get();
        user.setId(userEntity.getId().intValue());
        user.setName(userEntity.getName());
        user.setEmail(userEntity.getEmail());

        return user;
    }
    public String deleteUser(Long id){
        userRepository.deleteById(id);
        return "user deleted successfully"+id;
    }
    public String createUser(User user){
        UserEntity userEntity=new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());

        UserEntity createUser=userRepository.save(userEntity);
        return  "user created "+ createUser.getId();
    }
    public String updateUser(User user ,Long id){
        UserEntity userEntity=userRepository.findById(id).get();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        UserEntity updateUser=userRepository.save(userEntity);
        return "user updated secussfully"+ updateUser.getName();
    }
    public List<User> getAllUsers(){
        List<User> userList=new ArrayList<>();
        List<UserEntity>userEntities=userRepository.findAll();
        for(UserEntity entity:userEntities ){
            User user=new User();
            user.setEmail(entity.getEmail());
            user.setName(entity.getName());
            user.setId(entity.getId().intValue());
            userList.add(user);
        }
        return userList;

    }

}
