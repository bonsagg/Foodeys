package eu.jugcologne.foodeys.services.db;

import eu.jugcologne.foodeys.persistence.model.AbstractEntity;
import eu.jugcologne.foodeys.persistence.model.Food;
import eu.jugcologne.foodeys.services.AbstractService;
import eu.jugcologne.foodeys.services.api.FoodService;

import javax.ejb.Stateless;
import javax.enterprise.inject.Specializes;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

/**
 * Service which provides methods and configurations specific to persistent Food Objects
 * 
 * @author Daniel Sachse <mail@wombatsoftware.de>
 *
 */
@Stateless
public class DBFoodService extends AbstractService implements FoodService {
    private static final long serialVersionUID = -473372348203262950L;

    @Override
    public List<Food> findAllFoods() {
        return findAll(Food.class);
    }

    @Override
    public List<String> findAutocompleteSuggestions(String query) {
        return em.createNamedQuery(Food.findAllStartingWith, String.class).setParameter("query", query + "%").getResultList();
    }

    @Override
    public Food findFoodByName(String name) {
        try {
            return em.createNamedQuery(Food.findFoodByName, Food.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    @Override
    public Food findByID(long id) {
        return findById(Food.class, id);
    }
}