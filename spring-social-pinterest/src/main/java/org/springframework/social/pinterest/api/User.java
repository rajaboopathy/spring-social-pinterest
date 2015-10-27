package org.springframework.social.pinterest.api;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by Rajaboopathy Vijay on 10/25/15.
 */
public class User extends PinInterestObject implements Serializable {

    String id;
    String userName;
    String firstName;
    String bio;
    String lastName;
    String gender;
    String url;

    Locale locale;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public User() {
    }

    public User(String id, String firstName, String lastName, String url) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.url = url;
    }
}
