package eu.jugcologne.foodeys.rest;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import eu.jugcologne.foodeys.rest.model.AutocompleteResponse;

@Path(value = "/search/")
@Stateless
public class RestSearchService {
    private static final int arraySize = 5;

    @GET
    @Path("/autocomplete/")
    @Produces("application/json")
    public AutocompleteResponse add(@QueryParam("query") String s) {
	AutocompleteResponse ar = new AutocompleteResponse();
	String[] data = new String[arraySize];

	for(int i = 0; i<arraySize; i++) {
	    data[i] = s + i;
	}

	ar.setOptions(data);

	return ar;
    }
}