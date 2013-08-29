package eu.jugcologne.foodeys.services.db;

import eu.jugcologne.foodeys.services.AbstractService;
import eu.jugcologne.foodeys.services.api.UnitService;

import javax.ejb.Stateless;

/**
 * Service which provides methods and configurations specific to persistent Unit Objects
 * 
 * @author Daniel Sachse <mail@wombatsoftware.de>
 *
 */
@Stateless
public class DBUnitService extends AbstractService implements UnitService {
    private static final long serialVersionUID = -473372348203262950L;
}