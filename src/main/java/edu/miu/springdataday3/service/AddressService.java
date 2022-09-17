package edu.miu.springdataday3.service;

import edu.miu.springdataday3.entity.dto.AddressDTO;

import java.util.List;

public interface AddressService {

    //get
    List<AddressDTO> getAll();

    //save
    AddressDTO save(AddressDTO addressDTO);

    //update
    AddressDTO update(Long id, AddressDTO addressDTO);

    //delete
    AddressDTO delete(Long id);

}
