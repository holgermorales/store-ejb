/**
 * 
 */
package com.todo1.store.kardex.dao.impl;

import javax.ejb.Stateless;

import com.todo1.store.generic.dao.GenericJpaDaoImpl;
import com.todo1.store.kardex.dao.DetalleCompraDao;
import com.todo1.store.modelo.kardex.DetalleCompra;

/**
 * @author holger.morales
 */
@Stateless
public class DetalleCompraDaoImpl extends GenericJpaDaoImpl<DetalleCompra, Long> implements DetalleCompraDao {

}
