package eu.jugcologne.foodeys.services.db;

import eu.jugcologne.foodeys.FoodeysMarker;
import eu.jugcologne.foodeys.persistence.model.Food;
import eu.jugcologne.foodeys.persistence.model.Recipe;
import eu.jugcologne.foodeys.services.api.FoodService;
import eu.jugcologne.foodeys.services.api.RecipeService;
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
import java.util.ArrayList;
import java.util.List;

@RunWith(Arquillian.class)
public class DBRecipeServiceTest {
    public static final String tomatoSoupName = "Tomato Soup";
    public static final String tomatoSoupInstructions = "Cook tomatoes, add salt and pepper";

    @Inject
    private RecipeService recipeService;

    @Inject
    private FoodService foodService;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "DBRecipeServiceTest.war")
                .addPackages(true, FoodeysMarker.class.getPackage())
                .deleteClass(DefaultDataImporter.class)

                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @InSequence(1)
    public void testIsDeployed() {
        Assert.assertNotNull(recipeService);
    }

    @Test
    @InSequence(2)
    @UsingDataSet("datasets/DBRecipeServiceTest.yml")
    public void testFindByID() {
        Recipe tomatoSoup = recipeService.findByID(1l);

        Assert.assertNotNull(tomatoSoup);
        Assert.assertEquals(tomatoSoupName, tomatoSoup.getName());
        Assert.assertEquals(3, tomatoSoup.getIngredients().size());
    }

    @Test
    @InSequence(3)
    @UsingDataSet("datasets/DBRecipeServiceTest.yml")
    public void testFindAllRecipes() {
        List<Recipe> recipes =  recipeService.findAllRecipes();

        Assert.assertEquals(1, recipes.size());

        Recipe tomatoSoup = recipes.get(0);

        Assert.assertEquals(tomatoSoupName, tomatoSoup.getName());
        Assert.assertEquals(tomatoSoupInstructions, tomatoSoup.getInstructions());
        Assert.assertEquals(3, tomatoSoup.getIngredients().size());
    }

    @Test
    @InSequence(4)
    @UsingDataSet("datasets/DBRecipeServiceTest.yml")
    public void testFindAllRecipesForFood() {
        Food tomato = foodService.findByID(1l);
        Food salt = foodService.findByID(2l);
        Food pepper = foodService.findByID(3l);

        List<Recipe> tomatoRecipes = recipeService.findAllRecipesForFood(tomato);
        List<Recipe> saltRecipes = recipeService.findAllRecipesForFood(salt);
        List<Recipe> pepperRecipes = recipeService.findAllRecipesForFood(pepper);

        Assert.assertEquals(1, tomatoRecipes.size());
        Assert.assertEquals(1, saltRecipes.size());
        Assert.assertEquals(1, pepperRecipes.size());

        Recipe tomatoSoup1 = tomatoRecipes.get(0);
        Recipe tomatoSoup2 = saltRecipes.get(0);
        Recipe tomatoSoup3 = pepperRecipes.get(0);

        Assert.assertEquals(tomatoSoup2, tomatoSoup1);
        Assert.assertEquals(tomatoSoup3, tomatoSoup2);
        Assert.assertEquals(tomatoSoup1, tomatoSoup3);
    }

    @Test
    @InSequence(5)
    @UsingDataSet("datasets/DBRecipeServiceTest.yml")
    public void findAllRecipesByFoodNamesWithOneRecipe() {
        Food tomato = foodService.findByID(1l);
        Food salt = foodService.findByID(2l);
        Food pepper = foodService.findByID(3l);

        List<String> foods = new ArrayList<>();
        foods.add("Tomato");

        List<Recipe> results = recipeService.findAllRecipesByFoodNames(foods);

        Assert.assertEquals(1, results.size());
        Recipe tomatoSoup = results.get(0);

        Assert.assertEquals("Tomato Soup", tomatoSoup.getName());
    }

    @Test
    @InSequence(6)
    @UsingDataSet("datasets/DBRecipeServiceTestWithMultipleRecipes.yml")
    public void findAllRecipesByFoodNamesWithMultipleRecipe() {
        Food tomato = foodService.findByID(1l);
        Food salt = foodService.findByID(2l);
        Food pepper = foodService.findByID(3l);

        List<String> foods = new ArrayList<>();
        foods.add("Tomato");

        List<Recipe> results = recipeService.findAllRecipesByFoodNames(foods);

        Assert.assertEquals(2, results.size());
        Recipe tomatoSoup = results.get(0);
        Recipe tomatoMash = results.get(1);

        Assert.assertEquals("Tomato Soup", tomatoSoup.getName());
        Assert.assertEquals("Tomato Mash", tomatoMash.getName());
    }
}