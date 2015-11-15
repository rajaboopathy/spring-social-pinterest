package org.springframework.social.pinterest.api;

import java.util.Map;

/**
 * Created by Rajaboopathy Vijay on 11/15/15.
 */
public class Image {
 Map<String,ImageInfo> imageInfoMap;

    public Map<String, ImageInfo> getImageInfoMap() {
        return imageInfoMap;
    }

    public void setImageInfoMap(Map<String, ImageInfo> imageInfoMap) {
        this.imageInfoMap = imageInfoMap;
    }
}

class ImageInfo {
    String url;
    String width;
    String height;

    public ImageInfo(String height, String url, String width) {
        this.height = height;
        this.url = url;
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}

