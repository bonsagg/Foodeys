package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.FoodeysMarker;
import eu.jugcologne.foodeys.rest.api.model.AddCookRequest;
import eu.jugcologne.foodeys.rest.model.CookResponse;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
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

// TODO: Find out why this sometimes fails the build on both servers ?!?!?

@RunWith(Arquillian.class)
public class CookResourceTest {

    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "CookResourceTest.war")
                .addPackages(true, FoodeysMarker.class.getPackage())
                .addClass(RestApplication.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    /*
    @Test
    @RunAsClient
    public void testGetCookByIdWithoutMatch(@ArquillianResource URL base) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + "/cooks/1/");
        Response response = target.request().get();

        Assert.assertEquals(Response.Status.NO_CONTENT, response.getStatusInfo());
    }*/


    @Test
    @RunAsClient
    public void testAddCooks(@ArquillianResource URL base) throws Exception {
        Response wombatResponse = addCook(base, "Wombat");
        Response kubaResponse = addCook(base, "Kuba");

        Assert.assertEquals(Response.Status.CREATED, wombatResponse.getStatusInfo());
        Assert.assertEquals(Response.Status.CREATED, kubaResponse.getStatusInfo());

        Assert.assertEquals(new URI(base.toURI() + RestApplication.REST_PATH + "/cooks/" + 1 + "/"), wombatResponse.getLocation());
        Assert.assertEquals(new URI(base.toURI() + RestApplication.REST_PATH + "/cooks/" + 2 + "/"), kubaResponse.getLocation());
    }


    /*@Test
    @RunAsClient
    public void testGetCookByIdWithMatch(@ArquillianResource URL base) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + "/cooks/1/");
        Response response = target.request().get();

        Assert.assertEquals(Response.Status.OK, response.getStatusInfo());
        CookResponse wombat = response.readEntity(CookResponse.class);
        Assert.assertEquals("Wombat", wombat.getName());
    }*/

    private Response addCook(URL base, String name) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + "/cooks/");
        return target.request().post(Entity.entity(new AddCookRequest(name), MediaType.APPLICATION_JSON_TYPE));
    }
}