/**
 * 
 */
package org.app.ds.datamapper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author anandm
 * 
 */
public class CrudImpl implements ICrud {

	private DataSource dataSource;

	private static final String camelCaseToUnderScore(String camelCase) {
		return camelCase.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
	}

	private static final String classToTableName(Class<?> clazz) {
		return camelCaseToUnderScore(clazz.getSimpleName());
	}

	private static final String fieldToColumnName(Field field) {
		return camelCaseToUnderScore(field.getName());
	}

	private static final String sqlInsertStatement(Class<?> clazz) {
		StringBuilder builder = new StringBuilder("insert into ")
				.append(classToTableName(clazz)).append(' ').append('(');

		Field[] fields = clazz.getDeclaredFields();
		boolean first = true;

		for (Field field : fields) {
			if (!(Modifier.isStatic(field.getModifiers()) || Modifier
					.isTransient(field.getModifiers()))) {
				if (first) {
					first = false;
				} else {
					builder.append(',');
				}
				builder.append('"').append(fieldToColumnName(field))
						.append('"');
			}
		}

		builder.append(')');
		builder.append(' ');
		builder.append("values ").append('(');

		first = true;
		for (Field field : fields) {
			if (!(Modifier.isStatic(field.getModifiers()) || Modifier
					.isTransient(field.getModifiers()))) {
				if (first) {
					first = false;
				} else {
					builder.append(',');
				}
				builder.append('?');
			}
		}
		builder.append(')');
		return builder.toString();
	}

	@Override
	public <T> T save(Class<T> clazz, T obj) throws IllegalArgumentException,
			SQLException, IllegalAccessException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection
				.prepareStatement(sqlInsertStatement(clazz));
		int i = 0;
		for (Field field : clazz.getDeclaredFields()) {
			if (!(Modifier.isStatic(field.getModifiers()) || Modifier
					.isTransient(field.getModifiers()))) {
				Class<?> fieldType = field.getType();

				if (fieldType.isPrimitive()) {
					if (fieldType.equals(Character.TYPE)) {
						char[] chars = new char[] { field.getChar(obj) };
						statement.setString(i++, new String(chars));
					}

					if (fieldType.equals(Boolean.TYPE)) {
						statement.setBoolean(i++, field.getBoolean(obj));
					}

					if (fieldType.equals(Byte.TYPE)) {
						statement.setByte(i++, field.getByte(obj));
					}
					if (fieldType.equals(Short.TYPE)) {
						statement.setShort(i++, field.getShort(obj));
					}
					if (fieldType.equals(Integer.TYPE)) {
						statement.setInt(i++, field.getInt(obj));
					}
					if (fieldType.equals(Long.TYPE)) {
						statement.setLong(i++, field.getLong(obj));
					}

					if (fieldType.equals(Float.TYPE)) {
						statement.setFloat(i++, field.getFloat(obj));
					}

					if (fieldType.equals(Double.TYPE)) {
						statement.setDouble(i++, field.getDouble(obj));
					}
				} else {
					if (fieldType.equals(String.class)) {
						statement.setString(i++, (String) field.get(obj));
					}
				}
			}
		}
		statement.execute();
		connection.close();
		return obj;
	}

	@Override
	public <T, Id extends Serializable> T get(Class<T> clazz, Id id) {

		return null;
	}

	@Override
	public <T, Id extends Serializable> boolean delete(Class<T> clazz, Id id) {

		return false;
	}

	public static void main(String[] args) {
		System.out.println(CrudImpl.classToTableName(Person.class));

		System.out.println(CrudImpl.sqlInsertStatement(Person.class));
	}

}

class Person {

	private static final String STR = "str";

	private String id;
	private String firstName;
	private String lastName;
	private String emailId;
	private transient String fullName;

	/**
	 * 
	 */
	public Person() {
		super();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailId=" + emailId + "]";
	}

}
