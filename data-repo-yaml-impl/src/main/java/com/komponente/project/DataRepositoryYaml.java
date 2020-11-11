package com.komponente.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.komponente.project.exceptions.EntityIdNotUniqueException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataRepositoryYaml implements DataRepository{


    private ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
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

        // Assume that next file doesn't exist
        int maxIndex=1;

        ArrayList<File> parts = this.findCollectionParts(collection);
        for(File f:parts){
            String fileName = f.getName();
            if(fileName.startsWith(collection)) {
                if(fileName.startsWith(collection+"_")){
                    // collection_23
                    String index = fileName.substring(collection.length()+1);
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

    private ArrayList<File> findCollectionParts(String collection) {
        String fullPath = new File(getCollectionPath(collection)).getAbsolutePath();

        String parentFolderPath = fullPath.substring(0,fullPath.lastIndexOf(File.separator));
        File parentFolder = new File(parentFolderPath);

        String[] files = parentFolder.list();

        ArrayList<File> collectionParts = new ArrayList<>();
        // Uvek je postojeci fajl deo kolekcije, cak iako drugih nema
        collectionParts.add(new File(getCollectionPath(collection)));

        for(String f:files) {
            if (f.startsWith(collection)) {
                if (f.startsWith(collection + "_")) {
                    collectionParts.add(new File(getCollectionPath(f)));
                }
            }
        }
        return collectionParts;
    }

    @Override
    public void save(String collection, java.lang.Object object) {
        try {
            List<Object> objects = objectMapper.readValue(new File(getCollectionPath(collection)), new TypeReference<List<Object>>() {
            });
            // Igore - ovo ce biti deo refaktorizacije, pripremi Yaml
            AbstractEntity objectParameter=null;
            if(object instanceof AbstractEntity) {
                objectParameter = (AbstractEntity) object;
            }
            if(objectParameter==null) {
                // Upozorenje za refaktorizaciju
                throw new Exception("Save object needs AbstractEntity");
            }

            // Trazimo ID dal je unique
            boolean foundId=false;
            for(Object o:objects) {
                JsonNode jsonNode = objectMapper.valueToTree(o);
                // Treba da bude AbstractEntity
                if(object instanceof AbstractEntity) {
                    if (jsonNode.get("id").asText().equals(objectParameter.getId())) {
                        foundId = true;
                    }
                }
            }
            if(foundId)
                throw new EntityIdNotUniqueException(objectParameter.getId());

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
    public void delete(String collection, int id) {

    }

    @Override
    public void update(String collection, String id, HashMap<String, Object> newData) {

    }

    @Override
    public <T> List<T> findByCriteria(String collection, HashMap<String, String> criteriaList, Class<T> type) {
        try{
            File file = new File(getCollectionPath(collection));
            JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
            List<T> objects = objectMapper.readValue(file, javaType);
            return objects.stream().filter(object -> {
                JsonNode jsonNode = objectMapper.valueToTree(object);

                for (Map.Entry<String,String> entry : criteriaList.entrySet()) {

                    String key = entry.getKey();
                    String value = entry.getValue();


                    // Ako kljuca nema - sigurno ne odgovara pretrazi
                    JsonNode jn = jsonNode.get("attributes").get(key);
                    System.out.println(jn);
                    if(jn == null) {
                        return false;
                    }

                    boolean beginsWith=false;
                    boolean endsWith=false;
                    if(value.charAt(0)=='*') {
                        endsWith=true;
                    }
                    if(value.charAt(value.length()-1) =='*') {
                        beginsWith=true;
                    }

                    if(beginsWith && endsWith) {
                        System.out.println("Both");
                        return jn.textValue().contains(value);
                    }
                    else if(beginsWith) {
                        System.out.println("Begins with");
                        return jn.textValue().startsWith(value);
                    }
                    else if(endsWith) {
                        System.out.println("Ends with");
                        return jn.textValue().endsWith(value);
                    }
                    else {
                        System.out.println("Neither");
                        return jn.textValue().equals(value);
                    }

                }
                return false;
            }).collect(Collectors.toList());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public <T> List<T> findByCriteriaOrder(String collection, HashMap<String, String> criteriaList, AbstractEntity type, ArrayList<OrderClause> sortBy) {
        return null;
    }

    private String getCollectionPath(String collection) {
        if(this.currentWarehouse.length() == 0){
            return collection;
        }
        return this.currentWarehouse+"/"+collection;
    }
}


