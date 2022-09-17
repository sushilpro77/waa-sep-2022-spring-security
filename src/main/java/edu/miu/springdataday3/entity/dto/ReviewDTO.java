package edu.miu.springdataday3.entity.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.miu.springdataday3.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private String name;

    @JsonBackReference
    private UserDTO user;
}
