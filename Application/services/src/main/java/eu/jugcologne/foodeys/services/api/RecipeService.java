package eu.jugcologne.foodeys.services.api;

import eu.jugcologne.foodeys.persistence.model.Food;
import eu.jugcologne.foodeys.persistence.model.Recipe;

import java.util.List;

/**
 * Interface which provides services specific to Recipes
 * 
 * @author Daniel Sachse <mail@wombatsoftware.de>
 *
 */
public interface RecipeService extends Service {
    List<Recipe> findAllRecipes();
    List<Recipe> findAllRecipesForFood(Food food);
}