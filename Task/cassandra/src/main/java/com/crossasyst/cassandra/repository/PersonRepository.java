package com.crossasyst.cassandra.repository;

import com.crossasyst.cassandra.entity.PersonEntity;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends CassandraRepository<PersonEntity, UUID> {

    @AllowFiltering
    PersonEntity getByName(String name);

    @AllowFiltering
    PersonEntity deleteByName(String name);
}
