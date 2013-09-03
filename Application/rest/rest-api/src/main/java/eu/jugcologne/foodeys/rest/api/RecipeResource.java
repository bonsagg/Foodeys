package eu.jugcologne.foodeys.rest.api;

import eu.jugcologne.foodeys.rest.api.model.AddIngredientRequest;
import eu.jugcologne.foodeys.rest.api.model.AddRecipeRequest;
import eu.jugcologne.foodeys.rest.api.model.UpdateRecipeRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Daniel Sachse
 * @date 02.09.13 17:46
 */

@Path(RecipeResource.recipeURI)
public interface RecipeResource {
    public final String recipeURI = "/recipes/";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewRecipe(AddRecipeRequest addRecipeRequest, @QueryParam("cookToken") String cookToken);

    @GET
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipe(@PathParam("id") String recipeID);

    @PUT
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRecipe(UpdateRecipeRequest updateRecipeRequest, @PathParam("id") String recipeID, @QueryParam("cookToken") String cookToken);

    @DELETE
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRecipe(@PathParam("id") String recipeID, @QueryParam("cookToken") String cookToken);

    @GET
    @Path("/{id}/search/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchForRecipe(@PathParam("id") String recipeID, @QueryParam("food") List<String> foods);

    @GET
    @Path("/{id}/ingredients/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIngredientsForRecipe(@PathParam("id") String recipeID);

    @POST
    @Path("/{id}/ingredients/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewIngredientToRecipe(AddIngredientRequest addIngredientRequest, @PathParam("id") String recipeID, @QueryParam("cookToken") String cookToken);
}