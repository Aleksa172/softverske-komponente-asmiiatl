package com.komponente.project;

import com.komponente.project.exceptions.EntityIdNotUniqueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author com
 */
public interface DataRepository {


    boolean openWarehouse(String path);

    void setMaxEntitiesPerFile(int maxEntities);

    /**
     * Save object in the specified collection of the storage.
     *
     * @param collection name of the collection
     * @param object     data
     */
    void save(String collection, java.lang.Object object);

    /**
     * Get the object with the specified id.
     *
     * @param collection name of the collection
     * @param id         id of the object we want to get
     * @param type       type of the object
     * @return object with specified id
     */
    <T> T findById(String collection, String id, Class<T> type);

    /**
     * Get all objects from specified collection
     *
     * @param collection name of the collection
     * @param type       of objects
     * @return list off objects from specified collection
     */
    <T> List<T> findAll(String collection, Class<T> type);

    void delete(String collection, int id);

    void update(String collection, String id, HashMap<String, Object> newData);

    <T> List<T> findByCriteria(String collection, HashMap<String, String> criteriaList, Class<T> type);

    <T> List<T> findByCriteriaOrder(String collection, HashMap<String, String> criteriaList, AbstractEntity type, ArrayList<OrderClause> sortBy);
}
