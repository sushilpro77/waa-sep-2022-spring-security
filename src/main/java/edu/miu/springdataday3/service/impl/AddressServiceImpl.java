package edu.miu.springdataday3.service.impl;

import edu.miu.springdataday3.aspect.annotation.ExecutionTime;
import edu.miu.springdataday3.entity.Address;
import edu.miu.springdataday3.entity.dto.AddressDTO;
import edu.miu.springdataday3.repo.AddressRepo;
import edu.miu.springdataday3.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepo addressRepo;
    private final ModelMapper modelMapper;

    @Override
    @ExecutionTime
    public List<AddressDTO> getAll() {
        List<Address> address = addressRepo.findAll();
        return address.stream().map(address1 -> modelMapper.map(address1, AddressDTO.class)).collect(Collectors.toList());
    }

    @Override
    @ExecutionTime
    public AddressDTO save(AddressDTO addressDTO) {
        return modelMapper.map(addressRepo.save(modelMapper.map(addressDTO, Address.class)), AddressDTO.class);
    }

    @Override
    @ExecutionTime
    public AddressDTO update(Long id, AddressDTO addressDTO) {
        if (id != addressDTO.getId() ) throw new RuntimeException("Address Id not found");
        return modelMapper.map(addressRepo.save(modelMapper.map(addressDTO, Address.class)), AddressDTO.class);
    }

    @Override
    @ExecutionTime
    public AddressDTO delete(Long id) {
        Address address = addressRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        addressRepo.delete(address);
        return modelMapper.map(address, AddressDTO.class);
    }
}
