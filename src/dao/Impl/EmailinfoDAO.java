package dao.Impl;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.Emailinfo;
import dao.IEmailinfoDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Emailinfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Emailinfo
 * @author MyEclipse Persistence Tools
 */

public class EmailinfoDAO extends HibernateDaoSupport implements IEmailinfoDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EmailinfoDAO.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#save(dao.Emailinfo)
	 */
	public void save(Emailinfo transientInstance) {
		log.debug("saving Emailinfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#delete(dao.Emailinfo)
	 */
	public void delete(Emailinfo persistentInstance) {
		log.debug("deleting Emailinfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#findById(java.lang.Integer)
	 */
	public Emailinfo findById(java.lang.Integer id) {
		log.debug("getting Emailinfo instance with id: " + id);
		try {
			Emailinfo instance = (Emailinfo) getHibernateTemplate().get(
					"dao.Emailinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#findByExample(dao.Emailinfo)
	 */
	public List findByExample(Emailinfo instance) {
		log.debug("finding Emailinfo instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Emailinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Emailinfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#findByUid(java.lang.Object)
	 */
	public List findByUid(Object uid) {
		return findByProperty(UID, uid);
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#findBySavePwd(java.lang.Object)
	 */
	public List findBySavePwd(Object savePwd) {
		return findByProperty(SAVE_PWD, savePwd);
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#findBySmtp(java.lang.Object)
	 */
	public List findBySmtp(Object smtp) {
		return findByProperty(SMTP, smtp);
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#findByPop3(java.lang.Object)
	 */
	public List findByPop3(Object pop3) {
		return findByProperty(POP3, pop3);
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#findByEuser(java.lang.Object)
	 */
	public List findByEuser(Object euser) {
		return findByProperty(EUSER, euser);
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#findByEpwd(java.lang.Object)
	 */
	public List findByEpwd(Object epwd) {
		return findByProperty(EPWD, epwd);
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#findAll()
	 */
	public List findAll() {
		log.debug("finding all Emailinfo instances");
		try {
			String queryString = "from Emailinfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#merge(dao.Emailinfo)
	 */
	public Emailinfo merge(Emailinfo detachedInstance) {
		log.debug("merging Emailinfo instance");
		try {
			Emailinfo result = (Emailinfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#attachDirty(dao.Emailinfo)
	 */
	public void attachDirty(Emailinfo instance) {
		log.debug("attaching dirty Emailinfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dao.Impl.IEmailinfoDAO#attachClean(dao.Emailinfo)
	 */
	public void attachClean(Emailinfo instance) {
		log.debug("attaching clean Emailinfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IEmailinfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IEmailinfoDAO) ctx.getBean("EmailinfoDAO");
	}
}