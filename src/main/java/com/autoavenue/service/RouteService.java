package com.autoavenue.service;

import com.autoavenue.model.Route;

import java.util.HashMap;
import java.util.Map;

public class RouteService {
    private Map<String, Route> routeMap; 
    
    public RouteService() {
        routeMap = new HashMap<String, Route>();
        routeMap.put("Source1-Destination1", new Route("Source1", "Destination1", 10.0, 30, 20.0));
        routeMap.put("Source2-Destination2", new Route("Source2", "Destination2", 15.0, 45, 30.0));
    }

    public Route getRouteBySourceAndDestination(String source, String destination) {
        String key = source + "-" + destination;
        return routeMap.get(key);
    }
}
