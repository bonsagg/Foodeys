package eu.jugcologne.foodeys.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(RestApplication.REST_PATH)
public class RestApplication extends Application {
    public static final String REST_PATH = "rest";
}