/**
 * 
 */
package com.todo1.store.kardex.dao.impl;

import javax.ejb.Stateless;

import com.todo1.store.generic.dao.GenericJpaDaoImpl;
import com.todo1.store.kardex.dao.MovimientoDao;
import com.todo1.store.modelo.kardex.Movimiento;

/**
 * @author holger.morales
 */
@Stateless
public class MovimientoDaoImpl extends GenericJpaDaoImpl<Movimiento, Long> implements MovimientoDao {

}
