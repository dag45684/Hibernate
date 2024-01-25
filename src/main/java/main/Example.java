package main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class Example {

	public static void main(String[] args) {
		Session s = Instance.getSessionFactory().openSession();
		s.getTransaction().begin();
		
		Query<Clientes> semen = s.createQuery("from Clientes where id = 2", Clientes.class);
		List<Clientes> pene = semen.list();
		
		Clientes c = new Clientes();
		
		s.get(Clientes.class, "puta");
		
		
		
		//codigo de mierda
		
		
		s.getTransaction().commit();

	}

}
