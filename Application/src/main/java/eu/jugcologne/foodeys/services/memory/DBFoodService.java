package eu.jugcologne.foodeys.services.memory;

import javax.ejb.Stateless;

import eu.jugcologne.foodeys.services.api.FoodService;

/**
 * Service which provides methods and configurations specific to persistent Food Objects
 * 
 * @author Daniel Sachse <mail@wombatsoftware.de>
 *
 */
@Stateless
public class DBFoodService extends AbstractInMemoryService implements FoodService {
    private static final long serialVersionUID = -473372348203262950L;
}