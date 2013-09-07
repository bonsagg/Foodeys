package eu.jugcologne.foodeys.services.db;

import eu.jugcologne.foodeys.FoodeysMarker;
import eu.jugcologne.foodeys.persistence.model.Ingredient;
import eu.jugcologne.foodeys.persistence.model.Recipe;
import eu.jugcologne.foodeys.persistence.model.Unit;
import eu.jugcologne.foodeys.services.api.IngredientService;
import eu.jugcologne.foodeys.services.api.RecipeService;
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
import java.math.BigDecimal;

@RunWith(Arquillian.class)
public class DBIngredientServiceTest {
    @Inject
    private IngredientService ingredientService;

    @Inject
    private RecipeService recipeService;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "DBIngredientServiceTest.war")
                .addPackages(true, FoodeysMarker.class.getPackage())

                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @InSequence(1)
    public void testIsDeployed() {
        Assert.assertNotNull(ingredientService);
    }

    @Test
    @InSequence(2)
    @UsingDataSet("datasets/DBIngredientServiceTest.yml")
    public void testFindByID() {
        Ingredient tomatoes = ingredientService.findByID(1l);
        Ingredient salt = ingredientService.findByID(2l);
        Ingredient pepper = ingredientService.findByID(3l);

        Assert.assertNotNull(tomatoes);
        Assert.assertNotNull(salt);
        Assert.assertNotNull(pepper);

        Assert.assertEquals(new BigDecimal(5).setScale(2), tomatoes.getAmount());
        Assert.assertEquals(Unit.PIECE, tomatoes.getUnit());

        Assert.assertEquals(new BigDecimal(1).setScale(2), salt.getAmount());
        Assert.assertEquals(Unit.PORTION, salt.getUnit());

        Assert.assertEquals(new BigDecimal(1).setScale(2), pepper.getAmount());
        Assert.assertEquals(Unit.PORTION, pepper.getUnit());

        Recipe recipe = recipeService.findByID(1l);

        Assert.assertEquals(recipe, tomatoes.getRecipe());
        Assert.assertEquals(recipe, salt.getRecipe());
        Assert.assertEquals(recipe, pepper.getRecipe());
    }
}