package com.komponente.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class DataRepositoryJson implements DataRepository {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void save(String collection, Object object) {
        try {
            List<Object> objects = objectMapper.    readValue(new File(collection), new TypeReference<List<Object>>() {
            });
            objects.add(object);
            objectMapper.writeValue(new File(collection), objects);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to save object to the storage.");
        }
    }

    @Override
    public <T> T findById(String collection, String id, Class<T> type) {
        try {
            File file = new File(collection);
            JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
            List<T> objects = objectMapper.readValue(file, javaType);
            return objects.stream().filter(object -> {
                JsonNode jsonNode = objectMapper.valueToTree(object);
                return jsonNode.get("id").asText().equals(id);
            }).findFirst().orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Storage error.");
        }
    }

    @Override
    public <T> List<T> findAll(String collection, Class<T> type) {
        try {
            File file = new File(collection);
            JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
            return objectMapper.readValue(file, javaType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Storage error.");
        }
    }

    @Override
    public void delete(String collection, String id) {

    }

    @Override
    public void update(String collection, String id, HashMap<String, AbstractAttribute> newData) {

    }

    @Override
    public <T> T findByCriteria(String collection, HashMap<String, String> criteriaList, AbstractEntity type) {
        return null;
    }
}
