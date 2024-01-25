package main;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class Exercise5 {

	public static void main(String[] args) {

		
		if(args.length != 1) {
			System.err.println("Arugmentos invalidos");
			System.exit(0);
		}

		Session s = Instance.getSessionFactory().openSession();
		s.getTransaction().begin();
		
		System.out.println("Compobando cliente...");
		Query<Clientes> qry1 = s.createQuery("from Clientes where nombre = :nombre", Clientes.class);
		qry1.setParameter("nombre", args[0]);
		List<Clientes> clientes = qry1.list();
		
		System.out.println("Ventas del cliente" + args[0]);
		Query<Ventas> qry2 = s.createQuery("from Ventas where clientes = :cliente", Ventas.class);
		qry2.setParameter("cliente", clientes.get(0));
		List<Ventas> ventas = qry2.list();
		
		BigDecimal total = new BigDecimal(0);
		BigDecimal aux = new BigDecimal(0);
		for (Ventas e : ventas) {
			System.out.printf("Venta: %d ** %s%n", e.getIdventa(), e.getFechaventa());
			System.out.printf("Producto: %s%n", e.getProductos().getDescripcion());
			System.out.printf("Cantidad: %s ** PVP: %s%n", e.getProductos(), e.getProductos().getPvp().toString());
			aux = e.getProductos().getPvp().multiply(new BigDecimal(e.getCantidad()));
			total = total.add(aux);
			System.out.printf("Importe: %s EUR%n", aux.toString());
		}
		System.out.printf("TOTAL VENTAS: ", ventas.size());
		System.out.printf("TOTAL IMPORTE: ", total.toString());
		

		s.getTransaction().commit();
	}

}
