package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.persistence.model.Cook;
import eu.jugcologne.foodeys.rest.api.CookResource;
import eu.jugcologne.foodeys.rest.api.model.AddCookRequest;
import eu.jugcologne.foodeys.rest.api.model.LoginCookRequest;
import eu.jugcologne.foodeys.rest.model.CookResponse;
import eu.jugcologne.foodeys.rest.model.CooksResponse;
import eu.jugcologne.foodeys.rest.model.ExtendedCookResponse;
import eu.jugcologne.foodeys.rest.model.LoginResponse;
import eu.jugcologne.foodeys.services.api.CookService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
        List<Cook> cooks = cookService.findAllCooks();

        if(cooks == null || cooks.isEmpty()) {
            return Response.noContent().build();
        }

        List<ExtendedCookResponse> cookResponses = new ArrayList<>();

        for(Cook cook : cooks) {
            cookResponses.add(new ExtendedCookResponse(cook.getName(), buildURIForCook(cook).toString()));
        }

        return Response.ok(new CooksResponse(cookResponses)).build();
    }

    @Override
    public Response addNewCook(AddCookRequest addCookRequest) {
        Cook cook = cookService.findCookByEmailAddress(addCookRequest.getEmail());

        if(cook != null) {
            return Response.seeOther(buildURIForCook(cook)).build();
        }

        cook = new Cook(addCookRequest.getName(), addCookRequest.getEmail());

        cookService.save(cook);

        return Response.created(buildURIForCook(cook)).build();
    }

    @Override
    public Response getCookByID(@PathParam("id") long id) {
        Cook cook = cookService.findByID(id);

        if(cook == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(new CookResponse(cook.getName())).build();
    }

    @Override
    public Response loginCook(LoginCookRequest loginCookRequest) {
        Cook cook = cookService.findCookByEmailAddress(loginCookRequest.getEmail());

        if(cook == null) {
            // TODO: Return response message
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if(!cook.getName().equals(loginCookRequest.getName())) {
            // TODO: Return response message
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        // TODO: Return a better api token
        return Response.ok(new LoginResponse(String.valueOf(cook.getId()))).build();
    }

    private URI buildURIForCook(Cook cook) {
        return uriInfo.getBaseUriBuilder().path(CookResource.cookURI+ cook.getId() + "/").build();
    }
}