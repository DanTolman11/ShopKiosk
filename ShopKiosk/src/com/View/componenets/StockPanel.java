package com.View.componenets;

import com.Model.ItemManager;
import com.Model.data.Item;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class StockPanel extends JPanel {

    private static final Dimension LIST_DIMENSION = new Dimension(200, 200);

    private GridBagLayout mylayout;

    private JList<Item> lstItems;
    private JList<Item> lstBasket;

    private JButton btnAddToBasket;
    private JButton btnRemoveFromBasket;
    private JLabel lblBasket;
    private JButton btnPaymentCard;

    private JButton btnPaymentCash;


    private DefaultListModel<Item> itemsListModel = new DefaultListModel<>();
    private DefaultListModel<Item> basketListModel = new DefaultListModel<>();

    public StockPanel() {

        mylayout = new GridBagLayout();
        lstItems = new JList<>(itemsListModel);


        btnAddToBasket = new JButton("Add to basket");

        btnRemoveFromBasket = new JButton("Remove From basket");
        lstBasket = new JList<>(basketListModel);

        lstBasket.setMinimumSize(LIST_DIMENSION);
        lstItems.setMinimumSize(LIST_DIMENSION);

        lblBasket = new JLabel();
        btnPaymentCard = new JButton("CARD");
        btnPaymentCash = new JButton("CASH");

        this.setLayout(mylayout);
        GridBagConstraints gbc = new GridBagConstraints();

        itemsListModel.addAll(ItemManager.getAllItems());



        addComponenet(0,0,new JLabel("Kiosk"));
        addComponenet(0,1, lstItems);
        addComponenet(1,2,2, btnAddToBasket);

        addComponenet(1,3,2, btnRemoveFromBasket);
        addComponenet(2,0,new JLabel("Basket"));
        addComponenet(1,4,2, btnPaymentCard);

        addComponenet(1,5,2, btnPaymentCash);
        addComponenet(2,1,lstBasket);

        btnAddToBasket.addActionListener(event ->{
            int index = lstItems.getSelectedIndex();
            if(index != -1){
                Item item = itemsListModel.get(index);
                basketListModel.addElement(item);
            }
        });

        btnRemoveFromBasket.addActionListener(event ->{
            int index = lstBasket.getSelectedIndex();
            if(index != -1){
                basketListModel.remove(index);
            }
        });

        btnPaymentCard.addActionListener(event ->{
            double transactionTotal = getTransactionTotal();
            JOptionPane.showMessageDialog(this, String.format("The total transaction value is %.2f", transactionTotal));
            issueReciept();
            basketListModel.clear();
        });

        btnPaymentCash.addActionListener(event ->{

            double transactionTotal = getTransactionTotal();
            String enteredCashValue  = JOptionPane.showInputDialog(String.format("Transaction total %.2f Enter handled cash value", transactionTotal));

            try {
                double cashValue = Double.parseDouble(enteredCashValue);
                if(cashValue < transactionTotal){
                    JOptionPane.showMessageDialog(this, "handled cash is less than transaction value");
                    return;
                }

                JOptionPane.showMessageDialog(this, String.format("Change: %.2f", transactionTotal - cashValue ));

                issueReciept();
                basketListModel.clear();
            }catch(Exception e){
                e.printStackTrace();
            }


        });


    }

    private void issueReciept() {

    }

    private double getTransactionTotal() {
        double transactionTotal = 0.0;
        for (Iterator<Item> it = basketListModel.elements().asIterator(); it.hasNext(); ) {
            Item item = it.next();
            transactionTotal += item.getPrice();
        }
        return transactionTotal;
    }

    private void addComponenet(int gridX, int gridY, JComponent component){
       addComponenet(gridX, gridY, 1, component);
    }

    private void addComponenet(int gridX, int gridY, int gridWidth, JComponent component){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.gridwidth = gridWidth;
        this.add(component, gbc);
    }
}
