package model;

public class Resource {

    private String title;
    private String subject;
    private String type;
    private String uploadedBy;

    public Resource(String title, String subject, String type, String uploadedBy) {

        this.title = title;
        this.subject = subject;
        this.type = type;
        this.uploadedBy = uploadedBy;

    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getType() {
        return type;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

}