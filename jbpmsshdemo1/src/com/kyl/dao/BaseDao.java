package com.kyl.dao;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.kyl.exception.DaoException;

public class BaseDao extends HibernateSupport
  implements IBaseDao
{
  public List findChsEntities(Class entity)
    throws DaoException
  {
    return super.findEntities("from " + entity.getName());
  }

  public Object findChsEntityById(Class entity, Serializable id) throws DaoException
  {
    return super.findEntityById(entity, id);
  }

  public void deleteChsEntity(Object entity) throws DaoException {
    super.deleteEntity(entity);
  }

  public void deleteChsEntities(Collection entities) throws DaoException {
    super.deleteEntities(entities);
  }

  public Serializable saveChsEntity(Object entity) throws DaoException {
    return super.saveEntity(entity);
  }

  public void saveOrUpdateChsEntity(Object entity) throws DaoException {
    super.saveOrUpdateEntity(entity);
  }

  public void updateChsEntity(Object entity) throws DaoException {
    super.updateEntity(entity);
  }

  public List findChsEntities(String hql, Object[] params) throws DaoException
  {
    return super.findEntities(hql, params);
  }

  public List<?> findChsListForPage(String hql, int offset, int length, Object[] params) throws DaoException
  {
    return super.findListForPage(hql, offset, length, params);
  }

  public int executeChsCountQuery(String hql, Object[] params) throws DaoException
  {
    hql = "select count(*) " + hql;
    return super.executeCountQuery(hql, params);
  }

  public int executeCustomChsCountQuery(String hql, Object[] params) throws DaoException
  {
    return super.executeCountQuery(hql, params);
  }

  public List executeChsQueryBySql(String sql, Object[] params) throws DaoException
  {
    return super.executeQueryBySql(sql, params);
  }

  public void executeChsUpdate(String hql, Object[] params) throws DaoException
  {
    super.executeUpdate(hql, params);
  }

  public Object findFirstChsEntity(String hql, Object[] params) throws DaoException
  {
    return super.findFirstEntity(hql, params);
  }

  public void executeChsUpdateBySql(String sql, Object[] params) throws DaoException
  {
    super.executeUpdateBySql(sql, params);
  }

  public Object findChsObjectById(Class entity, Serializable id) throws DaoException
  {
    return super.findEntityById(entity, id);
  }

  public Object findFirstChsObject(String hql, Object[] params) throws DaoException
  {
    return super.findFirstObject(hql, params);
  }
}