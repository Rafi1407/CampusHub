package controller;

import model.Resource;
import model.Session;
import util.ResourceFileManager;

import java.util.ArrayList;
import java.util.List;

public class ResourceController {

    public void addResource(String title, String subject, String type) {

        if (title.trim().isEmpty() || subject.trim().isEmpty()) {
            return;
        }

        Resource resource = new Resource(

                title,
                subject,
                type,
                Session.getUsername()

        );
        ResourceFileManager.addResource(resource);

    }

    public List<Resource> getAllResources() {

        return ResourceFileManager.getAllResources();

    }

    public List<Resource> searchResource(String keyword) {

        List<Resource> result = new ArrayList<>();

        for (Resource r : getAllResources()) {

            if (r.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || r.getSubject().toLowerCase().contains(keyword.toLowerCase())
                    || r.getType().toLowerCase().contains(keyword.toLowerCase())) {

                result.add(r);

            }

        }

        return result;

    }

}