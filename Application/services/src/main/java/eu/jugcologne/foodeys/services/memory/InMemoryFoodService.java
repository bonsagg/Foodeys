package eu.jugcologne.foodeys.services.memory;

import eu.jugcologne.foodeys.persistence.model.Food;
import eu.jugcologne.foodeys.services.api.FoodService;
import eu.jugcologne.foodeys.services.db.DBFoodService;

import javax.ejb.Stateless;
import javax.enterprise.inject.Specializes;
import java.util.List;

/**
 * @author Daniel Sachse
 * @date 29.08.13 21:33
 */

@Stateless
@Specializes
public class InMemoryFoodService extends DBFoodService implements FoodService {

}