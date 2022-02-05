package com.example.neo4jsortanddotpropertiesprojectionsaving;

import com.example.neo4jsortanddotpropertiesprojectionsaving.domain.CityModel;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TestExoticProperties {
    @Autowired
    CityModelRepository cityModelRepository;

    @BeforeEach
    public void beforeEach() {
        cityModelRepository.deleteAll();
    }

    @Test
    public void testStoreExoticProperty() {
        CityModel cityModel = new CityModel();
        cityModel.setName("The Jungle");
        cityModel.setExoticProperty("lions");
        cityModel = cityModelRepository.save(cityModel);

        CityModel reloaded = cityModelRepository.findById(cityModel.getCityId())
                .orElseThrow(RuntimeException::new);
        assertThat(reloaded.getExoticProperty(), Matchers.equalTo("lions"));
    }

    @Test
    public void testSortOnExoticProperty() {
        Sort sort = Sort.by(Sort.Order.asc("exoticProperty"));
        cityModelRepository.findAll(sort);
    }

    @Test
    public void testSortOnExoticPropertyCustomQuery_MakeSureIUnderstand() {
        Sort sort = Sort.by(Sort.Order.asc("n.name"));
        cityModelRepository.customQuery(sort);
    }

    @Test
    public void testSortOnExoticPropertyCustomQuery() {
        Sort sort = Sort.by(Sort.Order.asc("n.`exotic.property`"));
        cityModelRepository.customQuery(sort);
    }
}
