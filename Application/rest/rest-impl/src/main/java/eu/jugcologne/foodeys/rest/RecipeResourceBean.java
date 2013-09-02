package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.rest.api.RecipeResource;
import eu.jugcologne.foodeys.rest.api.model.AddIngredientRequest;
import eu.jugcologne.foodeys.rest.api.model.AddRecipeRequest;
import eu.jugcologne.foodeys.rest.api.model.UpdateRecipeRequest;

import javax.ejb.Stateless;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Daniel Sachse
 * @date 02.09.13 18:08
 */

@Stateless
public class RecipeResourceBean implements RecipeResource {
    @Override
    public Response getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Response addNewRecipe(AddRecipeRequest addRecipeRequest, @QueryParam("userToken") String userToken) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Response getRecipe(@PathParam("id") String recipeID) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Response updateRecipe(UpdateRecipeRequest updateRecipeRequest, @PathParam("id") String recipeID, @QueryParam("userToken") String userToken) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Response deleteRecipe(@PathParam("id") String recipeID, @QueryParam("userToken") String userToken) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Response searchForRecipe(@PathParam("id") String recipeID, @QueryParam("food") List<String> foods) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Response getAllIngredientsForRecipe(@PathParam("id") String recipeID) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Response addNewIngredientToRecipe(AddIngredientRequest addIngredientRequest, @PathParam("id") String recipeID, @QueryParam("userToken") String userToken) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}