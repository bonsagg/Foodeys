package eu.jugcologne.foodeys.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * @author Daniel Sachse
 * @date 21.08.13 13:57
 */

@Path("/food/")
public interface FoodResource {
    @GET
    @Path("/autocomplete/")
    public Response autocomplete(@QueryParam("q") String query);
}