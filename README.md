HibernateOSGi
=============

This repository provides example client bundles for using Hibernate ORM in an OSGi environment.  There is currently only one example, but more are in the works.  All examples assume Apache Karaf is the runtime, however each should be able to be molded for alternative environments.

H2
--

All examples use in-memory H2.

HibernateOSGi_ContainerManaged
------------------------------

This bundle assumes an Enterprise OSGi environment and an OSGi container-managed EntityManager.  src/main/resources/OSGI-INF/blueprint/blueprint.xml sets up the persistence unit and maps the OSGi container-controlled EntityManager to a Bean property.

/datasource-h2.xml provides a blueprint to be installing in Karaf.  This examples' feature.xml file automatically does so.  Note that the "blueprint:file:...datasource-h2.xml" will need to be modified.  This datasource route is taken to allow the persistence.xml to use a JTA Data Source via Karaf's JNDI.

features.xml shows an example list of (somewhat) order-dependent bundles that need to be activated in the runtime.  This can be used directly in Karaf.  Note that JTA, JPA, and JNDI dependency bundles can be replaced by Karaf's "transaction", "jpa", and "jndi" features.  However, I list the bundles explicitly as a reference.

blueprint.xml also provides three Karaf Shell commands for use during testing:  "dp:add {name}", "dp:getall", and "dp:deleteall"

To run the example:
<ol>
<li>$KARAF_HOME/bin/karaf</li>
<li>features:addurl file:/.../HibernateOSGi/HibernateOSGi_ContainerManaged/features.xml</li>
<li>features:install hibernate-test</li>
<li>dp:add foo</li>
<li>dp:getall (should print "{id}, foo")</li>
<li>dp:deleteall</li>
<li>dp:getall (nothing should print)</li>
</ol>

HibernateOSGi_AppManaged
------------------------------

This bundle demonstrates how to use Hibernate SessionFactories or JPA EntityManagerFactories directly in your application, rather than relying on the OSGi container-managed EntityManager.

features.xml shows an example list of (somewhat) order-dependent bundles that need to be activated in the runtime.

blueprint.xml also provides six Karaf Shell commands for use during testing:  "dp:add {name}", "dp:getall", "dp:deleteall", "dp:addjpa {name}", "dp:getalljpa", and "dp:deletealljpa".  The commands proceeded by "jpa" utilize JPA's EntityManagerFactory, while the others directly use Hibernate's SessionFactory.

To run the example:
<ol>
<li>$KARAF_HOME/bin/karaf</li>
<li>features:addurl file:/.../HibernateOSGi/HibernateOSGi_AppManaged/features.xml</li>
<li>features:install hibernate-test</li>
<li>dp:add foo</li>
<li>dp:getall (should print "{id}, foo")</li>
<li>dp:deleteall</li>
<li>dp:getall (nothing should print)</li>
<li>dp:addjpa foo</li>
<li>dp:getalljpa (should print "{id}, foo")</li>
<li>dp:deletealljpa</li>
<li>dp:getalljpa (nothing should print)</li>
</ol>
