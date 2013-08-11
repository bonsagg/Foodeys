package eu.jugcologne.foodeys.services.memory;

import javax.ejb.Stateless;

import eu.jugcologne.foodeys.services.api.IngredientService;

/**
 * Service which provides methods and configurations specific to persistent Ingredient Objects
 * 
 * @author Daniel Sachse <mail@wombatsoftware.de>
 *
 */
@Stateless
public class DBIngredientService extends AbstractInMemoryService implements IngredientService {
    private static final long serialVersionUID = -473372348203262950L;
}