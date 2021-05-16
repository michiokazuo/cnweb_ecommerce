package com.http.convert;

import com.http.dto.UserDTO;
import com.http.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserConvert implements Convert<User, UserDTO> {

    @Override
    public UserDTO toDTO(User user) throws Exception {
        if (user == null) return null;
        return new UserDTO(user.getId(), user.getName(), user.getAvatar(), user.getEmail(), user.getRole(),
                user.getDeleted());
    }

    @Override
    public List<UserDTO> toDtoList(List<User> users) throws Exception {
        if (users == null) return null;
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            if (user != null)
                userDTOS.add(toDTO(user));
        }

        return userDTOS.isEmpty() ? null : userDTOS;
    }

    @Override
    public User toModel(UserDTO userDTO) throws Exception {
        return null;
    }
}
