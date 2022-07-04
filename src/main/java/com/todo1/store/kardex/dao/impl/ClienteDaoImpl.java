/**
 * 
 */
package com.todo1.store.kardex.dao.impl;

import javax.ejb.Stateless;

import com.todo1.store.generic.dao.GenericJpaDaoImpl;
import com.todo1.store.kardex.dao.ClienteDao;
import com.todo1.store.modelo.kardex.Cliente;

/**
 * @author developer
 */
@Stateless
public class ClienteDaoImpl extends GenericJpaDaoImpl<Cliente, Long> implements ClienteDao {

}
