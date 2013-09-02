package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.persistence.model.Cook;
import eu.jugcologne.foodeys.rest.api.CookResource;
import eu.jugcologne.foodeys.rest.api.model.AddCookRequest;
import eu.jugcologne.foodeys.rest.api.model.LoginCookRequest;
import eu.jugcologne.foodeys.rest.model.CookResponse;
import eu.jugcologne.foodeys.services.api.CookService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Daniel Sachse
 * @date 21.08.13 13:59
 */

@Stateless
public class CookResourceBean implements CookResource {
    @Context
    private UriInfo uriInfo;

    @Inject
    private CookService cookService;

    @Override
    public Response getAll() {
        return null;
    }

    @Override
    public Response addNewCook(AddCookRequest addCookRequest) {
        Cook cook = new Cook(addCookRequest.getName());

        cookService.save(cook);

        return Response.created(uriInfo.getAbsolutePathBuilder().path(cook.getId() + "/").build()).build();
    }

    @Override
    public Response getCookByID(@PathParam("id") long id) {
        Cook cook = cookService.findByID(id);

        if(cook == null) {
            return Response.noContent().build();
        }

        return Response.ok(new CookResponse(cook.getId(), cook.getName())).build();
    }

    @Override
    public Response loginCook(LoginCookRequest loginCookRequest) {
        return null;
    }
}