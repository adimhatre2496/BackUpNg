package com.example.customValidation.mapper;

import com.example.customValidation.entity.AddressEntity;
import com.example.customValidation.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressEntity addressToAddressEntity(Address address);
    Address addressEntityToAddress(AddressEntity addressEntity);
}
