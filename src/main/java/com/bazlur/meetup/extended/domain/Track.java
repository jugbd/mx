package com.bazlur.meetup.extended.domain;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
public enum Track {
    JAVA_SCRIPT("JavaScript"),
    CLOUD("Cloud"),
    SERVER_SIDE_JAVA("Server side Java"),
    ANDROID("Android Development"),
    WEB("Web Development"),
    CLEAN_CODE("Clean Code"),
    JAVA_9("Java 9"),
    OTHERS("Others");

    private String displayName;

    Track(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getId() {
        return name();
    }


}
