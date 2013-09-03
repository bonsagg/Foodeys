package eu.jugcologne.foodeys.rest.api;

import eu.jugcologne.foodeys.rest.api.model.UpdateIngredientRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response getIngredient(@PathParam("id") String ingredientID);

    @PUT
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateIngredient(UpdateIngredientRequest updateIngredientRequest, @PathParam("id") String ingredientID, @QueryParam("cookToken") String cookToken);

    @DELETE
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteIngredient(@PathParam("id") String ingredientID, @QueryParam("cookToken") String cookToken);
}