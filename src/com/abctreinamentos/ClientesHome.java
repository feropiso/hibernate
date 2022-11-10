package com.abctreinamentos;
// Generated 1 de nov. de 2022 07:17:19 by Hibernate Tools 4.3.6.Final

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Home object for domain model class Clientes.
 * @see com.abctreinamentos.Clientes
 * @author Hibernate Tools
 */

public class ClientesHome {
	
	/**
	 **Persistente (persistent) -> São objetos que estão associados aos registros de um banco de dados;
	 *Transiente (transient) -> São objetos que não estão associados aos registros de um banco de dados;
	 *Desanexado (detached) -> São objetos que existem no bando de dados, 
	 *porém não existem na sessão hibernate.
	 */

	private static final Log log = LogFactory.getLog(ClientesHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		
		SessionFactory sessionFactory = new Configuration().
			configure().buildSessionFactory();
		return sessionFactory;
	}
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();  
	}
	
	public void inserir(Clientes transientInstance) {
		log.debug("persisting Clientes instance");
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			session.persist(transientInstance);
			session.getTransaction().commit();
			
			session.close();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public void deletar(Clientes persistentInstance) {
		log.debug("deleting Clientes instance");
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			session.delete(persistentInstance);
			session.getTransaction().commit();
			
			session.close();
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void alterar(Clientes detachedInstance) {
		log.debug("mergeting Clientes instance");
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			session.merge(detachedInstance);
			session.getTransaction().commit();
			
			session.close();
			
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public Clientes find(int idCliente) {

		System.out.println("finding Cliente instances");
		
		try {
			
			Clientes cliente = getSession().get(Clientes.class, idCliente);
	
			return cliente;
		}
		catch (RuntimeException re) {
			System.err.println("find all failed "+ re);
			throw re;
		}
	}
	
	public Clientes buscar(String cpf){
		
		log.debug("getting Cliente instance");
		Clientes cliente = new Clientes();
		
		try {
			Session session = sessionFactory.openSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			Query<Clientes> instance = sessionFactory.openSession().
					createQuery("select c from Clientes c where c.cpf = :cpf", Clientes.class);
			
			instance.setParameter("cpf", cpf);
			
			log.debug("get successful, instance found");
			
			cliente = instance.getSingleResult();
			 
			session.close();
			
		} catch (RuntimeException re) {
			log.error("getting Cliente failed", re);
			throw re;
		}
		
		return cliente;
	}
	
	public List<Clientes> buscarTodos(){
		
		List<Clientes> cliente = new ArrayList<>();
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			Query<Clientes> instance = sessionFactory.getCurrentSession().
					createQuery("select c from Clientes c", Clientes.class);
			
			cliente = instance.getResultList();
			
			session.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		return cliente;
	}
	
}
