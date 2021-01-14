package com.View.mainviews;

import com.View.componenets.LoginPanel;
import com.View.componenets.StockPanel;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {

    public MainView() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.add(new LoginPanel(),gbc);
        gbc.gridy = 1;
        this.add(new StockPanel(),gbc);
    }
}
