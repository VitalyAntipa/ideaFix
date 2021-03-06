package com.ideafix.service;

import com.ideafix.model.dto.UserDTO;
import com.ideafix.model.pojo.User;

import java.util.List;

public interface UserService {

    User getUserByNickname(String nickname);

    User getUserByEmail(String email);

    User getUserById(long id);

    void create(UserDTO newUser);

    User edit(UserDTO user);

    void delete(long userId);

    List<User> getAll();

    void setBan(long id, boolean ban);

    void setRole(long userId, Long roleId);

    String authUser(String uniqueUserString, String password);

    Boolean checkCredentialsForRegister(UserDTO userDTO);

    User getUserByEmailOrNickname(String email, String nickname);
}
