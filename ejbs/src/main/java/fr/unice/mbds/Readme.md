A JavaEE project built with JPA, EJBs and JAX-RS to describe
the street letter boxes in France.

To package :
	
	mvn package

To deploy on Glassfish server on port 8080 :

	asadmin deploy --force ejbs/target/bal.war

Access localhost:8080/bal/