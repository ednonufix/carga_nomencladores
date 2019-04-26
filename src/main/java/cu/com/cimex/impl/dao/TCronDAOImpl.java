package cu.com.cimex.impl.dao;

import cu.com.cimex.interfaces.dao.TICronDAO;
import cu.com.cimex.model.TCron;
import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Created by enoel on 21/05/18.
 */
public class TCronDAOImpl implements TICronDAO {

    private SimpleLoggerFactory factory = new SimpleLoggerFactory();

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer CantidadCrones(Date fecha) throws DataAccessException {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        final Logger logger = factory.getLogger(this.getClass().getName());

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        Integer cant = 0;

        try {

            org.hibernate.query.Query consulta = sess.createQuery("from TCron as c where dayofyear(c.fecha_culminado) = dayofyear(:fecha)")
                    .setParameter("fecha", fecha);

            if (consulta.list().size() > 0) {

                cant = consulta.list().size();

            }

            tx.commit();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.close();

        }

        return cant;

    }

    @Override
    public void CreaCron(TCron cron) throws DataAccessException {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        final Logger logger = factory.getLogger(this.getClass().getName());

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        try {

            sess.save(cron);
            tx.commit();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.close();

        }

    }

    @Override
    public List<TCron> ListCrones() throws DataAccessException {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        final Logger logger = factory.getLogger(this.getClass().getName());

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        List<TCron> crones = new ArrayList<>();

        try {

            crones = sess.createQuery("from TCron").list();

            tx.commit();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.close();

        }

        return crones;
    }

    @Override
    public void EliminaCron() throws Exception {

        Session sess = this.sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();

        final Logger logger = factory.getLogger(this.getClass().getName());

        sess.setHibernateFlushMode(FlushMode.COMMIT);

        List<TCron> crones = new ArrayList<>();

        try {

            crones = sess.createQuery("from TCron").list();

            Iterator<TCron> it_crones = crones.iterator();

            while (it_crones.hasNext()) {

                sess.delete(it_crones.next());

            }

            tx.commit();

        } catch (HibernateException ex) {

            if (tx != null) tx.rollback();

            logger.error(ex.getMessage());

        } finally {

            sess.close();

        }

    }
}
