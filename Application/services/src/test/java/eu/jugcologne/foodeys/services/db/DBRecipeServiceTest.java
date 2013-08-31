package eu.jugcologne.foodeys.services.db;

import eu.jugcologne.foodeys.FoodeysMarker;
import eu.jugcologne.foodeys.services.api.RecipeService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class DBRecipeServiceTest {
    @Inject
    private RecipeService recipeService;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "DBRecipeServiceTest.war")
                .addPackages(true, FoodeysMarker.class.getPackage())

                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testIsDeployed() {
        Assert.assertNotNull(recipeService);
    }
}