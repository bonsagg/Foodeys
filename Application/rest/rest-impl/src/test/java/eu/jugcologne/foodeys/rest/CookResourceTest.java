package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.FoodeysMarker;
import eu.jugcologne.foodeys.rest.api.CookResource;
import eu.jugcologne.foodeys.rest.api.model.AddCookRequest;
import eu.jugcologne.foodeys.rest.api.model.LoginCookRequest;
import eu.jugcologne.foodeys.rest.model.CookResponse;
import eu.jugcologne.foodeys.rest.model.CooksResponse;
import eu.jugcologne.foodeys.services.api.CookService;
import eu.jugcologne.foodeys.services.importer.DefaultDataImporter;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
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

@RunWith(Arquillian.class)
public class CookResourceTest {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private CookService cookService;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "CookResourceTest.war")
                .addPackages(true, FoodeysMarker.class.getPackage())
                .addClass(RestApplication.class)
                .deleteClass(DefaultDataImporter.class)

                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @InSequence(1)
    public void testAddCooks(@ArquillianResource URL base) throws Exception {
        final String wombatName = "Wombat";
        final String kubaName = "Kuba";

        Response wombatResponse = requestCookWithID(base, 1L);
        Response kubaResponse = requestCookWithID(base, 2L);

        Assert.assertEquals(Response.Status.NOT_FOUND, wombatResponse.getStatusInfo());
        Assert.assertEquals(Response.Status.NOT_FOUND, kubaResponse.getStatusInfo());

        wombatResponse = addCook(base, wombatName, "mail@wombatsoftware.de");
        kubaResponse = addCook(base, kubaName, "jacob@wombatsoftware.de");

        Assert.assertEquals(Response.Status.CREATED, wombatResponse.getStatusInfo());
        Assert.assertEquals(Response.Status.CREATED, kubaResponse.getStatusInfo());

        URI wombatLocation = wombatResponse.getLocation();
        URI kubaLocation = kubaResponse.getLocation();

        Response secondWombatResponse = addCook(base, wombatName, "mail@wombatsoftware.de");
        Assert.assertEquals(Response.Status.SEE_OTHER, secondWombatResponse.getStatusInfo());
        Assert.assertEquals(wombatLocation, secondWombatResponse.getLocation());

        wombatResponse = requestCookWithURL(wombatLocation.toString());
        kubaResponse = requestCookWithURL(kubaLocation.toString());

        Assert.assertEquals(Response.Status.OK, wombatResponse.getStatusInfo());
        Assert.assertEquals(Response.Status.OK, kubaResponse.getStatusInfo());

        CookResponse wombatEntity = wombatResponse.readEntity(CookResponse.class);
        CookResponse kubaEntity = kubaResponse.readEntity(CookResponse.class);

        Assert.assertEquals(wombatName, wombatEntity.getName());
        Assert.assertEquals(kubaName, kubaEntity.getName());
    }

    @Test
    @InSequence(2)
    public void testGetAllCooks(@ArquillianResource URL base) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + CookResource.cookURI);

        Response response = target.request().get();
        Assert.assertEquals(Response.Status.OK, response.getStatusInfo());

        CooksResponse cooksResponse = response.readEntity(CooksResponse.class);

        Assert.assertEquals(2, cooksResponse.getCooks().size());
    }

    @Test
    @InSequence(3)
    public void testLoginCook(@ArquillianResource URL base) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + CookResource.cookURI + "login/");

        Response response = target.request().post(Entity.entity(new LoginCookRequest("Wombat", "mail@wombatsoftware.de"), MediaType.APPLICATION_JSON_TYPE));
        Assert.assertEquals(Response.Status.OK, response.getStatusInfo());
        response.close();

        response = target.request().post(Entity.entity(new LoginCookRequest("Wombat", "FakeEmail@example.com"), MediaType.APPLICATION_JSON_TYPE));
        Assert.assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo());
        response.close();

        response = target.request().post(Entity.entity(new LoginCookRequest("WrongName", "mail@wombatsoftware.de"), MediaType.APPLICATION_JSON_TYPE));
        Assert.assertEquals(Response.Status.BAD_REQUEST, response.getStatusInfo());
    }

    private Response requestCookWithURL(String uri) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uri);

        return target.request().get();
    }

    private Response requestCookWithID(URL base, long id) throws Exception {
        return requestCookWithURL(base.toURI() + RestApplication.REST_PATH + CookResource.cookURI + id + "/");
    }

    private Response addCook(URL base, String name, String email) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + CookResource.cookURI);

        return target.request().post(Entity.entity(new AddCookRequest(name, email), MediaType.APPLICATION_JSON_TYPE));
    }
}