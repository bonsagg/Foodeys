package eu.jugcologne.foodeys.services.api;

import eu.jugcologne.foodeys.persistence.model.Cook;

import java.util.List;

/**
 * @author Daniel Sachse
 * @date 29.08.13 22:25
 */
public interface CookService extends Service {
    List<Cook> findAllCooks();
    Cook findCookByEmailAddress(String email);
    Cook findCookByToken(String token);
}