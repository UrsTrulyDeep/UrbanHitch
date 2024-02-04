package com.autoavenue.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProvider {

    private static SessionFactory factory;

    private FactoryProvider() {
    }

    public static SessionFactory getFactory() {

        if (factory == null) {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }

        return factory;
    }

    public void closeFactory() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
