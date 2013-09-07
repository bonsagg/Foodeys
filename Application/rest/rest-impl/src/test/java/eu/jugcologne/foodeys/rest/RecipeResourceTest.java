/*
package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.FoodeysMarker;
import eu.jugcologne.foodeys.persistence.model.Cook;
import eu.jugcologne.foodeys.rest.api.CookResource;
import eu.jugcologne.foodeys.rest.api.RecipeResource;
import eu.jugcologne.foodeys.rest.api.model.AddRecipeRequest;
import eu.jugcologne.foodeys.rest.api.model.LoginCookRequest;
import eu.jugcologne.foodeys.rest.model.LoginResponse;
import eu.jugcologne.foodeys.services.api.CookService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.persistence.DataSource;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
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
@DataSource("jdbc/__default")
@UsingDataSet("datasets/prepareTestAddRecipe.yml")
public class RecipeResourceTest {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private CookService cookService;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "RecipeResourceTest.war")
                .addPackages(true, FoodeysMarker.class.getPackage())
                .addClass(RestApplication.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @InSequence(1)
    public void setupDatabase() throws Exception {
        List<Cook> cooks = cookService.findAllCooks();
        Assert.assertNotNull(cooks);
        Assert.assertEquals(2, cooks.size());
    }

    @Test
    @InSequence(2)
    @RunAsClient
    public void testAddRecipe(@ArquillianResource URL base) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + CookResource.cookURI + "login/");

        Response response = target.request().post(Entity.entity(new LoginCookRequest("Wombat", "mail@wombatsoftware.de"), MediaType.APPLICATION_JSON_TYPE));
        Assert.assertEquals(Response.Status.OK, response.getStatusInfo());

        LoginResponse loginResponse = response.readEntity(LoginResponse.class);

        Response testRecipeResponse = addRecipe(base, "TestRecipe", loginResponse.getToken());
        Assert.assertEquals(Response.Status.CREATED, testRecipeResponse.getStatusInfo());

        URI testRecipeLocation = testRecipeResponse.getLocation();
    }

    private Response addRecipe(URL base, String name, String cookToken) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + RecipeResource.recipeURI).queryParam("cookToken", cookToken);

        return target.request().post(Entity.entity(new AddRecipeRequest(), MediaType.APPLICATION_JSON_TYPE));
    }
}*/
