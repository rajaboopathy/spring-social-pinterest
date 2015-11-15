package org.springframework.social.pinterest.api;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by Rajaboopathy Vijay on 10/27/15.
 */
public class Pin extends PinInterestObject implements Serializable {

    private String url;

    private String link;

    private String id;

    private User creator;

    private Board board;

    private Date date;

    private String note;

    private String color;

    private Map<String, Integer> counts;

    private Map<String, String> media;

    private Map<String, String> attribution;

    private Map<String, Image> image;

    private Map<String, Object> metadata;

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getNote() {
        return note;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getAttribution() {
        return attribution;
    }

    public void setAttribution(Map<String, String> attribution) {
        this.attribution = attribution;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Map<String, Integer> getCounts() {
        return counts;
    }

    public void setCounts(Map<String, Integer> counts) {
        this.counts = counts;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Image> getImage() {
        return image;
    }

    public void setImage(Map<String, Image> image) {
        this.image = image;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Map<String, String> getMedia() {
        return media;
    }

    public void setMedia(Map<String, String> media) {
        this.media = media;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
