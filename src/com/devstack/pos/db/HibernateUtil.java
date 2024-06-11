package com.devstack.pos.db;

import com.devstack.pos.entity.AccessPoint;
import com.devstack.pos.entity.AccessPointCrud;
import com.devstack.pos.entity.User;
import com.devstack.pos.entity.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil { // Hibernate system i trigger seyyum class
    private static final SessionFactory sessionFactory = buildSessionFactory();
    // sessionFactory iza object a veachchim session a edukka ealum getSession class use pannaama

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(UserRole.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(AccessPoint.class)
                .addAnnotatedClass(AccessPointCrud.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        return metadata.getSessionFactoryBuilder()
                .build();
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
