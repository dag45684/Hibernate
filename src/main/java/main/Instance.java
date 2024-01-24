package main;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Instance {

		private static final SessionFactory sf = buildSessionFactory();
		
		private static SessionFactory buildSessionFactory() {
			try {
				return new Configuration().configure().buildSessionFactory(
						new StandardServiceRegistryBuilder().configure().build());
			} catch (Exception e) {
				System.err.println("Error al crear la sesion" + e);
				throw new ExceptionInInitializerError(e);
			}
		}
		
		public static SessionFactory getSessionFactory() {
			return sf;
		}

}
