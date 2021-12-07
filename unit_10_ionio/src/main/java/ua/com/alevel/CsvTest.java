package ua.com.alevel;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class CsvTest {

    private static final String USERS_CSV = "users.csv";

    public void test() {
        List<User> users = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(random.nextInt(100));
            user.setName(UUID.randomUUID().toString());
            user.setAge(random.nextInt(15, 85));
            user.setVisible(true);
            users.add(user);
        }

        List<String[]> allLines = new ArrayList<>();

        String[] header = new String[] { "id", "name", "age", "visible" };
        allLines.add(header);
        for (User user : users) {
            String[] userCsv = new String[] {
                    user.getId().toString(),
                    user.getName(),
                    user.getAge().toString(),
                    user.getVisible().toString()
            };
            allLines.add(userCsv);
        }

        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(USERS_CSV))) {
            csvWriter.writeAll(allLines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(CSVReader csvReader = new CSVReader(new FileReader(USERS_CSV))) {
            List<String[]> strings = csvReader.readAll();
            List<User> userList = strings.stream()
                    .skip(1)
                    .map((String[] strings1) -> {
                        User user = new User();
                        user.setId(Integer.parseInt(strings1[0]));
                        user.setName(strings1[1]);
                        user.setAge(Integer.parseInt(strings1[2]));
                        user.setVisible(Boolean.parseBoolean(strings1[3]));
                        return user;
                    }).toList();
            System.out.println("userList = " + userList);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
