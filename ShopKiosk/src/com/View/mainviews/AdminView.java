package com.View.mainviews;

import com.Controller.LoginController;
import com.Model.data.Item;
import com.Model.ItemManager;
import com.View.componenets.NewItemDialogue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
import java.util.Iterator;


import javax.swing.*;
import java.awt.*;

public class AdminView extends JPanel {


    private GridBagLayout mylayout;
    private JLabel lblAdminPanel;
    private JButton btnAddStock;
    private JButton btnRemoveStock;
    private JButton btnEditStock;
    private JButton btnBack;
    private JList<Item> lstStock;

    private static final Dimension List_DIMENSION = new Dimension(200,200);
    private LoginController controller = LoginController.getInstance();
    private DefaultListModel<Item> stockListModel = new DefaultListModel<>();

    public AdminView() {

        mylayout = new GridBagLayout();
        this.setLayout(mylayout);
        GridBagConstraints gbc = new GridBagConstraints();

        lstStock = new JList<>(stockListModel);
        lstStock.setMinimumSize(List_DIMENSION);

        gbc.gridx=1;
        gbc.gridy=0;
        lblAdminPanel = new JLabel("Administrator Interface");
        this.add(lblAdminPanel, gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=2;
        btnAddStock = new JButton("Add Stock");
        this.add(btnAddStock, gbc);

        gbc.gridx=2;
        gbc.gridy=1;
        gbc.gridwidth=2;
        btnRemoveStock = new JButton("Remove Stock");
        this.add(btnRemoveStock, gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=2;
        btnEditStock = new JButton("Edit Stock");
        this.add(btnEditStock, gbc);

        gbc.gridx=2;
        gbc.gridy=2;
        gbc.gridwidth=2;
        btnBack = new JButton("Back");
        this.add(btnBack, gbc);

        stockListModel.addAll(ItemManager.getAllItems());

        gbc.gridx=1;
        gbc.gridy=3;
        this.add(lstStock, gbc);


        btnRemoveStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item exist = lstStock.getSelectedValue();
                if(exist != null){
                    ItemManager.removeItem(exist);
                }
                refreshStockList();
            }
        });
        btnAddStock.addActionListener(event ->{
            NewItemDialogue d = new NewItemDialogue();
            int result = d.show();

            if(result == NewItemDialogue.OK){
                try {
                    String stockName = d.getStockName();
                    double stockPrice = d.getPrice();
                    String stockId = d.getId();
                    int stockAmount = d.getAmount();
                    Item item = new Item(stockName, stockId, stockPrice, stockAmount);

                    ItemManager.addItem(item);

                }catch (NumberFormatException e){
                    System.err.println("Dumbass");
                }

            }
            refreshStockList();
        });

        btnEditStock.addActionListener(event ->{
            Item remainingItem = lstStock.getSelectedValue();
            if(remainingItem != null){
                ItemManager.removeItem(remainingItem);

                NewItemDialogue inputItem = new NewItemDialogue();
                int result = inputItem.show();

                if(result == NewItemDialogue.OK){
                    try{
                        String stockName = inputItem.getStockName();
                        String stockId = inputItem.getId();
                        double stockPrice = inputItem.getPrice();
                        int stockAmount = inputItem.getAmount();
                        Item newItem = new Item(stockName,stockId,stockPrice,stockAmount);

                        ItemManager.addItem(newItem);
                    }catch(NumberFormatException e){
                        System.err.println(("Error, new item couldnt be created"));
                    }
                }
            }
            refreshStockList();
        });

    }

    private void refreshStockList() {
        stockListModel.removeAllElements();
        stockListModel.addAll(ItemManager.getAllItems());
    }
}
