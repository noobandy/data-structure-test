/**
 * @filenameName:org.app.ds.io.TeeOutputStream.java
 * @description:TODO
 * @author anandm
 * @date Aug 12, 2015 10:44:16 AM
 * @version: TODO
 */
package org.app.ds.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @className:org.app.ds.io.TeeOutputStream.java
 * @description:TODO
 * @author anandm
 * @date Aug 12, 2015 10:44:16 AM
 */
public class TeeOutputStream extends FilterOutputStream {

    private OutputStream teeOut;

    /**
     * @param out
     * @param teeOut
     */
    public TeeOutputStream(OutputStream out, OutputStream teeOut) {
        super(out);
        this.teeOut = teeOut;
    }

    @Override
    public void write(int b) throws IOException {

        super.write(b);

        teeOut.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {

        super.write(b);
        teeOut.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {

        super.write(b, off, len);
        teeOut.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {

        super.flush();
        teeOut.flush();
    }

    @Override
    public void close() throws IOException {

        super.close();
        teeOut.close();
    }

}
