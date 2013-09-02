package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.FoodeysMarker;
import eu.jugcologne.foodeys.persistence.model.Cook;
import eu.jugcologne.foodeys.rest.api.CookResource;
import eu.jugcologne.foodeys.rest.api.model.AddCookRequest;
import eu.jugcologne.foodeys.rest.model.CookResponse;
import eu.jugcologne.foodeys.services.api.CookService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.TestExecutionPhase;
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

@RunWith(Arquillian.class)
public class CookResourceTest {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private CookService cookService;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "CookResourceTest.war")
                .addPackages(true, FoodeysMarker.class.getPackage())
                .addClass(RestApplication.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @InSequence(1)
    @UsingDataSet("datasets/prepareTestAddCooks.yml")
    @Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.STRICT)
    public void prepareTestAddCooks() {
        Cook cook = cookService.findByID(1l);
        Assert.assertEquals("Wombat", cook.getName());
    }

    @Test
    @InSequence(2)
    @RunAsClient
    public void testAddCooks(@ArquillianResource URL base) throws Exception {
        final String wombatName = "Wombat";
        final String kubaName = "Kuba";

        Response wombatResponse = requestCookWithID(base, 1L);
        Response kubaResponse = requestCookWithID(base, 2L);

        Assert.assertEquals(Response.Status.NO_CONTENT, wombatResponse.getStatusInfo());
        Assert.assertEquals(Response.Status.NO_CONTENT, kubaResponse.getStatusInfo());

        wombatResponse = addCook(base, wombatName);
        kubaResponse = addCook(base, kubaName);

        Assert.assertEquals(Response.Status.CREATED, wombatResponse.getStatusInfo());
        Assert.assertEquals(Response.Status.CREATED, kubaResponse.getStatusInfo());

        URI wombatLocation = wombatResponse.getLocation();
        URI kubaLocation = kubaResponse.getLocation();

        wombatResponse = requestCookWithURL(wombatLocation.toString());
        kubaResponse = requestCookWithURL(kubaLocation.toString());

        Assert.assertEquals(Response.Status.OK, wombatResponse.getStatusInfo());
        Assert.assertEquals(Response.Status.OK, kubaResponse.getStatusInfo());

        CookResponse wombatEntity = wombatResponse.readEntity(CookResponse.class);
        CookResponse kubaEntity = kubaResponse.readEntity(CookResponse.class);

        Assert.assertEquals(wombatName, wombatEntity.getName());
        Assert.assertEquals(kubaName, kubaEntity.getName());

        Assert.assertEquals(new URI(base.toURI() + RestApplication.REST_PATH + CookResource.cookURI + wombatEntity.getId() + "/"), wombatLocation);
        Assert.assertEquals(new URI(base.toURI() + RestApplication.REST_PATH + CookResource.cookURI + kubaEntity.getId() + "/"), kubaLocation);
    }

    private Response requestCookWithURL(String uri) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uri);

        return target.request().get();
    }

    private Response requestCookWithID(URL base, long id) throws Exception {
        return requestCookWithURL(base.toURI() + RestApplication.REST_PATH + CookResource.cookURI + id + "/");
    }

    private Response addCook(URL base, String name) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(base.toURI() + RestApplication.REST_PATH + CookResource.cookURI);

        return target.request().post(Entity.entity(new AddCookRequest(name), MediaType.APPLICATION_JSON_TYPE));
    }
}