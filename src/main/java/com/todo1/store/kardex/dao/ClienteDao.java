/**
 * 
 */
package com.todo1.store.kardex.dao;

import javax.ejb.Local;

import com.todo1.store.generic.dao.GenericJpaDao;
import com.todo1.store.modelo.kardex.Cliente;

/**
 * @author holger.morales
 */
@Local
public interface ClienteDao extends GenericJpaDao<Cliente, Long> {

}
