package com.komponente.project;

import com.komponente.project.exceptions.EntityIdNotUniqueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author com
 */
public interface DataRepository {

    /**
     *
     * @param path String path to repository location
     * @return boolean - true if successfully opened, false otherwise (eg. if the path doesn't exist, or program lacks privileges)
     */
    boolean openWarehouse(String path);

    /**
     * Set the maximum number of entities per file
     * @param maxEntities number of entities in a file that can be written
     */
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

    /**
     * Delete an object from specified collection
     *
     * @param collection    name of the collection
     * @param id id of the object we want to delete
     */
    void delete(String collection, int id);

    /**
     * Update an object form collection
     *
     * @param collection name of the collection
     * @param id id of the object we want to update
     * @param newData data to update the object with
     */
    void update(String collection, String id, HashMap<String, Object> newData);

    /**
     * Get the object with the specified criteria
     *
     * @param collection name of the collection
     * @param criteriaList criteria by which we find the object
     * @param type type of object
     * @return return list of objects that meet the specified criteria
     */
    <T> List<T> findByCriteria(String collection, HashMap<String, String> criteriaList, Class<T> type);

    /**
     * Get
     * @param collection name of the collection
     * @param criteriaList criteria by which we find the object
     * @param type type of object
     * @param sortBy a rule by which the collection will be sorted by
     * @return return list of objects that meet the specified criteria in sorted order
     */
    <T> List<T> findByCriteriaOrder(String collection, HashMap<String, String> criteriaList, AbstractEntity type, ArrayList<OrderClause> sortBy);
}
