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
 * Home object for domain model class Cursos.
 * @see com.abctreinamentos.Cursos
 * @author Hibernate Tools
 */
public class CursosHome {

	private static final Log log = LogFactory.getLog(CursosHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		return sessionFactory;
	}
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();  
	}
	
	public void inserir(Cursos transientInstance) {
		log.debug("persisting Cursos instance");
		
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
	
	public void deletar(Cursos persistentInstance) {
		log.debug("deleting Cursos instance");
		
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
	
	public void alterar(Cursos detachedInstance) {
		log.debug("mergeting Cursos instance");
		
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
	
	public Cursos buscar(String nome){
		
		log.debug("getting Curso instance");
		
		Cursos curso = new Cursos();
		
		try {
			Session session = sessionFactory.openSession();

			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			Query<Cursos> instance = sessionFactory.getCurrentSession().
					createQuery("select c from Cursos c where c.nome = :nome", Cursos.class);
			
			instance.setParameter("nome", nome);			
			
			curso = instance.getSingleResult();
			 
			session.close();
			
		} catch (RuntimeException re) {
			log.error("getting Curso failed", re);
			throw re;
		}
		return curso;
	}
	
	public List<Cursos> buscarTodos(){
		
		log.debug("getting Curso instance");
		
		List<Cursos> cursos = new ArrayList<>();
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			Query<Cursos> instance = sessionFactory.getCurrentSession().
					createQuery("select c from Cursos c", Cursos.class);
			
			cursos = instance.getResultList();			 
			
			session.close();
			
		} catch (RuntimeException re) {
			log.error("getting Curso failed", re);
			throw re;
		}
		
		return cursos;
		
	}
	
	public Cursos buscarPorId(Integer id){
		
		log.debug("getting Curso instance");
		
		Cursos curso = new Cursos();
		
		try {
			
			Session session = sessionFactory.getCurrentSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			Query<Cursos> instance = sessionFactory.getCurrentSession().
					createQuery("select c from Cursos c where c.idCurso = :idCurso", Cursos.class);
			
			instance.setParameter("idCurso", id);
			
			curso = instance.getSingleResult();
			
			session.close();			 
			
		} catch (RuntimeException re) {
			log.error("getting Curso failed", re);
			throw re;
		}
		
		return curso;
		
	}

}
