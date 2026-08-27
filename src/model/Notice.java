package model;

public class Notice {

    private String title;
    private String description;
    private String postedBy;

    public Notice(String title, String description, String postedBy) {

        this.title = title;
        this.description = description;
        this.postedBy = postedBy;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPostedBy() {
        return postedBy;
    }

}