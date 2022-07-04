/**
 * 
 */
package com.todo1.store.kardex.dao.impl;

import javax.ejb.Stateless;

import com.todo1.store.generic.dao.GenericJpaDaoImpl;
import com.todo1.store.kardex.dao.CompraDao;
import com.todo1.store.modelo.kardex.Compra;

/**
 * @author holger.morales
 */
@Stateless
public class CompraDaoImpl extends GenericJpaDaoImpl<Compra, Long> implements CompraDao {

}
