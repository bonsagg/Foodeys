package eu.jugcologne.foodeys.services.memory;

import javax.ejb.Stateless;
import eu.jugcologne.foodeys.services.api.UnitService;

/**
 * Service which provides methods and configurations specific to persistent Unit Objects
 * 
 * @author Daniel Sachse <mail@wombatsoftware.de>
 *
 */
@Stateless
public class DBUnitService extends AbstractInMemoryService implements UnitService {
    private static final long serialVersionUID = -473372348203262950L;
}