/**
 * @filenameName:org.app.ds.others.classifier.User.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 12:35:31 PM
 * @version: TODO
 */
package org.app.ds.others.movieclassifier;


/**
 * @className:org.app.ds.others.classifier.User.java
 * @description:TODO
 * @author anandm
 * @date Jun 16, 2015 12:35:31 PM
 */
public class User {

    private String id;

    private String[] likedItemIds;

    private String[] dislikedItemIds;

    /**
     * 
     */
    public User() {
        super();

    }

    /**
     * @param id
     * @param likedItemIds
     * @param dislikedItemIds
     */
    public User(String id, String[] likedItemIds, String[] dislikedItemIds) {
        super();
        this.id = id;
        this.likedItemIds = likedItemIds;
        this.dislikedItemIds = dislikedItemIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getLikedItemIds() {
        return likedItemIds;
    }

    public void setLikedItemIds(String[] likedItemIds) {
        this.likedItemIds = likedItemIds;
    }

    public String[] getDislikedItemIds() {
        return dislikedItemIds;
    }

    public void setDislikedItemIds(String[] dislikedItemIds) {
        this.dislikedItemIds = dislikedItemIds;
    }

}
