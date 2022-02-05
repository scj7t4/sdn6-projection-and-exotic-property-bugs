package com.example.neo4jsortanddotpropertiesprojectionsaving;

import com.example.neo4jsortanddotpropertiesprojectionsaving.domain.CityModel;
import com.example.neo4jsortanddotpropertiesprojectionsaving.domain.CityModelDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CityModelRepository extends Neo4jRepository<CityModel, UUID> {
    Optional<CityModelDTO> findByCityId(UUID cityId);

    @Query(""
            + "MATCH (n:CityModel)"
            + "RETURN n :#{orderBy(#sort)}")
    List<CityModel> customQuery(Sort sort);
}
