package edu.miu.springdataday3.service;

import edu.miu.springdataday3.entity.dto.UserDTO;

import java.util.List;

public interface UserService {

    // get
    List<UserDTO> getAll();

    //post
    UserDTO save(UserDTO userDTO);

    // update
    UserDTO update(Long id, UserDTO userDTO);

    // delete
    UserDTO delete(Long id);
}
