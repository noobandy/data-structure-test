/**
 * @filenameName:org.app.ds.io.Student.java
 * @description:TODO
 * @author anandm
 * @date Aug 11, 2015 3:57:04 PM
 * @version: TODO
 */
package org.app.ds.io;

import java.io.Serializable;
import java.util.Date;

/**
 * @className:org.app.ds.io.Student.java
 * @description:TODO
 * @author anandm
 * @date Aug 11, 2015 3:57:04 PM
 */
public class Student implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private Sex sex;

    private Date dob;

    /**
     * @param id
     * @param name
     * @param sex
     * @param dob
     */
    public Student(int id, String name, Sex sex, Date dob) {
        super();
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", sex=" + sex
                + ", dob=" + dob + "]";
    }

}
