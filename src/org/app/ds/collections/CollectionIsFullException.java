/**
 * @filenameName:org.app.ds.collections.CollectionIsFullException.java
 * @description:TODO
 * @author anandm
 * @date Aug 10, 2015 12:04:50 PM
 * @version: TODO
 */
package org.app.ds.collections;

/**
 * @className:org.app.ds.collections.CollectionIsFullException.java
 * @description:TODO
 * @author anandm
 * @date Aug 10, 2015 12:04:50 PM
 */
public class CollectionIsFullException extends RuntimeException {

    /**
     * 
     */
    public static final long serialVersionUID = 1L;

    /**
     * 
     */
    public CollectionIsFullException() {
        super();

    }

    /**
     * @param message
     * @param cause
     */
    public CollectionIsFullException(String message, Throwable cause) {
        super(message, cause);

    }

    /**
     * @param message
     */
    public CollectionIsFullException(String message) {
        super(message);

    }

    /**
     * @param cause
     */
    public CollectionIsFullException(Throwable cause) {
        super(cause);

    }

}
