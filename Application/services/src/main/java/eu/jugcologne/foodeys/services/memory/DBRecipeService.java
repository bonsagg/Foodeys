package eu.jugcologne.foodeys.services.memory;

import javax.ejb.Stateless;

import eu.jugcologne.foodeys.services.api.RecipeService;

/**
 * Service which provides methods and configurations specific to persistent Recipe Objects
 * 
 * @author Daniel Sachse <mail@wombatsoftware.de>
 *
 */
@Stateless
public class DBRecipeService extends AbstractInMemoryService implements RecipeService {
    private static final long serialVersionUID = -473372348203262950L;
}