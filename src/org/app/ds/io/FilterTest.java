/**
 * @filenameName:org.app.ds.io.FilterTest.java
 * @description:TODO
 * @author anandm
 * @date Aug 12, 2015 10:11:09 AM
 * @version: TODO
 */
package org.app.ds.io;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @className:org.app.ds.io.FilterTest.java
 * @description:TODO
 * @author anandm
 * @date Aug 12, 2015 10:11:09 AM
 */
public class FilterTest {

    /**
     * @methodName:FilterTest.java.main
     * @description:TODO
     * @author anandm
     * @returntype void
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        String string = "this is some lowercase text";
        InputStream is = new UppercaseInputStream(new ByteArrayInputStream(
                string.getBytes()));

        OutputStream tee = new TeeOutputStream(System.out,
                new FileOutputStream("out.txt"));

        int b = -1;
        while ((b = is.read()) != -1) {
            tee.write(b);
        }
        is.close();
        tee.close();
    }
}
