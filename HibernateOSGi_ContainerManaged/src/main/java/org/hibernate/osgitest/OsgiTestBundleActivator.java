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

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Brett Meyer
 */
public class OsgiTestBundleActivator implements BundleActivator {

	public void start(BundleContext bc) throws Exception {
//		// This is "un-managed."  To get the "managed" unit from Aries, need
//		// to filter on org.apache.aries.jpa.container.managed.
//		ServiceReference[] serviceReferences = bc.getServiceReferences( EntityManagerFactory.class.getName(),
//				String.format( "(%s=%s)", EntityManagerFactoryBuilder.JPA_UNIT_NAME, "HibernateOSGi_ContainerManaged" ) );
//		if ( serviceReferences == null || serviceReferences.length == 0 ) {
//			System.out.println( "NO EntityManagerFactory" );
//			return;
//		}
//
//		EntityManagerFactory emf = (EntityManagerFactory) bc.getService( serviceReferences[0] );
//		
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		DataPoint dp = new DataPoint();
//		dp.setName( "foo" );
//		em.persist( dp );
//		em.getTransaction().commit();
//		em.close();
//		System.out.println("PERSISTED");
//		
//		em = emf.createEntityManager();
//		em.getTransaction().begin();
//		em.find( DataPoint.class, dp.getId() );
//		em.getTransaction().commit();
//		em.close();
//		System.out.println("FOUND DATAPOINT: " + dp.getName());
	}

	public void stop(BundleContext bc) throws Exception {

	}

}
