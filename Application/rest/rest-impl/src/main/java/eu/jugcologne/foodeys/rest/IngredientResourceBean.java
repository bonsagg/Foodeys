package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.persistence.model.Ingredient;
import eu.jugcologne.foodeys.rest.api.IngredientResource;
import eu.jugcologne.foodeys.rest.api.model.IngredientResponse;
import eu.jugcologne.foodeys.rest.api.model.UpdateIngredientRequest;

import javax.ejb.Stateless;
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

    @Override
    public Response getIngredientByID(@PathParam("id") long ingredientID) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Response updateIngredient(UpdateIngredientRequest updateIngredientRequest, @PathParam("id") String ingredientID, @QueryParam("cookToken") String cookToken) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Response deleteIngredient(@PathParam("id") String ingredientID, @QueryParam("cookToken") String cookToken) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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