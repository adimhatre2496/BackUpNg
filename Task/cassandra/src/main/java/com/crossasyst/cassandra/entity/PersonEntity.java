package com.crossasyst.cassandra.entity;


import com.crossasyst.cassandra.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;


import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;


import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "person")
public class PersonEntity {


    @PrimaryKeyColumn(name = "id",ordinal = 1)
    private UUID id = UUID.randomUUID();

    @PrimaryKeyColumn(name = "personId", ordinal = 2,type = PrimaryKeyType.PARTITIONED)
    private String personId;

    @PrimaryKeyColumn(name = "name", ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String name;

    private LocalDate date;

    private Date timestamp;

    private Status status;





}
