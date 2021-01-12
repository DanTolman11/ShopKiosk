package com.Controller;

import com.Model.AccountManager;
import com.Model.data.User;

import java.io.IOException;

public class LoginController {
    private static LoginController instance;

    private LoginController() {
    }

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public boolean validateLogin(String username, String password) {
        try {
            User user = AccountManager.getUserByUsername(username);
            if(user == null){
                return false;
            }
            if(user.getPasword().equals(password)){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
