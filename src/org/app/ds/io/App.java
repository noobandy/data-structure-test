/**
 * @filenameName:org.app.ds.io.App.java
 * @description:TODO
 * @author anandm
 * @date Aug 11, 2015 12:16:15 PM
 * @version: TODO
 */
package org.app.ds.io;

import java.io.Console;
import java.io.IOException;

/**
 * @className:org.app.ds.io.App.java
 * @description:TODO
 * @author anandm
 * @date Aug 11, 2015 12:16:15 PM
 */
public class App {

    /**
     * @methodName:App.java.main
     * @description:TODO
     * @author anandm
     * @returntype void
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Console console = System.console();
        if (console != null) {
            String message = console.readLine("Please enter a message");
            console.writer().write("You entered : " + message);
        }
    }
}
