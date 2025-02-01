package org.udemy.sergey.user.service;

import org.springframework.stereotype.Service;
import org.udemy.sergey.user.exception.CustomException;
import org.udemy.sergey.user.model.User;
import org.udemy.sergey.user.model.UserEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    Map<String, User> userMap;
    public UserEntity getUser(String userId){
        return Optional.ofNullable(userMap.get(userId))
                .map(user -> new UserEntity(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserId()))
                .orElseThrow(()->new CustomException("User ID doesn't exist: " + userId));
    }
    public User createUser(UserEntity userEntity){
        userMap = Optional.ofNullable(userMap).orElseGet(HashMap::new);
        String userID = String.valueOf(Math.abs(UUID.randomUUID().hashCode() % 10));
        User result = User.builder().firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName()).email(userEntity.getEmail()).userId(userID).build();
        userMap.put(userID, result);
        return result;
    }

    public User updateUser(String userId, UserEntity userEntity) {
        return Optional.ofNullable(userMap.get(userId))
                .map(user -> {
                    user.setFirstName(userEntity.getFirstName());
                    user.setLastName(userEntity.getLastName());
                    return user;
                })
                .orElseThrow(()->new CustomException("User ID doesn't exist: " + userId));
    }

    public void deleteUser(String userId) {
        Optional.ofNullable(userMap.remove(userId)).map(user -> userMap.remove(userId))
                .orElseThrow(() -> new CustomException("User ID doesn't exist: " + userId));
    }
}
