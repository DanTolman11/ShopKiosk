package com.View.componenets;

import com.Controller.LoginController;
import com.View.mainviews.AdminView;
import com.View.window.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;

public class LoginPanel extends JPanel{
    private JLabel lblAdmin;
    private JButton btnLogin;
    private JTextField txtUsername;
    private JTextField txtPassword;


    private LoginController controller = LoginController.getInstance();
    private GridBagLayout mylayout;

    public LoginPanel() {
        //initilises components
        txtUsername = new JTextField(12);
        txtPassword = new JPasswordField(12);
        btnLogin = new JButton("Admin Login");


        //adds components
        mylayout = new GridBagLayout();
        this.setLayout(mylayout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx=0;
        gbc.gridy=0;
        this.add(new JLabel("Username:"), gbc);

        gbc.gridx=1;
        this.add(txtUsername, gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        this.add(new JLabel("Password:"), gbc);

        gbc.gridy=1;
        gbc.gridx=1;
        this.add(txtPassword, gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth = 2;
        this.add(btnLogin, gbc);

        btnLogin.addActionListener(e ->{
            boolean valid = controller.validateLogin(txtUsername.getText(), txtPassword.getText());
            if(valid){
                //remove all content, change view
                MainFrame.getInstance().getContentPane().removeAll();
                MainFrame.getInstance().add(new AdminView());
                MainFrame.getInstance().pack();

            }
        });
    }
}
