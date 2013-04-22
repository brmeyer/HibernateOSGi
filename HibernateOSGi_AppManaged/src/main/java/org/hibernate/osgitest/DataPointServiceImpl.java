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

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.osgitest.entity.DataPoint;

/**
 * @author Brett Meyer
 */
public class DataPointServiceImpl implements DataPointService {

	public void add(DataPoint dp) {
		Session s = HibernateUtil.getSession();
		s.getTransaction().begin();
		s.persist( dp );
		s.getTransaction().commit();
		s.close();
	}

	public void update(DataPoint dp) {
		Session s = HibernateUtil.getSession();
		s.getTransaction().begin();
		s.update( dp );
		s.getTransaction().commit();
		s.close();
	}

	public DataPoint get(long id) {
		Session s = HibernateUtil.getSession();
		s.getTransaction().begin();
		DataPoint dp = (DataPoint) s.createCriteria( DataPoint.class ).add(
				Restrictions.eq( "id", id ) ).uniqueResult();
		s.getTransaction().commit();
		s.close();
		return dp;
	}

	public List<DataPoint> getAll() {
		Session s = HibernateUtil.getSession();
		s.getTransaction().begin();
		List list = s.createQuery( "from DataPoint" ).list();
		s.getTransaction().commit();
		s.close();
		return list;
	}
	
	public Map<Number, DataPoint> getRevisions(long id) {
		EntityManager em = HibernateUtil.getEntityManager();
		AuditReader reader = AuditReaderFactory.get(em);
		List<Number> revisionNums = reader.getRevisions( DataPoint.class, id );
		return reader.findRevisions( DataPoint.class, new HashSet<Number>(revisionNums) );
	}

	public void deleteAll() {
		Session s = HibernateUtil.getSession();
		s.getTransaction().begin();
		s.createQuery( "delete from DataPoint" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}

	public void addJPA(DataPoint dp) {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist( dp );
		em.getTransaction().commit();
		em.close();
	}

	public List<DataPoint> getAllJPA() {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		List list = em.createQuery( "from DataPoint" ).getResultList();
		em.getTransaction().commit();
		em.close();
		return list;
	}

	public void deleteAllJPA() {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.createQuery( "delete from DataPoint" ).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

}
