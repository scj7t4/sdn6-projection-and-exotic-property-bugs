package com.example.neo4jsortanddotpropertiesprojectionsaving.domain;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Data
public class JobRelationship {
    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private PersonModel person;

    private String jobTitle;
}
