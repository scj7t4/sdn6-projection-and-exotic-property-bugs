package com.example.neo4jsortanddotpropertiesprojectionsaving;

import com.example.neo4jsortanddotpropertiesprojectionsaving.domain.PersonModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface PersonModelRepository extends Neo4jRepository<PersonModel, UUID> {
}
