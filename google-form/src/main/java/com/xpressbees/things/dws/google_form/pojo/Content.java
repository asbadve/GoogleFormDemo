package com.xpressbees.things.dws.google_form.pojo;

import java.util.ArrayList;

/**
 * Created by ajinkyabadve on 12/12/17.
 */

public class Content {
    private String title;
    private String description;
    private String requiredLogo;

    @Override
    public String toString() {
        return "Content{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", requiredLogo='" + requiredLogo + '\'' +
                ", contentListItems=" + contentListItems +
                '}';
    }

    private ArrayList<ContentListItem> contentListItems;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredLogo() {
        return requiredLogo;
    }

    public void setRequiredLogo(String requiredLogo) {
        this.requiredLogo = requiredLogo;
    }

    public ArrayList<ContentListItem> getContentListItems() {
        return contentListItems;
    }

    public void setContentListItems(ArrayList<ContentListItem> contentListItems) {
        this.contentListItems = contentListItems;
    }
}
