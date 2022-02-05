package com.example.neo4jsortanddotpropertiesprojectionsaving.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Node
@Data
public class CityModel {
    @Id
    @GeneratedValue(generatorClass = GeneratedValue.UUIDGenerator.class)
    private UUID cityId;

    @Relationship(value = "MAYOR")
    private PersonModel mayor;

    @Relationship(value = "CITIZEN")
    private List<PersonModel> citizens = new ArrayList<>();

    @Relationship(value = "EMPLOYEE")
    private List<JobRelationship> cityEmployees = new ArrayList<>();

    private String name;

    @Property("exotic.property")
    private String exoticProperty;
}
