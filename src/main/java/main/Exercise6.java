package main;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import jakarta.persistence.PersistenceException;

public class Exercise6 {

	public static void main(String[] args) {
		
		//En casa utilizo Linux, y parece ser que la vista de Hibernate en Eclipse Linux
		//no funciona bien, asi que he tenido que hacer los ejercicios sin poder comprobar
		//si se ejecutaban correctamente.

		Session s = Instance.getSessionFactory().openSession();
		s.getTransaction().begin();
		
		Clientes cli1 = new Clientes((byte) 11, "cli1", "Calle A", "Ciudad A", "111111111", "111111111A", null);
		Clientes cli2 = new Clientes((byte) 22, "cli2", "Calle B", "Ciudad B", "222222222", "222222222B", null);
		Clientes cli3 = new Clientes((byte) 33, "cli3", "Calle C", "Ciudad C", "333333333", "333333333C", null);
		
		Productos pro1 = new Productos(11, "Ansioliticos para caballos", 11, 11, new BigDecimal(2.59), null);
		Productos pro2 = new Productos(22, "Fentanilo 12 Litros monodosis", 22, 22, new BigDecimal(3.59), null);
		Productos pro3 = new Productos(33, "Alprazolam para elefantes adultos", 33, 33, new BigDecimal(4.59), null);
		
		Ventas ven1 = new Ventas((short)11, pro1, cli1, new Date(new java.util.Date().getTime()), (byte)3);
		Ventas ven2 = new Ventas((short)22, pro2, cli2, new Date(new java.util.Date().getTime()), (byte)5);
		Ventas ven3 = new Ventas((short)33, pro3, cli3, new Date(new java.util.Date().getTime()), (byte)9);
		
		try {
			if(constraintCheck(s, ven1)) s.persist(ven1);
			else System.err.printf("La venta %s no cumple la integridad de la BDD", ven1.toString());
			if(constraintCheck(s, ven2)) s.persist(ven2);
			else System.err.printf("La venta %s no cumple la integridad de la BDD", ven2.toString());
			if(constraintCheck(s, ven3)) s.persist(ven3);
			else System.err.printf("La venta %s no cumple la integridad de la BDD", ven3.toString());
			s.persist(cli1);
			s.persist(cli2);
			s.persist(cli3);
			s.persist(pro1);
			s.persist(pro2);
			s.persist(pro3);
			s.getTransaction().commit();
		}catch (ConstraintViolationException e) {
			System.err.println(e);
			s.getTransaction().rollback();
		}catch (PersistenceException e) {
			System.err.println(e);
		}
		
		s.getTransaction().commit();
	}
	
	public static boolean constraintCheck (Session s, Ventas v){
		
		s.getTransaction().begin();
		
		Query<Ventas> qry1 = s.createQuery("from Ventas where idventa = :id", Ventas.class);
		qry1.setParameter("id", v.getIdventa());
		List<Ventas> lv = qry1.list();	
		
		
		Query<Productos> qry2 = s.createQuery("from Productos where descripcion = :desc", Productos.class);
		qry1.setParameter("desc", v.getProductos());
		List<Productos> lp = qry2.list();	

		Query<Clientes> qry3 = s.createQuery("from Clientes where nombre = :nombre", Clientes.class);
		qry1.setParameter("nombre", v.getClientes());
		List<Clientes> lc = qry3.list();	
		
		return lc.size()==1 && lv.size()==0 && lp.size()==1 && v.getCantidad()<lp.get(0).getStockactual();
	}
}
