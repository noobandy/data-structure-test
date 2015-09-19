/**
 * @filenameName:org.app.ds.io.StudentManager.java
 * @description:TODO
 * @author anandm
 * @date Aug 11, 2015 3:56:33 PM
 * @version: TODO
 */
package org.app.ds.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className:org.app.ds.io.StudentManager.java
 * @description:TODO
 * @author anandm
 * @date Aug 11, 2015 3:56:33 PM
 */
public class StudentManager {

    private Map<Integer, Student> map;

    private AtomicInteger lastId;

    /**
     * 
     */
    public StudentManager() {
        super();

    }

    public int addStudent(String name, Sex sex, Date dob) {
        Student student = new Student(lastId.intValue(), name, sex, dob);
        map.put(lastId.intValue(), student);
        return lastId.getAndIncrement();
    }

    public Student findStudentById(int id) {
        return map.get(id);
    }

    public void init() throws FileNotFoundException, IOException,
            ClassNotFoundException {
        File studentData = new File("students.data");
        if (studentData.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new FileInputStream(studentData));
            map = (Map<Integer, Student>) objectInputStream.readObject();
            lastId = (AtomicInteger) objectInputStream.readObject();
            objectInputStream.close();
        }
        else {
            map = new HashMap<Integer, Student>();
            lastId = new AtomicInteger(1);
        }
    }

    public void stop() throws IOException {
        File studentData = new File("students.data");
        if (studentData.exists()) {
            studentData.delete();
        }

        studentData.createNewFile();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(studentData));
        objectOutputStream.writeObject(map);
        objectOutputStream.writeObject(lastId);
        objectOutputStream.close();
    }
}
