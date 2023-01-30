package com.example.OracleConnection.mapper;

import com.example.relationmappings.one2one.entity.AddressEntity;
import com.example.relationmappings.one2one.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressEntity addressToAddressEntity(Address address);
    Address addressEntityToAddress(AddressEntity addressEntity);
}
