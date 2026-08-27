package controller;

import model.Notice;
import model.Session;
import util.NoticeFileManager;

import java.util.List;

public class NoticeController {

    public void addNotice(String title, String description) {

        if (title == null || title.trim().isEmpty()) {
            return;
        }

        if (description == null || description.trim().isEmpty()) {
            return;
        }

        Notice notice = new Notice(
                title.trim(),
                description.trim(),
                Session.getUsername()
        );

        NoticeFileManager.addNotice(notice);

    }

    public List<Notice> getAllNotices() {

        return NoticeFileManager.getAllNotices();

    }

}