package eu.jugcologne.foodeys.rest.api;

import eu.jugcologne.foodeys.persistence.model.Ingredient;
import eu.jugcologne.foodeys.rest.api.model.IngredientResponse;
import eu.jugcologne.foodeys.rest.api.model.UpdateIngredientRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Set;

/**
 * @author Daniel Sachse
 * @date 02.09.13 17:45
 */

@Path(IngredientResource.ingredientURI)
public interface IngredientResource {
    public final String ingredientURI = "/ingredients/";

    @GET
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngredientByID(@PathParam("id") long ingredientID);

    @PUT
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateIngredient(UpdateIngredientRequest updateIngredientRequest, @PathParam("id") String ingredientID, @QueryParam("cookToken") String cookToken);

    @DELETE
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteIngredient(@PathParam("id") String ingredientID, @QueryParam("cookToken") String cookToken);

    List<IngredientResponse> transformIngredientsToIngredientResponses(List<Ingredient> ingredients);
    List<IngredientResponse> transformIngredientsToIngredientResponses(Set<Ingredient> ingredients);

    URI buildURIForIngredient(Ingredient ingredient);
}