package eu.jugcologne.foodeys.rest.api;

import eu.jugcologne.foodeys.rest.api.model.AddFoodRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Daniel Sachse
 * @date 21.08.13 13:57
 */

@Path("/food/")
public interface FoodResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll();

    @GET
    @Path("/{name}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFood(@PathParam("name") String name);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewFood(AddFoodRequest addFoodRequest);

    @GET
    @Path("/autocomplete/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response autocomplete(@QueryParam("q") String query);
}