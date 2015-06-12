package org.app.ds.datamapper;

import java.io.Serializable;
import java.sql.SQLException;

public interface ICrud {

	<T> T save(Class<T> clazz, T obj) throws IllegalArgumentException, SQLException, IllegalAccessException;

	<T, Id extends Serializable> T get(Class<T> clazz, Id id);

	<T, Id extends Serializable> boolean delete(Class<T> clazz, Id id);

}
