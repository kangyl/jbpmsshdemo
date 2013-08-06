package com.kyl.dao;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.kyl.exception.DaoException;

public abstract interface IBaseDao<T> extends Serializable
{
  public abstract List<T> findChsEntities(Class<T> paramClass)
    throws DaoException;

  public abstract T findChsEntityById(Class<T> paramClass, Serializable paramSerializable)
    throws DaoException;

  public abstract Object findChsObjectById(Class paramClass, Serializable paramSerializable)
    throws DaoException;

  public abstract Serializable saveChsEntity(Object paramObject)
    throws DaoException;

  public abstract void saveOrUpdateChsEntity(Object paramObject)
    throws DaoException;

  public abstract void updateChsEntity(Object paramObject)
    throws DaoException;

  public abstract void deleteChsEntity(Object paramObject)
    throws DaoException;

  public abstract void deleteChsEntities(Collection paramCollection)
    throws DaoException;

  public abstract T findFirstChsEntity(String paramString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract Object findFirstChsObject(String paramString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract List findChsEntities(String paramString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract List findChsListForPage(String paramString, int paramInt1, int paramInt2, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract int executeChsCountQuery(String paramString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract int executeCustomChsCountQuery(String paramString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void executeChsUpdate(String paramString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void executeChsUpdateBySql(String paramString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract List executeChsQueryBySql(String paramString, Object[] paramArrayOfObject)
    throws DaoException;
}
