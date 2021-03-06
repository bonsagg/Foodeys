package eu.jugcologne.foodeys.rest.api;

import eu.jugcologne.foodeys.rest.api.model.AddCookRequest;
import eu.jugcologne.foodeys.rest.api.model.LoginCookRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Daniel Sachse
 * @date 21.08.13 13:57
 */

@Path(CookResource.cookURI)
public interface CookResource {
    public final String cookURI = "/cooks/";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewCook(AddCookRequest addCookRequest);

    @GET
    @Path("/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCookByID(@PathParam("id") long id);

    @POST
    @Path("/login/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginCook(LoginCookRequest loginCookRequest);
}