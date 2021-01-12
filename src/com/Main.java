package com;

import com.View.componenets.NewItemDialogue;
import com.View.window.MainFrame;
import com.View.mainviews.MainView;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args){

        MainFrame frame = MainFrame.getInstance();
        frame.setTitle("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(650,650));
        frame.add(new MainView());
        frame.setVisible(true);

    }
//        CustomerKiosk page = new CustomerKiosk("title");
//        page.setVisible(true);
//
//        AdminGUI adminpage = new AdminGUI("title");
//        adminpage.setVisible(true);    }
}
