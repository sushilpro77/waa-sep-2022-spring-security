package edu.miu.springdataday3.controller;

import edu.miu.springdataday3.entity.dto.UserDTO;
import edu.miu.springdataday3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    //get
    @GetMapping
    public List<UserDTO> getAll(){
        return userService.getAll();
    }

    //post
    @PostMapping
    public UserDTO save(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }


    //update
    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return userService.update(id, userDTO);
    }


    // delete
    @DeleteMapping("/{id}")
    public UserDTO delete(@PathVariable Long id){
        return userService.delete(id);
    }


}
