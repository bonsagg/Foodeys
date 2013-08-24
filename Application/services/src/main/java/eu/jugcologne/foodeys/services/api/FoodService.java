package eu.jugcologne.foodeys.services.api;

import eu.jugcologne.foodeys.persistence.model.Food;

import java.util.List;

/**
 * Interface which provides services specific to Food
 * 
 * @author Daniel Sachse <mail@wombatsoftware.de>
 *
 */
public interface FoodService extends Service {
    List<String> findAutocompleteSuggestions(String query);
    public Food findFoodByName(String name);
}
