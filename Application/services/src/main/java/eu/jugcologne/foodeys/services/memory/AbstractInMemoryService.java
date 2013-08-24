package eu.jugcologne.foodeys.services.memory;

import eu.jugcologne.foodeys.services.AbstractService;

/**
 * Abstract Service specific for the InMemory solution that configures the system to use the InMemory database
 *
 * @author Daniel Sachse <mail@wombatsoftware.de>
 */
public abstract class AbstractInMemoryService extends AbstractService {
    private static final long serialVersionUID = 989016797578684211L;

//  TODO: Should @Inject the InMemory DB
    /*@PersistenceContext
    protected EntityManager em;

    @Override
    public EntityManager getEm() {
        return em;
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }*/
}