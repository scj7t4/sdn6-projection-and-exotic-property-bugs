package com.example.neo4jsortanddotpropertiesprojectionsaving;

import com.example.neo4jsortanddotpropertiesprojectionsaving.domain.CityModel;
import com.example.neo4jsortanddotpropertiesprojectionsaving.domain.CityModelDTO;
import com.example.neo4jsortanddotpropertiesprojectionsaving.domain.PersonModel;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TestCityModelProjection {

    @Autowired
    CityModelRepository cityModelRepository;

    @Autowired
    PersonModelRepository personModelRepository;

    @Autowired
    Neo4jTemplate neo4jTemplate;

    @BeforeEach
    public void beforeEach() {
        cityModelRepository.deleteAll();
    }

    @Test
    public void testCityModelProjectionPersistence() {
        CityModel cityModel = new CityModel();
        cityModel.setName("New Cool City");
        cityModel = cityModelRepository.save(cityModel);

        PersonModel personModel = new PersonModel();
        personModel.setName("Mr. Mayor");
        personModel.setAddress("1600 City Avenue");
        personModel.setFavoriteFood("tacos");
        personModelRepository.save(personModel);

        CityModelDTO cityModelDTO = cityModelRepository.findByCityId(cityModel.getCityId())
                .orElseThrow(RuntimeException::new);
        cityModelDTO.setName("Changed name");
        cityModelDTO.setExoticProperty("tigers");

        CityModelDTO.PersonModelDTO personModelDTO = new CityModelDTO.PersonModelDTO();
        personModelDTO.setPersonId(personModelDTO.getPersonId());

        CityModelDTO.JobRelationshipDTO jobRelationshipDTO = new CityModelDTO.JobRelationshipDTO();
        jobRelationshipDTO.setPerson(personModelDTO);

        cityModelDTO.setMayor(personModelDTO);
        cityModelDTO.setCitizens(Collections.singletonList(personModelDTO));
        cityModelDTO.setCityEmployees(Collections.singletonList(jobRelationshipDTO));

        neo4jTemplate.save(CityModel.class).one(cityModelDTO);

        CityModel reloaded = cityModelRepository.findById(cityModel.getCityId())
                .orElseThrow(RuntimeException::new);
        assertThat(reloaded.getMayor(), Matchers.notNullValue());
        assertThat(reloaded.getCitizens(), IsCollectionWithSize.hasSize(1));
        assertThat(reloaded.getCityEmployees(), IsCollectionWithSize.hasSize(1));
    }
}
