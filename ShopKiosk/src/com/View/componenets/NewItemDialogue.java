package com.View.componenets;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

public class NewItemDialogue
{

    public static final int OK = 0;
    public static final int CANCEL = 1;

    private List<JComponent> components;

    private JPanel mainPanel = new JPanel();

    private JLabel lblStockName;
    private JTextField txtStockName;

    private JLabel lblPrice;
    private JTextField txtPrice;

    private JLabel lblId;
    private JTextField txtId;

    private JLabel lblAmount;
    private JTextField txtAmount;

    public NewItemDialogue()
    {
        components = new ArrayList<>(Arrays.asList(mainPanel));

        lblStockName = new JLabel("Stock Name");
        txtStockName = new JTextField(25);
        lblPrice = new JLabel("Price");
        txtPrice = new JTextField(25);
        lblId = new JLabel("Id");
        txtId = new JTextField(25);
        lblAmount = new JLabel("Amount");
        txtAmount = new JTextField(25);

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx=1;
        gbc.gridy=0;
        mainPanel.add(lblStockName, gbc);

        gbc.gridx=2;
        gbc.gridy=0;
        mainPanel.add(txtStockName, gbc);

        gbc.gridx=1;
        gbc.gridy=1;
        mainPanel.add(lblPrice, gbc);

        gbc.gridx=2;
        gbc.gridy=1;
        mainPanel.add(txtPrice, gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        mainPanel.add(lblId, gbc);

        gbc.gridx=2;
        gbc.gridy=3;
        mainPanel.add(txtId, gbc);

        gbc.gridx=1;
        gbc.gridy=3;
        mainPanel.add(lblAmount, gbc);

        gbc.gridx=2;
        gbc.gridy=4;
        mainPanel.add(txtAmount, gbc);

    }
    public int show()
    {

        int selection = JOptionPane.showOptionDialog(null,
                components.toArray(), "title",  JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                new String[] { "OK", "Cancel" }, 0);

        return selection;
    }

    public static String getLineBreak()
    {
        return "<br>";
    }

    public String getStockName() {
        return txtStockName.getText();
    }

    public double getPrice() throws NumberFormatException{
        return Double.parseDouble(txtPrice.getText());
    }

    public String getId() {
        return txtId.getText();
    }

    public int getAmount()throws  NumberFormatException{
        return Integer.parseInt(txtAmount.getText());
    }
}