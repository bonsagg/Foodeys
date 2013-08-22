package eu.jugcologne.foodeys.services.api;

import java.util.List;

/**
 * Interface which provides services specific to Food
 * 
 * @author Daniel Sachse <mail@wombatsoftware.de>
 *
 */
public interface FoodService extends Service {
    List<String> findAutocompleteSuggestions(String query);
}
