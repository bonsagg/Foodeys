package eu.jugcologne.foodeys.services.memory;

import eu.jugcologne.foodeys.entities.AbstractEntity;
import eu.jugcologne.foodeys.services.AbstractService;
import eu.jugcologne.foodeys.services.api.IngredientService;
import eu.jugcologne.foodeys.services.api.Service;
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
public class DBIngredientServiceTest {
    @Inject
    private IngredientService ingredientService;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(DBIngredientService.class)
                .addClass(AbstractInMemoryService.class)
                .addClass(AbstractService.class)

                .addClass(IngredientService.class)
                .addClass(Service.class)

                .addClass(AbstractEntity.class)

                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testIsDeployed() {
        Assert.assertNotNull(ingredientService);
    }
}