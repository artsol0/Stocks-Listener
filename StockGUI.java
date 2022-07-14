import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StockGUI {

    public static void main(String[] args) {

        JFrame window = new JFrame("Stocks Listener");      // name of window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(450,250);                    // height and width
        window.setLocationRelativeTo(null);                     // move to center
        window.setLayout(null);

        JLabel label = new JLabel("Input name of stock:");
        label.setBounds(25,10,150,20);
        window.add(label);

        JLabel outputName = new JLabel();                           // output of name
        outputName.setBounds(95,60,400,20);
        window.add(outputName);

        JLabel outputPrice = new JLabel();                           // output of price
        outputPrice.setBounds(100,83,400,20);
        window.add(outputPrice);

        JLabel outputChanges = new JLabel();                           // output of changes
        outputChanges.setBounds(73,105,400,20);
        window.add(outputChanges);

        JTextField inputInc = new JTextField();                      // input field
        inputInc.setBounds(175,10,100,20);
        window.add(inputInc);

        JButton buy = new JButton("BUY");
        buy.setBounds(325,67,100,50);

        JButton button = new JButton("Search");
        button.setBounds(325,10,100,19);
        window.add(button);

        JLabel source = new JLabel("All info was getting from: https://finance.yahoo.com");
        source.setBounds(45,170,400,20);
        window.add(source);

        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    JOptionPane.showMessageDialog(null,"We are going to the market.. pleas wait...");

                    String inc = inputInc.getText();

                    String name = StockOf.getName(inc);
                    outputName.setText("Name: " + name);

                    double price = StockOf.priceOf(inc);
                    outputPrice.setText("Price: " + price);

                    String priceChanges = StockOf.priceChanges(inc);
                    String priceChangePercent = StockOf.priceChangePercent(inc);
                    outputChanges.setText("Changes: " + priceChanges + "(" + priceChangePercent + ")");

                    window.add(buy);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null,"This stock is not exist.");
                }

            }
        };

        ActionListener buyStocks = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String inc = inputInc.getText();
                    StockOf.goBuy(inc);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };

        button.addActionListener(actionListener);
        buy.addActionListener(buyStocks);
        window.setVisible(true);                                // get it visible
    }

}