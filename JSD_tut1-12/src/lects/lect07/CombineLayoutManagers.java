package lects.lect07;

import javax.swing.*;
import java.awt.*;

public class CombineLayoutManagers {
    public static void main(String[] args) {
        JFrame w = new JFrame("Combine layout managers");
        w.setSize(420, 300);
        w.setLocationRelativeTo(null);
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // search bar
        JPanel searchBar = new JPanel();
        JTextField keyword = new JTextField(12);
        JButton searchButton = new JButton("Search!");
        searchBar.add(new JLabel("Search:"));
        searchBar.add(keyword);
        searchBar.add(searchButton);

        // add search bar into window (north)
        w.add(searchBar, BorderLayout.NORTH);

        // middle section
        JPanel middle = new JPanel();
        ((FlowLayout) middle.getLayout()).setAlignment(FlowLayout.LEFT);
        JLabel lblName = new JLabel("Name:");
        lblName.setPreferredSize(new Dimension(105, 21));
        JLabel lblAddr = new JLabel("Address:");
        lblAddr.setPreferredSize(new Dimension(105, 21));
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setPreferredSize(new Dimension(105, 21));
        JTextField name = new JTextField(24);
        JTextField addr = new JTextField(24);
        JComboBox cbGender = new JComboBox(new String[]{"Female", "Male", "Other"});
        cbGender.setPreferredSize(new Dimension(135, 21));
        middle.add(lblName);
        middle.add(name);
        middle.add(lblAddr);
        middle.add(addr);
        middle.add(lblGender);
        middle.add(cbGender);

        // add middle panel to window (center)
        w.add(middle, BorderLayout.CENTER);

        // bottom pagination section
        JPanel pagination = new JPanel();
        ((FlowLayout) pagination.getLayout()).setAlignment(FlowLayout.RIGHT);
        JButton next = new JButton("Next >");
        JButton prev = new JButton("< Previous");
        JTextField page = new JTextField(3);
        JButton goToPage = new JButton("Go");
        pagination.add(prev);
        pagination.add(next);
        pagination.add(page);
        pagination.add(goToPage);


        // add pagination to window (south)
        w.add(pagination, BorderLayout.SOUTH);

        w.setVisible(true);
    }
}
