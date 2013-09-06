package eu.jugcologne.foodeys.rest.api;

import eu.jugcologne.foodeys.persistence.model.Recipe;
import eu.jugcologne.foodeys.rest.api.model.AddIngredientRequest;
import eu.jugcologne.foodeys.rest.api.model.AddRecipeRequest;
import eu.jugcologne.foodeys.rest.api.model.UpdateRecipeRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
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
    @Path("/search/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchForRecipe(@QueryParam("food") List<String> foods);

    @GET
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipeByID(@PathParam("id") long recipeID);

    @PUT
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRecipe(UpdateRecipeRequest updateRecipeRequest, @PathParam("id") long recipeID, @QueryParam("cookToken") String cookToken);

    @DELETE
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRecipe(@PathParam("id") long recipeID, @QueryParam("cookToken") String cookToken);

    @GET
    @Path("/{id}/ingredients/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIngredientsForRecipe(@PathParam("id") long recipeID);

    @POST
    @Path("/{id}/ingredients/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewIngredientToRecipe(AddIngredientRequest addIngredientRequest, @PathParam("id") long recipeID, @QueryParam("cookToken") String cookToken);

    URI buildURIForRecipe(Recipe recipe);
}