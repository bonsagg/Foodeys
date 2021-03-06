package eu.jugcologne.foodeys.services.api;

import eu.jugcologne.foodeys.persistence.model.AbstractEntity;

import javax.persistence.EntityManager;

/**
 * Interface which provides basic services that are available to all specific Services
 * 
 * @author Daniel Sachse <mail@wombatsoftware.de>
 *
 */
public interface Service {
    public abstract <T extends AbstractEntity> void delete(T entity);
    public abstract <T extends AbstractEntity> void save(T t);
    public abstract <T extends AbstractEntity> T findByID(long id);
}