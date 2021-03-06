package eu.jugcologne.foodeys.services.db;

import eu.jugcologne.foodeys.persistence.model.Ingredient;
import eu.jugcologne.foodeys.services.AbstractService;
import eu.jugcologne.foodeys.services.api.IngredientService;

import javax.ejb.Stateless;

/**
 * Service which provides methods and configurations specific to persistent Ingredient Objects
 * 
 * @author Daniel Sachse <mail@wombatsoftware.de>
 *
 */
@Stateless
public class DBIngredientService extends AbstractService implements IngredientService {
    private static final long serialVersionUID = -473372348203262950L;

    @Override
    public Ingredient findByID(long id) {
        return findById(Ingredient.class, id);
    }
}