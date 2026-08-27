package util;

import model.LostItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LostFileManager {

    private static final String FILE_PATH = "data/lostfound.txt";

    // Add Item
    public static void addItem(LostItem item) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

            bw.write(
                    item.getItemName() + "," +
                            item.getLocation() + "," +
                            item.getStatus() + "," +
                            item.getPostedBy()
            );

            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Read All Items
    public static List<LostItem> getAllItems() {

        List<LostItem> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 4) {

                    list.add(new LostItem(
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

}