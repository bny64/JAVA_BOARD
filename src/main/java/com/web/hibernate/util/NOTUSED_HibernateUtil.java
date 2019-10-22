package com.web.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class NOTUSED_HibernateUtil {

	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;

	
	
	static {
		if (sessionFactory == null) {
			try {
				// Create StandardServiceRegistry
				//     / 경로는 src/main/resources 다음
				standardServiceRegistry = new StandardServiceRegistryBuilder().configure("/database/hibernate.cfg.xml").build();
				// Create MetadataSources
				MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
				// Create Metadata
				Metadata metadata = metadataSources.getMetadataBuilder().build();
				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				e.printStackTrace();
				if (standardServiceRegistry != null) {
					StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
				}
			}
		}
	}

	// Utility method to return SessionFactory
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
