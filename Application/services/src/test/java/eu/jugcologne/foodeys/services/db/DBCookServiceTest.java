package eu.jugcologne.foodeys.services.db;

import eu.jugcologne.foodeys.FoodeysMarker;
import eu.jugcologne.foodeys.persistence.model.Cook;
import eu.jugcologne.foodeys.services.api.CookService;
import eu.jugcologne.foodeys.services.importer.DefaultDataImporter;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class DBCookServiceTest {
    @Inject
    private CookService cookService;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "DBCookServiceTest.war")
                .addPackages(true, FoodeysMarker.class.getPackage())
                .deleteClass(DefaultDataImporter.class)

                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @InSequence(1)
    public void testIsDeployed() {
        Assert.assertNotNull(cookService);
    }

    @Test
    @InSequence(2)
    @UsingDataSet("datasets/DBCookServiceTest.yml")
    public void testFindByID() {
        Cook wombat = cookService.findByID(1l);
        Cook kuba = cookService.findByID(2l);
        Cook fail = cookService.findByID(3l);

        Assert.assertNotNull(wombat);
        Assert.assertNotNull(kuba);
        Assert.assertNull(fail);

        Assert.assertEquals("Wombat", wombat.getName());
        Assert.assertEquals("mail@wombatsoftware.de", wombat.getEmail());

        Assert.assertEquals("Kuba", kuba.getName());
        Assert.assertEquals("jacob@wombatsoftware.de", kuba.getEmail());
    }

    @Test
    @InSequence(3)
    @UsingDataSet("datasets/DBCookServiceTest.yml")
    public void testFindAllCooks() {
        List<Cook> cooks = cookService.findAllCooks();

        Assert.assertEquals(2, cooks.size());

        Cook wombat = cooks.get(0);
        Cook kuba = cooks.get(1);

        Assert.assertNotNull(wombat);
        Assert.assertNotNull(kuba);

        Assert.assertEquals("Wombat", wombat.getName());
        Assert.assertEquals("mail@wombatsoftware.de", wombat.getEmail());

        Assert.assertEquals("Kuba", kuba.getName());
        Assert.assertEquals("jacob@wombatsoftware.de", kuba.getEmail());
    }

    @Test
    @InSequence(4)
    @UsingDataSet("datasets/DBCookServiceTest.yml")
    public void testFindCookByEmailAddress() {
        Cook wombat = cookService.findCookByEmailAddress("mail@wombatsoftware.de");
        Cook kuba = cookService.findCookByEmailAddress("jacob@wombatsoftware.de");
        Cook fail = cookService.findCookByEmailAddress("fail@example.com");

        Assert.assertNotNull(wombat);
        Assert.assertNotNull(kuba);
        Assert.assertNull(fail);

        Assert.assertEquals("Wombat", wombat.getName());
        Assert.assertEquals("mail@wombatsoftware.de", wombat.getEmail());

        Assert.assertEquals("Kuba", kuba.getName());
        Assert.assertEquals("jacob@wombatsoftware.de", kuba.getEmail());
    }

    @Test
    @InSequence(5)
    @UsingDataSet("datasets/DBCookServiceTest.yml")
    public void testFindCookByToken() {
        Cook wombat = cookService.findCookByToken("1");
        Cook kuba = cookService.findCookByToken("2");
        Cook fail = cookService.findCookByToken("3");

        Assert.assertNotNull(wombat);
        Assert.assertNotNull(kuba);
        Assert.assertNull(fail);

        Assert.assertEquals("Wombat", wombat.getName());
        Assert.assertEquals("mail@wombatsoftware.de", wombat.getEmail());

        Assert.assertEquals("Kuba", kuba.getName());
        Assert.assertEquals("jacob@wombatsoftware.de", kuba.getEmail());
    }

    /*
        TODO: Fix it somehow. It fails in Glassfish 4 with : "Internal Exception: java.sql.SQLIntegrityConstraintViolationException".
        JBoss Wildfly is fine :)

        @Test
        @InSequence(5)
        @UsingDataSet("datasets/DBCookServiceTest.yml")
        public void testSaveNewCook() {
            Cook cook = new Cook("Wombatus", "wombatus@wombatsoftware.de");
            cookService.save(cook);

            Assert.assertEquals(3, (long) cook.getId());
        }
    */
}