package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.persistence.model.Cook;
import eu.jugcologne.foodeys.persistence.model.Ingredient;
import eu.jugcologne.foodeys.rest.api.IngredientResource;
import eu.jugcologne.foodeys.rest.api.model.IngredientResponse;
import eu.jugcologne.foodeys.rest.api.model.UpdateIngredientRequest;
import eu.jugcologne.foodeys.services.api.CookService;
import eu.jugcologne.foodeys.services.api.IngredientService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Daniel Sachse
 * @date 02.09.13 18:07
 */

@Stateless
public class IngredientResourceBean implements IngredientResource {
    @Context
    private UriInfo uriInfo;

    @Inject
    private IngredientService ingredientService;

    @Inject
    private CookService cookService;

    @Override
    public Response getIngredientByID(@PathParam("id") long ingredientID) {
        Ingredient ingredient = ingredientService.findByID(ingredientID);

        if(ingredient == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(new IngredientResponse(ingredient.getFood().getName(), ingredient.getAmount(), ingredient.getUnit(), buildURIForIngredient(ingredient).toString())).build();
    }

    @Override
    public Response updateIngredient(UpdateIngredientRequest updateIngredientRequest, @PathParam("id") long ingredientID, @QueryParam("cookToken") String cookToken) {
        Cook cook = cookService.findCookByToken(cookToken);

        if(cook == null) {
            // TODO: Return response message
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Ingredient ingredient = ingredientService.findByID(ingredientID);

        if(ingredient == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(!cook.equals(ingredient.getRecipe().getCook())) {
            // TODO: Return response message
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        ingredient.setAmount(updateIngredientRequest.getAmount());
        ingredient.setUnit(updateIngredientRequest.getUnit());

        ingredientService.save(ingredient);

        return Response.noContent().build();
    }

    @Override
    public Response deleteIngredient(@PathParam("id") long ingredientID, @QueryParam("cookToken") String cookToken) {
        Cook cook = cookService.findCookByToken(cookToken);

        if(cook == null) {
            // TODO: Return response message
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Ingredient ingredient = ingredientService.findByID(ingredientID);

        if(ingredient == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(!cook.equals(ingredient.getRecipe().getCook())) {
            // TODO: Return response message
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        ingredientService.delete(ingredient);

        return Response.noContent().build();
    }

    @Override
    public List<IngredientResponse> transformIngredientsToIngredientResponses(List<Ingredient> ingredients) {
        List<IngredientResponse> ingredientResponses = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            ingredientResponses.add(new IngredientResponse(ingredient.getFood().getName(), ingredient.getAmount(), ingredient.getUnit(), buildURIForIngredient(ingredient).toString()));
        }

        return ingredientResponses;
    }

    @Override
    public List<IngredientResponse> transformIngredientsToIngredientResponses(Set<Ingredient> ingredients) {
        return transformIngredientsToIngredientResponses(new ArrayList<>(ingredients));
    }

    public URI buildURIForIngredient(Ingredient ingredient) {
        //TODO: VALIDATE IF THE URI IS CORRECTLY BUILD
        return uriInfo.getAbsolutePathBuilder().path(IngredientResource.ingredientURI + "/" + ingredient.getId() + "/").build();
    }
}