package eu.jugcologne.foodeys.services.importer;

import eu.jugcologne.foodeys.persistence.model.*;
import eu.jugcologne.foodeys.services.api.CookService;
import eu.jugcologne.foodeys.services.api.FoodService;
import eu.jugcologne.foodeys.services.api.IngredientService;
import eu.jugcologne.foodeys.services.api.RecipeService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * @author Daniel Sachse
 * @date 21.09.13 17:46
 */

@Singleton
@Startup
public class DefaultDataImporter {
    @Inject
    private CookService cookService;

    @Inject
    private FoodService foodService;

    @Inject
    private IngredientService ingredientService;

    @Inject
    private RecipeService recipeService;

    @PostConstruct
    private void init() {
        Cook danielSachse = new Cook("Daniel Sachse", "mail@wombatsoftware.de");
        cookService.save(danielSachse);

        Food tomato = new Food("Tomato");
        Food salt = new Food("Salt");
        Food pepper = new Food("Pepper");
        foodService.save(tomato);
        foodService.save(salt);
        foodService.save(pepper);

        Recipe tomatoSoup = new Recipe("Tomato Soup", danielSachse);
        tomatoSoup.setInstructions("Cook tomatoes, add salt and pepper");
        recipeService.save(tomatoSoup);

        Ingredient tomatoIngredient = new Ingredient(new BigDecimal(5), tomatoSoup, tomato, Unit.PIECE);
        Ingredient saltIngredient = new Ingredient(new BigDecimal(1), tomatoSoup, salt, Unit.PORTION);
        Ingredient pepperIngredient = new Ingredient(new BigDecimal(1), tomatoSoup, pepper, Unit.PORTION);

        ingredientService.save(tomatoIngredient);
        ingredientService.save(saltIngredient);
        ingredientService.save(pepperIngredient);
    }
}