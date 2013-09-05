package eu.jugcologne.foodeys.services.db;

import eu.jugcologne.foodeys.persistence.model.AbstractEntity;
import eu.jugcologne.foodeys.persistence.model.Cook;
import eu.jugcologne.foodeys.services.AbstractService;
import eu.jugcologne.foodeys.services.api.CookService;
import eu.jugcologne.foodeys.services.api.IngredientService;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

/**
 * Service which provides methods and configurations specific to persistent Cook Objects
 * 
 * @author Daniel Sachse <mail@wombatsoftware.de>
 *
 */

@Stateless
public class DBCookService extends AbstractService implements CookService {
    private static final long serialVersionUID = -473372348203262950L;

    @Override
    public Cook findByID(long id) {
        return findById(Cook.class, id);
    }

    @Override
    public List<Cook> findAllCooks() {
        return findAll(Cook.class);
    }

    @Override
    public Cook findCookByEmailAddress(String email) {
        try {
            return em.createNamedQuery(Cook.findCookByEmail, Cook.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    @Override
    public Cook findCookByToken(String token) {
        return findByID(Long.valueOf(token));
    }
}