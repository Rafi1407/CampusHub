package model;

public class LostItem {

    private String itemName;
    private String location;
    private String status;
    private String postedBy;

    public LostItem(String itemName, String location, String status, String postedBy) {

        this.itemName = itemName;
        this.location = location;
        this.status = status;
        this.postedBy = postedBy;

    }

    public String getItemName() {
        return itemName;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getPostedBy() {
        return postedBy;
    }

}