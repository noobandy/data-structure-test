/**
 * @filenameName:org.app.ds.io.UppercaseInputStream.java
 * @description:TODO
 * @author anandm
 * @date Aug 12, 2015 10:09:09 AM
 * @version: TODO
 */
package org.app.ds.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @className:org.app.ds.io.UppercaseInputStream.java
 * @description:TODO
 * @author anandm
 * @date Aug 12, 2015 10:09:09 AM
 */
public class UppercaseInputStream extends FilterInputStream {

    /**
     * @param in
     */
    protected UppercaseInputStream(InputStream in) {
        super(in);

    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c == -1) {
            return -1;
        }
        else {
            return Character.toUpperCase(c);
        }
    }

}
