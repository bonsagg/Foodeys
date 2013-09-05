package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.persistence.model.Food;
import eu.jugcologne.foodeys.persistence.model.Ingredient;
import eu.jugcologne.foodeys.persistence.model.Recipe;
import eu.jugcologne.foodeys.rest.api.FoodResource;
import eu.jugcologne.foodeys.rest.api.IngredientResource;
import eu.jugcologne.foodeys.rest.api.model.AddFoodRequest;
import eu.jugcologne.foodeys.rest.model.AutocompleteResponse;
import eu.jugcologne.foodeys.rest.model.FoodResponse;
import eu.jugcologne.foodeys.rest.api.model.IngredientResponse;
import eu.jugcologne.foodeys.rest.model.RecipeResponse;
import eu.jugcologne.foodeys.services.api.FoodService;
import eu.jugcologne.foodeys.services.api.RecipeService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Sachse
 * @date 21.08.13 13:59
 */

@Stateless
public class FoodResourceBean implements FoodResource {
    @Context
    private UriInfo uriInfo;

    @Inject
    private FoodService foodService;

    @Inject
    private RecipeService recipeService;

    @Inject
    private IngredientResource ingredientResource;

    @Override
    public Response getAll() {
        // TODO: Write Test

        List<Food> foods = foodService.findAllFoods();

        if (foods == null || foods.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(transformFoodsToFoodResponses(foods)).build();
    }

    @Override
    public Response getFoodByID(@PathParam("id") long id) {
        Food food = foodService.findByID(id);

        if (food == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(new FoodResponse(food.getName())).build();
    }

    @Override
    public Response getRecipesForFood(@PathParam("id") long id) {
        // TODO: Write Test
        Food food = foodService.findByID(id);

        if (food == null) {
            // TODO: Return response message
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        List<Recipe> recipes = recipeService.findAllRecipesForFood(food);

        return Response.ok(transformRecipesToRecipeResponses(recipes)).build();
    }

    @Override
    public Response addNewFood(AddFoodRequest addFoodRequest, @QueryParam("cookToken") String cookToken) {
        // TODO: Write Test -> upper-, lowercase versions, etc.

        String name = addFoodRequest.getName();
        Food food = foodService.findFoodByName(name);

        if (food != null) {
            return Response.seeOther(buildURIForFood(food)).build();
        }

        food = new Food(name);

        foodService.save(food);

        return Response.created(buildURIForFood(food)).build();
    }

    @Override
    public Response autocomplete(@QueryParam("q") String query) {
        if (query == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        List<String> suggestions = foodService.findAutocompleteSuggestions(query);

        if (suggestions == null || suggestions.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(new AutocompleteResponse(suggestions)).build();
    }

    private URI buildURIForFood(Food food) {
        return uriInfo.getAbsolutePathBuilder().path(food.getId() + "/").build();
    }

    private List<FoodResponse> transformFoodsToFoodResponses(List<Food> foods) {
        List<FoodResponse> foodResponses = new ArrayList<>();

        for (Food food : foods) {
            foodResponses.add(new FoodResponse(food.getName()));
        }

        return foodResponses;
    }

    private List<RecipeResponse> transformRecipesToRecipeResponses(List<Recipe> recipes) {
        List<RecipeResponse> recipeResponses = new ArrayList<>();

        for (Recipe recipe : recipes) {
            recipeResponses.add(new RecipeResponse(recipe.getName(), recipe.getInstructions(), ingredientResource.transformIngredientsToIngredientResponses(recipe.getIngredients())));
        }

        return recipeResponses;
    }
}