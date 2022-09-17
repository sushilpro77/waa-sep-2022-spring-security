package edu.miu.springdataday3.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO{
    private Long id;
    private String name;
}
