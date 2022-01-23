package com.example.periodtableservice.configs;

import com.example.periodtableservice.entities.Element;
import com.example.periodtableservice.services.ElementService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class DbContentFillerConfig {

    @Autowired
    private final ElementService elementService;

    @PostConstruct
    public void fillDb(){
        // read json and write to db
        final ObjectMapper mapper = new ObjectMapper();
        final TypeReference<List<Element>> typeReference = new TypeReference<List<Element>>(){};
        final InputStream inputStream = TypeReference.class.getResourceAsStream("/jsons/periodic_table.json");
        try {
            List<Element> elements = mapper.readValue(inputStream,typeReference);
            this.elementService.saveAll(elements);
            log.info("[DbContentFillerConfig] - Filling the db with initial data was successful.");
        } catch (IOException e){
            log.warn("[DbContentFillerConfig] - Filling the db with initial data was unsuccessful. Failed with exception: " + e.getMessage());
        }
    }
}
