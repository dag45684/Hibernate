package main;

import org.hibernate.Session;

public class Exercise4 {
	
	//En casa utilizo Linux, y parece ser que la vista de Hibernate en Eclipse Linux
	//no funciona bien, asi que he tenido que hacer los ejercicios sin poder comprobar
	//si se ejecutaban correctamente.

	public static void main(String[] args) {
		Session s = Instance.getSessionFactory().openSession();
		s.getTransaction().begin();
		
		//No tengo la bdd con Empleados en el esquema porque tuve que borrarla,
		//asi que va a dar error, pero seria basicamente esto:
		s.createQuery("from Empleados where empNo = 7369", Empleados.class)
			.list()
			.foreach( e -> System.out.println(e) );

		s.getTransaction().commit();

	}

}
