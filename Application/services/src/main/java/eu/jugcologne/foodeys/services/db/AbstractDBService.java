package eu.jugcologne.foodeys.services.db;

import eu.jugcologne.foodeys.services.AbstractService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Abstract Service specific for the InMemory solution that configures the system to use the InMemory database
 *
 * @author Daniel Sachse <mail@wombatsoftware.de>
 */
public abstract class AbstractDBService extends AbstractService {
    private static final long serialVersionUID = 989016797578684211L;

    @PersistenceContext
    protected EntityManager em;

    @Override
    public EntityManager getEm() {
        return em;
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
}