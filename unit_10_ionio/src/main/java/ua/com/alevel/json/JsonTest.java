package ua.com.alevel.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import ua.com.alevel.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class JsonTest {

    private static final String USER_JSON = "user.json";
    private static final String USERS_JSON = "users.json";
    private static final String SOMETHING_JSON = "something.json";

    public void test() {
        parseJson();
    }

    private void convertJsonToObj() {
        User user = new User();
        user.setId(10);
        user.setName("name");
        user.setAge(20);
        user.setVisible(true);

        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        System.out.println("userJson = " + userJson);
        user = gson.fromJson(userJson, User.class);
        System.out.println("user = " + user);

//        try(FileWriter fileWriter = new FileWriter(USER_JSON)) {
//            fileWriter.write(userJson);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try (FileReader fileReader = new FileReader(USER_JSON);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder builder = new StringBuilder();
            while (bufferedReader.ready()) {
                builder.append(bufferedReader.readLine());
            }
            System.out.println("s = " + builder.toString());
            user = gson.fromJson(builder.toString(), User.class);
            System.out.println("user = " + user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            user = gson.fromJson(new FileReader(USER_JSON), User.class);
            System.out.println("!!! user = " + user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void convertArrayToObj() {
        Gson gson = new Gson();
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

        String jsonUsers = gson.toJson(users);
        System.out.println("jsonUsers = " + jsonUsers);

        try (FileWriter fileWriter = new FileWriter(USERS_JSON)) {
            fileWriter.write(jsonUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }

        users = gson.fromJson(jsonUsers, new TypeToken<List<User>>(){ }.getType());
        System.out.println("users = " + users);
    }

    private void parseJson() {
        Gson gson = new Gson();
        try {
            JsonElement json = gson.fromJson(new FileReader(SOMETHING_JSON), JsonElement.class);
            startParse(json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void startParse(JsonElement json) {
        if (json.isJsonArray()) {
            JsonArray jsonArray = json.getAsJsonArray();
            readJsonArray(jsonArray);
        } else {
            readJsonObject(json.getAsJsonObject());
        }
    }

    private void readJsonArray(JsonArray jsonArray) {
        for (JsonElement jsonElement : jsonArray) {
            if (jsonElement.isJsonArray()) {
                readJsonArray(jsonElement.getAsJsonArray());
            } else if (jsonElement.isJsonPrimitive()) {
                System.out.println("jsonElement = " + jsonElement);
            } else {
                readJsonObject(jsonElement.getAsJsonObject());
            }
        }
    }

    private void readJsonObject(JsonObject jsonObject) {
        if (!jsonObject.isJsonNull()) {
            System.out.println("jsonObject = " + jsonObject);
            if (jsonObject.isJsonPrimitive()) {
                System.out.println("jsonObject primitive");
            }
            if (jsonObject.isJsonObject()) {
                System.out.println("jsonObject obj");
                Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
                for (Map.Entry<String, JsonElement> entry : entries) {
                    System.out.println("key = " + entry.getKey());
                    System.out.println("value = " + entry.getValue());
                    if (entry.getValue().isJsonObject()) {
                        readJsonObject(entry.getValue().getAsJsonObject());
                    }
                    if (entry.getValue().isJsonArray()) {
                        readJsonArray(entry.getValue().getAsJsonArray());
                    }
                }
            }
            if (jsonObject.isJsonArray()) {
                System.out.println("jsonObject array");
                readJsonArray(jsonObject.getAsJsonArray());
            }
        }
    }
}
