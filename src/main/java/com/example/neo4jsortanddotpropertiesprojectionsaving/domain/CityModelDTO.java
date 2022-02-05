package com.example.neo4jsortanddotpropertiesprojectionsaving.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class CityModelDTO {
    private UUID cityId;
    private String name;
    private String exoticProperty;

    public PersonModelDTO mayor;
    public List<PersonModelDTO> citizens = new ArrayList<>();
    public List<JobRelationshipDTO> cityEmployees = new ArrayList<>();

    @Data
    public static class PersonModelDTO {
        private UUID personId;
    }

    @Data
    public static class JobRelationshipDTO {
        private PersonModelDTO person;
    }
}
