package SantasFactory.NorthPoleElfs.Service.HiernateService;

import SantasFactory.Gifts.GoodKid;
import SantasFactory.Gifts.Present;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class GoodKidHibernateService {

    /*
    This method initializes and returns a Hibernate SessionFactory instance,
    which serves as a key component for managing Hibernate sessions and their associated configurations.
    The method follows a singleton pattern,
    ensuring that only one SessionFactory is created and reused throughout the application.
     */

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){

        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.DRIVER,"org.h2.Driver");
            properties.put(Environment.URL, "jdbc:h2:tcp://localhost/~/test");
            properties.put(Environment.USER, "sa");
            properties.put(Environment.PASS, "");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "create-drop");

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(GoodKid.class);
            configuration.addAnnotatedClass(Present.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

}
