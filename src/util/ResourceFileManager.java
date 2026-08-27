package util;

import model.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceFileManager {

    private static final String FILE_PATH = "data/resources.txt";

    // Add Resource
    public static void addResource(Resource resource) {

        try (BufferedWriter bw =
                     new BufferedWriter(
                             new FileWriter(FILE_PATH, true))) {

            bw.write(
                    resource.getTitle() + "," +
                            resource.getSubject() + "," +
                            resource.getType() + "," +
                            resource.getUploadedBy()
            );

            bw.newLine();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    // Get All Resources
    public static List<Resource> getAllResources() {

        List<Resource> list = new ArrayList<>();

        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader(FILE_PATH))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 4) {

                    list.add(new Resource(
                            data[0],
                            data[1],
                            data[2],
                            data[3]
                    ));

                }

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        return list;

    }

    // Delete Resource
    public static boolean deleteResource(String title) {

        List<Resource> resources = getAllResources();

        boolean deleted = false;

        try (BufferedWriter bw =
                     new BufferedWriter(
                             new FileWriter(FILE_PATH))) {

            for (Resource resource : resources) {

                // Skip the resource that should be deleted
                if (resource.getTitle().equals(title)) {

                    deleted = true;
                    continue;

                }

                bw.write(
                        resource.getTitle() + "," +
                                resource.getSubject() + "," +
                                resource.getType() + "," +
                                resource.getUploadedBy()
                );

                bw.newLine();
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        return deleted;

    }

}