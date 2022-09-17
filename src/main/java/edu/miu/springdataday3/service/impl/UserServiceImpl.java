package edu.miu.springdataday3.service.impl;

import edu.miu.springdataday3.aspect.annotation.ExecutionTime;
import edu.miu.springdataday3.entity.User;
import edu.miu.springdataday3.entity.dto.UserDTO;
import edu.miu.springdataday3.repo.UserRepo;
import edu.miu.springdataday3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Override
    @ExecutionTime
    public List<UserDTO> getAll() {
        List<User> users = userRepo.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return modelMapper.map(userRepo.save(modelMapper.map(userDTO, User.class)), UserDTO.class);
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        if (id != userDTO.getId()) throw new RuntimeException(" Not Found");
        return modelMapper.map(userRepo.save(modelMapper.map(userDTO, User.class)), UserDTO.class);
    }

    @Override
    public UserDTO delete(Long id) {
        User user = userRepo.findById(id).orElseThrow(()-> new RuntimeException("Not Found!"));
        userRepo.delete(user);
        return modelMapper.map(user, UserDTO.class);
    }
}
