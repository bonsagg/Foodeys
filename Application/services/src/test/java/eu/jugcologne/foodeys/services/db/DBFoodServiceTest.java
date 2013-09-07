package eu.jugcologne.foodeys.services.db;

import eu.jugcologne.foodeys.FoodeysMarker;
import eu.jugcologne.foodeys.persistence.model.Food;
import eu.jugcologne.foodeys.services.api.FoodService;
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
public class DBFoodServiceTest {
    public static final String tomatoName = "Tomato";
    public static final String orangeName = "Orange";
    public static final String tamarilloName = "Tamarillo";
    public static final String cherryName = "Cherry";

    @Inject
    private FoodService foodService;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "DBFoodServiceTest.war")
                .addPackages(true, FoodeysMarker.class.getPackage())

                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @InSequence(1)
    public void testIsDeployed() {
        Assert.assertNotNull(foodService);
    }

    @Test
    @InSequence(2)
    @UsingDataSet("datasets/DBFoodServiceTest.yml")
    public void testFindAllFoods() {
        List<Food> foods = foodService.findAllFoods();

        Assert.assertEquals(3, foods.size());

        Food tomato = foods.get(0);
        Food orange = foods.get(1);
        Food tamarillo = foods.get(2);

        Assert.assertNotNull(tomato);
        Assert.assertNotNull(orange);
        Assert.assertNotNull(tamarillo);

        Assert.assertEquals(tomatoName, tomato.getName());
        Assert.assertEquals(orangeName, orange.getName());
        Assert.assertEquals(tamarilloName, tamarillo.getName());
    }

    @Test
    @InSequence(2)
    @UsingDataSet("datasets/DBFoodServiceTest.yml")
    public void testFindAutocompleteSuggestionsWithT() {
        List<String> suggestions = foodService.findAutocompleteSuggestions("T");

        Assert.assertEquals(2, suggestions.size());

        String tamarillo = suggestions.get(0);
        String tomato = suggestions.get(1);

        Assert.assertNotNull(tomato);
        Assert.assertNotNull(tamarillo);

        Assert.assertEquals(tomatoName, tomato);
        Assert.assertEquals(tamarilloName, tamarillo);
    }

    @Test
    @InSequence(3)
    @UsingDataSet("datasets/DBFoodServiceTest.yml")
    public void testFindAutocompleteSuggestionsWithTo() {
        List<String> suggestions = foodService.findAutocompleteSuggestions("To");

        Assert.assertEquals(1, suggestions.size());

        String tomato = suggestions.get(0);

        Assert.assertNotNull(tomato);
        Assert.assertEquals(tomatoName, tomato);
    }

    @Test
    @InSequence(4)
    @UsingDataSet("datasets/DBFoodServiceTest.yml")
    public void testFindFoodByName() {
        Food tomato = foodService.findFoodByName(tomatoName);
        Food orange = foodService.findFoodByName(orangeName);
        Food tamarillo = foodService.findFoodByName(tamarilloName);
        Food cherry = foodService.findFoodByName(cherryName);

        Assert.assertNotNull(tomato);
        Assert.assertNotNull(orange);
        Assert.assertNotNull(tamarillo);
        Assert.assertNull(cherry);

        Assert.assertEquals(tomatoName, tomato.getName());
        Assert.assertEquals(orangeName, orange.getName());
        Assert.assertEquals(tamarilloName, tamarillo.getName());
    }

    @Test
    @InSequence(4)
    @UsingDataSet("datasets/DBFoodServiceTest.yml")
    public void testFindByID() {
        Food tomato = foodService.findByID(1l);
        Food orange = foodService.findByID(2l);
        Food tamarillo = foodService.findByID(3l);
        Food cherry = foodService.findByID(4l);

        Assert.assertNotNull(tomato);
        Assert.assertNotNull(orange);
        Assert.assertNotNull(tamarillo);
        Assert.assertNull(cherry);

        Assert.assertEquals(tomatoName, tomato.getName());
        Assert.assertEquals(orangeName, orange.getName());
        Assert.assertEquals(tamarilloName, tamarillo.getName());
    }
}