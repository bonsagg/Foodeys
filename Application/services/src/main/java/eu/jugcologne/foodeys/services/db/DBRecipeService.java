package eu.jugcologne.foodeys.services.db;

import eu.jugcologne.foodeys.persistence.model.AbstractEntity;
import eu.jugcologne.foodeys.persistence.model.Recipe;
import eu.jugcologne.foodeys.services.AbstractService;
import eu.jugcologne.foodeys.services.api.RecipeService;

import javax.ejb.Stateless;

/**
 * Service which provides methods and configurations specific to persistent Recipe Objects
 * 
 * @author Daniel Sachse <mail@wombatsoftware.de>
 *
 */
@Stateless
public class DBRecipeService extends AbstractService implements RecipeService {
    private static final long serialVersionUID = -473372348203262950L;

    @Override
    public Recipe findByID(long id) {
        return findById(Recipe.class, id);
    }
}