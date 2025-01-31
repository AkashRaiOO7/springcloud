package org.udemy.sergey.springcloud.service;

import org.springframework.stereotype.Service;
import org.udemy.sergey.springcloud.exception.CustomException;
import org.udemy.sergey.springcloud.model.User;
import org.udemy.sergey.springcloud.model.UserEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    Map<String, User> userMap;
    public UserEntity getUser(String userId){
        if(!userMap.containsKey(userId)){
            throw new CustomException("userId doesn't exists: "+ userId);
        }
        User user = userMap.get(userId);
        return new UserEntity(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserId());
    }
    public User createUser(UserEntity userEntity){
        User result = new User();
        result.setEmail(userEntity.getEmail());
        result.setFirstName(userEntity.getFirstName());
        result.setLastName(userEntity.getLastName());
        var userID = Integer.toString(Math.abs(UUID.randomUUID().hashCode() % 10));
        if(userMap==null) userMap = new HashMap<>();
        result.setUserId(userID);
        userMap.put(userID, result);
        return result;
    }

    public User updateUser(String userId, UserEntity userEntity) {
        if(!userMap.containsKey(userId)){
            throw new CustomException("userId doesn't exists: "+ userId);
        }
        User user = userMap.get(userId);
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        userMap.put(userId,user);
        return user;
    }

    public void deleteUser(String userId) {
        if(!userMap.containsKey(userId)){
            throw new CustomException("userId doesn't exists: "+ userId);
        }
        userMap.remove(userId);
    }
}
