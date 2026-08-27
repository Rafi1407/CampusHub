package controller;

import model.LostItem;
import model.Session;
import util.LostFileManager;

import java.util.List;

public class LostController {
    public void addItem(String itemName, String location, String status) {

        if (itemName == null || itemName.trim().isEmpty()) return;
        if (location == null || location.trim().isEmpty()) return;

        LostItem item = new LostItem(
                itemName.trim(),
                location.trim(),
                status,
                Session.getUsername()
        );
        LostFileManager.addItem(item);

    }
    public List<LostItem> getAllItems() {

        return LostFileManager.getAllItems();

    }

}