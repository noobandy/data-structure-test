/**
 * @filenameName:org.app.ds.io.StudentManagerApp.java
 * @description:TODO
 * @author anandm
 * @date Aug 11, 2015 4:08:12 PM
 * @version: TODO
 */
package org.app.ds.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.app.ds.applications.menumanager.Menu;
import org.app.ds.applications.menumanager.MenuCommand;
import org.app.ds.applications.menumanager.MenuManager;

/**
 * @className:org.app.ds.io.StudentManagerApp.java
 * @description:TODO
 * @author anandm
 * @date Aug 11, 2015 4:08:12 PM
 */
public class StudentManagerApp {

    public static void main(String[] args) throws FileNotFoundException,
            IOException, ClassNotFoundException {
        final StudentManager studentManager = new StudentManager();
        studentManager.init();
        final Scanner scanner = new Scanner(System.in);
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        MenuManager menuManager = new MenuManager(new MenuCommand() {

            @Override
            public boolean execute() {
                try {
                    studentManager.stop();
                    System.exit(0);
                    return true;
                }
                catch (IOException e) {

                    e.printStackTrace();
                }
                return false;
            }
        });
        menuManager.addMenu(new Menu(1, "Add Student", new MenuCommand() {

            @Override
            public boolean execute() {

                try {
                    System.out.println("Enter student name");
                    String name = scanner.nextLine();
                    System.out.println("Enter sex M/F");
                    String sex = scanner.nextLine();
                    System.out.println("Enter dob dd/mm/yyyy");
                    String dob = scanner.nextLine();
                    Sex sexEnum = "M".equalsIgnoreCase(sex) ? Sex.Male
                            : Sex.Female;
                    Date dobDate = dateFormat.parse(dob);

                    System.out.println("Assigned student id : "
                            + studentManager.addStudent(name, sexEnum, dobDate));
                    return true;
                }
                catch (ParseException e) {

                    e.printStackTrace();
                }
                return false;
            }
        }));

        menuManager.addMenu(new Menu(2, "Find student by id ",
                new MenuCommand() {

                    @Override
                    public boolean execute() {
                        System.out.println("Enter student id");
                        int id = scanner.nextInt();
                        Student student = studentManager.findStudentById(id);
                        if (student != null) {
                            System.out.println(student);
                        }
                        else {
                            System.out.println("No match found.");
                        }
                        return true;
                    }
                }));

        menuManager.start();

    }
}
