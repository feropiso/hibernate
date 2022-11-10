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
 * Home object for domain model class Pagamentos.
 * @see com.abctreinamentos.Pagamentos
 * @author Hibernate Tools
 */
public class PagamentosHome {

	private static final Log log = LogFactory.getLog(PagamentosHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		return sessionFactory;
	}
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();  
	}

	public void inserir(Pagamentos transientInstance) {
		log.debug("persisting Pagamentos instance");
		
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
	
	public void deletar(Pagamentos persistentInstance) {
		log.debug("deleting Pagamentos instance");
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			session.delete(persistentInstance);
			session.getTransaction().commit();
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void alterar(Pagamentos detachedInstance) {
		log.debug("mergeting Pagamentos instance");
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			session.merge(detachedInstance);
			session.getTransaction().commit();
			
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public Pagamentos buscar(Integer idPagamento){
		
		log.debug("getting Pagamento instance");
		
		Pagamentos pag = new Pagamentos();
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();

			Query<Pagamentos> instance = sessionFactory.getCurrentSession().
					createQuery("select p from Pagamentos p where p.idPagamento = :idPagamento", Pagamentos.class);
			
			instance.setParameter("idPagamento", idPagamento);
			
			log.debug("get successful, instance found");
			
			pag = instance.getSingleResult();
			
			session.close();
			
		} catch (RuntimeException re) {
			log.error("getting Pagamento failed", re);
			throw re;
		}
		
		return pag;
	}
	
	public List<Pagamentos> buscarTodos(){
		
		log.debug("getting Pagamentos instance");
		
		List<Pagamentos> pagamentos = new ArrayList<>();
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			Query<Pagamentos> instance = sessionFactory.getCurrentSession().
					createQuery("select p from Pagamentos p", Pagamentos.class);
					
			pagamentos = instance.getResultList();
			 
			session.close();
			
		} catch (RuntimeException re) {
			log.error("getting Curso failed", re);
			throw re;
		}
		return pagamentos;
	}
			
	public List<Cursos> buscarPorCursos(){
		
		log.debug("getting Curso instance");
		List<Cursos> cursos = new ArrayList<>();
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			Query<Cursos> instance = sessionFactory.getCurrentSession().
					createQuery("select c from Cursos c inner join "
							+ "Pagamentos p on c.idCurso = p.fk_curso", Cursos.class);
			
			cursos = instance.getResultList();
			 
			session.close();
			
		} catch (RuntimeException re) {
			log.error("getting Curso failed", re);
			throw re;
		}
		
		return cursos;
	}
	
	public List<Pagamentos> buscarPorCliente(Integer id_cliente){
		
		log.debug("getting Pagamento instance");
		
		List<Pagamentos> lista = new ArrayList<>();
		
		try {
			Session session = sessionFactory.getCurrentSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();

			Query<Pagamentos> instance = sessionFactory.getCurrentSession().
					createQuery("select p from Pagamentos p where p.fk_cliente = :fk_cliente", Pagamentos.class);
			
			instance.setParameter("fk_cliente", id_cliente);
			
			log.debug("get successful, instance found");
			
			lista = instance.getResultList();			 
			
			session.close();
			
		} catch (RuntimeException re) {
			log.error("getting Pagamento failed", re);
			throw re;
		}
		
		return lista;
	}
}
