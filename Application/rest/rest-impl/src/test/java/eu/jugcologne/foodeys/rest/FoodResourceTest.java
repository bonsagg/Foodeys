package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.FoodeysMarker;
import eu.jugcologne.foodeys.rest.api.FoodResource;
import eu.jugcologne.foodeys.rest.api.model.AddFoodRequest;
import eu.jugcologne.foodeys.rest.model.AutocompleteResponse;
import eu.jugcologne.foodeys.rest.model.FoodResponse;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URL;
import java.util.List;

@RunWith(Arquillian.class)
public class FoodResourceTest {
    @PersistenceContext
    private EntityManager em;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "FoodResourceTest.war")
                .addPackages(true, FoodeysMarker.class.getPackage())
                .addClass(RestApplication.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testAutocompleteWithoutQuery(@ArquillianResource URL base) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + FoodResource.foodURI + "autocomplete/");
        Response response = target.request().get();

        Assert.assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo());
    }

    @Test
    public void testAutocompleteWithoutData(@ArquillianResource URL base) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + FoodResource.foodURI + "autocomplete/");
        Response response = target.queryParam("q", "To").request().get();

        Assert.assertEquals(Response.Status.NO_CONTENT, response.getStatusInfo());
    }

    @Test
    public void testAddFoods(@ArquillianResource URL base) throws Exception {
        final String tomato = "Tomato";
        final String tamarillo = "Tamarillo";

        Response tomatoResponse = requestFoodWithID(base, 1l);
        Response tamarilloResponse = requestFoodWithID(base, 2l);

        Assert.assertEquals(Response.Status.NOT_FOUND, tomatoResponse.getStatusInfo());
        Assert.assertEquals(Response.Status.NOT_FOUND, tamarilloResponse.getStatusInfo());

        tomatoResponse = addFood(base, tomato);
        tamarilloResponse = addFood(base, tamarillo);

        Assert.assertEquals(Response.Status.CREATED, tomatoResponse.getStatusInfo());
        Assert.assertEquals(Response.Status.CREATED, tamarilloResponse.getStatusInfo());

        Assert.assertEquals(new URI(base.toURI() + RestApplication.REST_PATH + FoodResource.foodURI + 1 + "/"), tomatoResponse.getLocation());
        Assert.assertEquals(new URI(base.toURI() + RestApplication.REST_PATH + FoodResource.foodURI + 2 + "/"), tamarilloResponse.getLocation());

        tomatoResponse = requestFoodWithID(base, 1l);
        tamarilloResponse = requestFoodWithID(base, 2l);

        Assert.assertEquals(Response.Status.OK, tomatoResponse.getStatusInfo());
        Assert.assertEquals(Response.Status.OK, tamarilloResponse.getStatusInfo());

        FoodResponse tomatoEntity = tomatoResponse.readEntity(FoodResponse.class);
        FoodResponse tamarilloEntity = tamarilloResponse.readEntity(FoodResponse.class);

        Assert.assertEquals(tomato, tomatoEntity.getName());
        Assert.assertEquals(tamarillo, tamarilloEntity.getName());
    }

    @Test
    public void testAutocompleteWithData(@ArquillianResource URL base) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + FoodResource.foodURI + "autocomplete/");
        Response response = target.queryParam("q", "T").request().get();

        Assert.assertEquals(Response.Status.OK, response.getStatusInfo());
        AutocompleteResponse autocompleteResponse = response.readEntity(AutocompleteResponse.class);
        List<String> results = autocompleteResponse.getOptions();

        Assert.assertEquals(2, results.size());
        Assert.assertEquals("Tamarillo", results.get(0));
        Assert.assertEquals("Tomato", results.get(1));
    }

    private Response requestFoodWithID(URL base, long id) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + FoodResource.foodURI + id + "/");

        return target.request().get();
    }

    private Response addFood(URL base, String name) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + FoodResource.foodURI);

        return target.request().post(Entity.entity(new AddFoodRequest(name), MediaType.APPLICATION_JSON_TYPE));
    }
}