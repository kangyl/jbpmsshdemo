package com.kyl.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kyl.exception.DaoException;

public abstract class HibernateSupport<T> extends HibernateDaoSupport
{
  public List<T> findEntities(String hql)
    throws DaoException
  {
    try
    {
      return findEntities(hql, new Object[0]);
    } catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public List<T> findEntitiesByClass(Class<T> clazz)
    throws DaoException
  {
    String hql = "from " + clazz.getSimpleName();
    try {
      return findEntities(hql);
    } catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public List<T> findEntities(final String hql, final Object[] params)
    throws DaoException
  {
    try
    {
      return (List)super.getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          Query query = session.createQuery(hql);
          HibernateSupport.this.fillNameParameter(query, params);
          return query.list();
        }
      });
    }
    catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public T findFirstEntity(final String hql, final Object[] params)
    throws DaoException
  {
    try
    {
      return (T) super.getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          Query query = session.createQuery(hql);
          HibernateSupport.this.fillNameParameter(query, params);
          return query.uniqueResult();
        }
      });
    }
    catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public Object findFirstObject(final String hql, final Object[] params) throws DaoException
  {
    try {
      return super.getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          Query query = session.createQuery(hql);
          HibernateSupport.this.fillNameParameter(query, params);
          return query.uniqueResult();
        } } );
    }
    catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public T findEntityById(Class<T> entityClass, Serializable id)
    throws DaoException
  {
    try
    {
      return getHibernateTemplate().get(entityClass, id);
    } catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public Serializable saveEntity(T entity)
    throws DaoException
  {
    try
    {
      return super.getHibernateTemplate().save(entity);
    } catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public void saveOrUpdateEntity(T entity)
    throws DaoException
  {
    try
    {
      super.getHibernateTemplate().saveOrUpdate(entity);
    } catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public void updateEntity(T entity)
    throws DaoException
  {
    try
    {
      super.getHibernateTemplate().update(entity);
    } catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public void deleteEntity(T entity)
    throws DaoException
  {
    try
    {
      super.getHibernateTemplate().delete(entity);
    } catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public void deleteEntities(Collection<T> collection)
    throws DaoException
  {
    try
    {
      super.getHibernateTemplate().deleteAll(collection);
    } catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public void executeUpdate(final String hql)
    throws DaoException
  {
    try
    {
      super.getHibernateTemplate().execute(new HibernateCallback()
      {
        public Object doInHibernate(Session session) throws HibernateException, SQLException
        {
          Query query = session.createQuery(hql);
          return Integer.valueOf(query.executeUpdate());
        }
      });
    }
    catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public void executeUpdate(final String hql, final Object[] params)
    throws DaoException
  {
    try
    {
      super.getHibernateTemplate().execute(new HibernateCallback()
      {
        public Object doInHibernate(Session session) throws HibernateException, SQLException
        {
          Query query = session.createQuery(hql);
          HibernateSupport.this.fillNameParameter(query, params);
          return Integer.valueOf(query.executeUpdate());
        }
      });
    }
    catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public void executeUpdateBySql(final String sql, final Object[] params)
    throws DaoException
  {
    try
    {
      super.getHibernateTemplate().execute(new HibernateCallback()
      {
        public Object doInHibernate(Session session) throws HibernateException, SQLException
        {
          SQLQuery query = session.createSQLQuery(sql);
          HibernateSupport.this.fillNameParameter(query, params);
          return Integer.valueOf(query.executeUpdate());
        }
      });
    }
    catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public List<?> executeQueryBySql(final String sql, final Object[] params)
    throws DaoException
  {
    try
    {
      return (List)super.getHibernateTemplate().execute(new HibernateCallback()
      {
        public Object doInHibernate(Session session) throws HibernateException, SQLException
        {
          SQLQuery query = session.createSQLQuery(sql);
          HibernateSupport.this.fillNameParameter(query, params);
          return query.list();
        }
      });
    }
    catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public List<?> executeQuery(final String hql, final Object[] params)
    throws DaoException
  {
    try
    {
      return (List)super.getHibernateTemplate().execute(new HibernateCallback()
      {
        public Object doInHibernate(Session session)
          throws HibernateException, SQLException
        {
          Query query = session.createQuery(hql);
          HibernateSupport.this.fillNameParameter(query, params);
          return query.list();
        } } );
    }
    catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public int executeCountQuery(final String hql, final Object[] params)
    throws DaoException
  {
    try
    {
      Long count = (Long)super.getHibernateTemplate().execute(new HibernateCallback()
      {
        public Object doInHibernate(Session session) throws HibernateException, SQLException
        {
          Query query = session.createQuery(hql);
          HibernateSupport.this.fillNameParameter(query, params);
          Object size = query.list().get(0);
          if ((size instanceof Integer)) {
            return Long.valueOf(size.toString());
          }
          return size;
        }
      });
      count = Long.valueOf(count == null ? 0L : count.longValue());
      return count.intValue();
    } catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public List<?> findListForPage(final String hql, final int offset, final int length, final Object[] params)
    throws DaoException
  {
    try
    {
      return getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          Query query = session.createQuery(hql);
          HibernateSupport.this.fillNameParameter(query, params);
          query.setFirstResult(offset);
          query.setMaxResults(length);
          return query.list();
        }
      });
    }
    catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  public List<?> findListForPage(final Class<?> object, final Criterion[] criterions, final int offset, final int length)
    throws DaoException
  {
    try
    {
      return getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException, SQLException {
          Criteria criteria = session.createCriteria(object);

          for (Criterion c : criterions) {
            criteria.add(c);
          }
          criteria.setFirstResult(offset);
          criteria.setMaxResults(length);
          return criteria.list();
        }
      });
    }
    catch (Throwable e) {
      throw new DaoException(e);
    }
  }

  private void fillNameParameter(Query query, Object[] params)
    throws SQLException
  {
    String[] nameParams = query.getNamedParameters();
    if ((nameParams != null) && (nameParams.length > 0)) {
      if ((params != null) && (params.length > 0)) {
        if (nameParams.length != params.length) {
          throw new SQLException("参数个数不对�?");
        }
        int i = 0;
        for (Object o : params) {
          if ((o instanceof Collection))
            query.setParameterList(nameParams[i], (Collection)o);
          else if ((o instanceof String[]))
            query.setParameterList(nameParams[i], (String[])o);
          else {
            query.setParameter(i++, o);
          }
        }
      }
    }
    else if ((params != null) && (params.length > 0)) {
      int i = 0;
      for (Object o : params)
        query.setParameter(i++, o);
    }
  }
}