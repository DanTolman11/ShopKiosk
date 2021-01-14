package com.Model;

import com.Model.data.User;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountManager {
    private static List<User> users;

    private static void loadUserAccounts() throws IOException {

        users = new ArrayList<>();

        List<String> lines = Files.readAllLines(Path.of("Resources/AdminAccounts.csv"));

        for (String line: lines) {
            String[] data = line.split(",");
            String username = data[0];
            String password = data[1];
            users.add(new User(username, password));
            System.out.printf("Username: %s, Password: %s\n", username, password);
        }
    }

    public static User getUserByUsername(String username) throws IOException {
        if(users == null){
            loadUserAccounts();
        }
        for(User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
