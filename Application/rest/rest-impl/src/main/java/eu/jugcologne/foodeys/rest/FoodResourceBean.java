package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.persistence.model.Food;
import eu.jugcologne.foodeys.rest.api.FoodResource;
import eu.jugcologne.foodeys.rest.api.model.AddFoodRequest;
import eu.jugcologne.foodeys.rest.model.AutocompleteResponse;
import eu.jugcologne.foodeys.rest.model.FoodResponse;
import eu.jugcologne.foodeys.services.api.FoodService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * @author Daniel Sachse
 * @date 21.08.13 13:59
 */

@Stateless
public class FoodResourceBean implements FoodResource {
    @Context
    private UriInfo uriInfo;

    @Inject
    private FoodService foodService;

    @Override
    public Response getAll() {
        return null;
    }

    @Override
    public Response getFoodByID(@PathParam("id") long id) {
        Food food = foodService.findByID(id);

        if(food == null) {
            return Response.noContent().build();
        }

        return Response.ok(new FoodResponse(food.getId(), food.getName())).build();
    }

    @Override
    public Response getRecipesForFood(@PathParam("id") long id) {
        return null;
    }

    @Override
    public Response addNewFood(AddFoodRequest addFoodRequest) {
        Food food = new Food(addFoodRequest.getName());

        foodService.save(food);

        return Response.created(uriInfo.getAbsolutePathBuilder().path(food.getId() + "/").build()).build();
    }

    @Override
    public Response autocomplete(@QueryParam("q") String query) {
        if(query == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        List<String> suggestions = foodService.findAutocompleteSuggestions(query);

        if(suggestions == null || suggestions.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(new AutocompleteResponse(suggestions)).build();
    }
}