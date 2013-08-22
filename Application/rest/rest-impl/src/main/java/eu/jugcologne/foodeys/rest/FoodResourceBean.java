package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.rest.api.FoodResource;
import eu.jugcologne.foodeys.services.api.FoodService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Daniel Sachse
 * @date 21.08.13 13:59
 */

@Stateless
public class FoodResourceBean implements FoodResource {
    @Inject
    private FoodService foodService;

    @Override
    public Response autocomplete(@QueryParam("q") String query) {
        if(query == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        List<String> suggestions = foodService.findAutocompleteSuggestions(query);

        if(suggestions == null || suggestions.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.ok(suggestions).build();
    }
}