package edu.miu.springdataday3.entity.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @JsonBackReference
    private List<ReviewDTO> review;

    @JsonBackReference
    private AddressDTO address;
}
