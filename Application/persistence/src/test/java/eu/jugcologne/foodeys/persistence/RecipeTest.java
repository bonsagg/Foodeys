package eu.jugcologne.foodeys.persistence;

import eu.jugcologne.foodeys.persistence.model.Recipe;
import eu.jugcologne.foodeys.persistence.util.DbConst;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * @author "Sergey Shcherbakov"
 */
@RunWith(Arquillian.class)
public class RecipeTest {
    /* TODO: Rewrite Tests to new infrastructure

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(Recipe.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void prepare() throws Exception {
        cleanData();
        insertData();
        startTransaction();
    }

    private void cleanData() throws Exception {
        utx.begin();
        em.joinTransaction();

        System.out.println("Clearing the database...");
        em.createQuery("delete from " + DbConst.Recipe).executeUpdate();

        utx.commit();
    }

    private void insertData() throws Exception {
        utx.begin();
        em.joinTransaction();

        Recipe recipe = new Recipe();
        recipe.setName("Test");
        recipe.setInstructions("Test instructions");
        em.persist(recipe);

        utx.commit();
        em.clear();
    }

    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }

    @After
    public void commitTransaction() throws Exception {
        utx.commit();
    }

    @Test
    public void test() throws Exception {
        Recipe recipe = em.find(Recipe.class, 1L);
        assertNotNull(recipe);
        assertEquals("Test", recipe.getName());
    }*/
}