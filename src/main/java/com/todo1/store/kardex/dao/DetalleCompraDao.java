/**
 * 
 */
package com.todo1.store.kardex.dao;

import javax.ejb.Local;

import com.todo1.store.generic.dao.GenericJpaDao;
import com.todo1.store.modelo.kardex.DetalleCompra;

/**
 * @author holger.morales
 */
@Local
public interface DetalleCompraDao extends GenericJpaDao<DetalleCompra, Long> {

}
