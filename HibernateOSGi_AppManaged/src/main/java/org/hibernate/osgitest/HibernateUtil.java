/* 
 * Hibernate, Relational Persistence for Idiomatic Java
 * 
 * JBoss, Home of Professional Open Source
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package org.hibernate.osgitest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * @author Brett Meyer
 */

public class HibernateUtil {

    private static SessionFactory sf;

    private static EntityManagerFactory emf;
    
    public static Session getSession() {
    	return getSessionFactory().openSession();
    }

    private static SessionFactory getSessionFactory() {
    	if (sf == null) {
	    	Configuration configuration = new Configuration();
	        configuration.configure();
	        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
	        		.applySettings(configuration.getProperties()).buildServiceRegistry();        
	        sf = configuration.buildSessionFactory(serviceRegistry);
    	}
        return sf;
    }
    
    public static EntityManager getEntityManager() {
    	return getEntityManagerFactory().createEntityManager();
    }

    private static EntityManagerFactory getEntityManagerFactory() {
    	if (emf == null) {
    		HibernatePersistence hp = new HibernatePersistence();
	    	emf = hp.createEntityManagerFactory( "HibernateOSGi_AppManaged", null );
    	}
    	return emf;
    }
}