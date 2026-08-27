package util;

import model.Notice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NoticeFileManager {

    private static final String FILE_PATH = "data/notices.txt";

    // Add Notice
    public static void addNotice(Notice notice) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

            bw.write(
                    notice.getTitle() + "," +
                            notice.getDescription() + "," +
                            notice.getPostedBy()
            );

            bw.newLine();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    // Get All Notices
    public static List<Notice> getAllNotices() {

        List<Notice> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 3) {

                    list.add(new Notice(
                            data[0],
                            data[1],
                            data[2]
                    ));

                }

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        return list;

    }

}