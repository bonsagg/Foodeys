package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.persistence.model.Cook;
import eu.jugcologne.foodeys.persistence.model.Food;
import eu.jugcologne.foodeys.persistence.model.Ingredient;
import eu.jugcologne.foodeys.persistence.model.Recipe;
import eu.jugcologne.foodeys.rest.api.IngredientResource;
import eu.jugcologne.foodeys.rest.api.RecipeResource;
import eu.jugcologne.foodeys.rest.api.model.AddIngredientRequest;
import eu.jugcologne.foodeys.rest.api.model.AddRecipeRequest;
import eu.jugcologne.foodeys.rest.api.model.UpdateRecipeRequest;
import eu.jugcologne.foodeys.rest.model.IngredientResponses;
import eu.jugcologne.foodeys.rest.model.RecipeResponse;
import eu.jugcologne.foodeys.services.api.CookService;
import eu.jugcologne.foodeys.services.api.FoodService;
import eu.jugcologne.foodeys.services.api.IngredientService;
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
 * @date 02.09.13 18:08
 */

@Stateless
public class RecipeResourceBean implements RecipeResource {
    @Context
    private UriInfo uriInfo;

    @Inject
    private RecipeService recipeService;

    @Inject
    private IngredientResource ingredientResource;

    @Inject
    private CookService cookService;

    @Inject
    private FoodService foodService;

    @Inject
    private IngredientService ingredientService;

    @Override
    public Response getAll() {
        List<Recipe> recipes = recipeService.findAllRecipes();

        if (recipes == null || recipes.isEmpty()) {
            return Response.noContent().build();
        }

        List<RecipeResponse> recipeResponses = new ArrayList<>();

        for (Recipe recipe : recipes) {
            recipeResponses.add(new RecipeResponse(recipe.getName(), recipe.getInstructions(), buildURIForRecipe(recipe).toString(), ingredientResource.transformIngredientsToIngredientResponses(recipe.getIngredients())));
        }

        return null;
    }

    @Override
    public Response addNewRecipe(AddRecipeRequest addRecipeRequest, @QueryParam("cookToken") String cookToken) {
        Cook cook = cookService.findCookByToken(cookToken);

        if (cook == null) {
            // TODO: Return response message
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Recipe recipe = new Recipe(addRecipeRequest.getName(), cook);

        recipeService.save(recipe);

        return Response.created(buildURIForRecipe(recipe)).build();
    }

    @Override
    public Response searchForRecipe(@QueryParam("food") List<String> foods) {
        return null;
    }

    @Override
    public Response getRecipeByID(@PathParam("id") long recipeID) {
        Recipe recipe = recipeService.findByID(recipeID);

        if (recipe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(new RecipeResponse(recipe.getName(), recipe.getInstructions(), buildURIForRecipe(recipe).toString(), ingredientResource.transformIngredientsToIngredientResponses(recipe.getIngredients()))).build();
    }

    @Override
    public Response updateRecipe(UpdateRecipeRequest updateRecipeRequest, @PathParam("id") long recipeID, @QueryParam("cookToken") String cookToken) {
        Cook cook = cookService.findCookByToken(cookToken);

        if (cook == null) {
            // TODO: Return response message
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Recipe recipe = recipeService.findByID(recipeID);

        if (recipe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (!cook.equals(recipe.getCook())) {
            // TODO: Return response message
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        recipe.setName(updateRecipeRequest.getName());
        recipe.setInstructions(updateRecipeRequest.getInstructions());

        recipeService.save(recipe);

        return Response.noContent().build();
    }

    @Override
    public Response deleteRecipe(@PathParam("id") long recipeID, @QueryParam("cookToken") String cookToken) {
        Cook cook = cookService.findCookByToken(cookToken);

        if (cook == null) {
            // TODO: Return response message
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Recipe recipe = recipeService.findByID(recipeID);

        if (recipe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (!cook.equals(recipe.getCook())) {
            // TODO: Return response message
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        recipeService.delete(recipe);

        return Response.noContent().build();
    }

    @Override
    public Response getAllIngredientsForRecipe(@PathParam("id") long recipeID) {
        Recipe recipe = recipeService.findByID(recipeID);

        if (recipe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(new IngredientResponses(ingredientResource.transformIngredientsToIngredientResponses(recipe.getIngredients()))).build();
    }

    @Override
    public Response addNewIngredientToRecipe(AddIngredientRequest addIngredientRequest, @PathParam("id") long recipeID, @QueryParam("cookToken") String cookToken) {
        Cook cook = cookService.findCookByToken(cookToken);

        if (cook == null) {
            // TODO: Return response message
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Recipe recipe = recipeService.findByID(recipeID);

        if (recipe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (!cook.equals(recipe.getCook())) {
            // TODO: Return response message
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        Food food = foodService.findByID(addIngredientRequest.getFoodID());

        if (food == null) {
            // TODO: Return response message
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Ingredient ingredient = new Ingredient(addIngredientRequest.getAmount(), recipe, food, addIngredientRequest.getUnit());
        ingredientService.save(ingredient);

        // TODO: Validate the correct URI
        return Response.created(ingredientResource.buildURIForIngredient(ingredient)).build();
    }

    public URI buildURIForRecipe(Recipe recipe) {
        //TODO: VALIDATE IF THE URI IS CORRECTLY BUILD
        return uriInfo.getAbsolutePathBuilder().path(RecipeResource.recipeURI + "/" + recipe.getId() + "/").build();
    }
}