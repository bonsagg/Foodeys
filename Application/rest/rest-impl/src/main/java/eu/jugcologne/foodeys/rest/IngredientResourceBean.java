package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.rest.api.IngredientResource;
import eu.jugcologne.foodeys.rest.api.model.UpdateIngredientRequest;

import javax.ejb.Stateless;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * @author Daniel Sachse
 * @date 02.09.13 18:07
 */

@Stateless
public class IngredientResourceBean implements IngredientResource {
    @Override
    public Response getIngredient(@PathParam("id") String ingredientID) {
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
}