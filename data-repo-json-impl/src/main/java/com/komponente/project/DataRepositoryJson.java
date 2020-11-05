package com.komponente.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataRepositoryJson implements DataRepository {

    private ObjectMapper objectMapper = new ObjectMapper();
    private String currentWarehouse = "";
    private int maxEntities=50;

    @Override
    public boolean openWarehouse(String path) {
        if(path.endsWith("/")) {
            path = path.substring(0, path.length()-1);
        }
        File file = new File(path);
        if(file.isDirectory() && file.exists()){
            this.currentWarehouse = path;
            return true;
        }
        return false;
    }

    @Override
    public void setMaxEntitiesPerFile(int maxEntities) {
        if(maxEntities<=0){
            return;
        }
        this.maxEntities=maxEntities;
    }

    private String getNextAvailableFilename(String collection) {
        String fullPath = new File(getCollectionPath(collection)).getAbsolutePath();

        String parentFolderPath = fullPath.substring(0,fullPath.lastIndexOf(File.separator));
        File parentFolder = new File(parentFolderPath);

        String[] files = parentFolder.list();
        // Assume that next file doesn't exist
        int maxIndex=1;

        for(String f:files){
            if(f.startsWith(collection)) {
                if(f.startsWith(collection+"_")){
                    // collection_23
                    String index = f.substring(collection.length()+1);
                    try {
                        int intIndex = Integer.parseInt(index);
                        System.out.println("Found index "+intIndex);
                        if(intIndex > maxIndex) {
                            maxIndex = intIndex;
                        }
                    }
                    catch(Exception e) {
                        System.out.println("Unable to parse "+index);
                    }
                }
            }
        }

        return collection+"_"+(maxIndex+1);
    }

    @Override
    public void save(String collection, java.lang.Object object) {
        try {
            List<java.lang.Object> objects = objectMapper.    readValue(new File(getCollectionPath(collection)), new TypeReference<List<java.lang.Object>>() {
            });
            String path = getCollectionPath(collection);
            if(objects.size() >= this.maxEntities) {
                path = this.getNextAvailableFilename(collection);
            }
            objects.add(object);
            objectMapper.writeValue(new File(path), objects);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to save object to the storage.");
        }
    }

    @Override
    public <T> T findById(String collection, String id, Class<T> type) {
        try {
            File file = new File(getCollectionPath(collection));
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
            System.out.println(getCollectionPath(collection));
            File file = new File(getCollectionPath(collection));
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
    public void update(String collection, String id, HashMap<String, Object> newData) {

    }

    @Override
    public <T> T findByCriteria(String collection, HashMap<String, String> criteriaList, AbstractEntity type) {
        return null;
    }

    private String getCollectionPath(String collection) {
        if(this.currentWarehouse.length() == 0){
            return collection;
        }
        return this.currentWarehouse+"/"+collection;
    }
}
