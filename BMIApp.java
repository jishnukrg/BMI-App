/*
 * File: BMIApp.java
 * Author: Jishnu Ganesh
 * Description:
 * A standalone, user-friendly Java Swing BMI calculator.
 * Supports metric (kg/m) and imperial (lb/ft) units,
 * and displays an image according to BMI category.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BMIApp extends JFrame implements ActionListener {
    JTextField weightField, heightField;
    JComboBox<String> weightUnitBox, heightUnitBox;
    JLabel resultLabel, imageLabel;

    public BMIApp() {
        // --- Window setup ---
        setTitle("BMI Calculator");
        setSize(450, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center screen

        // --- Main panel ---
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // --- Input section ---
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel weightLabel = new JLabel("Weight:");
        JLabel heightLabel = new JLabel("Height:");
        JLabel weightUnitLabel = new JLabel("Select weight unit:");
        JLabel heightUnitLabel = new JLabel("Select height unit:");

        weightField = new JTextField(10);
        heightField = new JTextField(10);

        weightUnitBox = new JComboBox<>(new String[]{"kg", "lb"});
        heightUnitBox = new JComboBox<>(new String[]{"m", "ft"});

        // Add items neatly with GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0; inputPanel.add(weightLabel, gbc);
        gbc.gridx = 1; inputPanel.add(weightField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; inputPanel.add(weightUnitLabel, gbc);
        gbc.gridx = 1; inputPanel.add(weightUnitBox, gbc);

        gbc.gridx = 0; gbc.gridy = 2; inputPanel.add(heightLabel, gbc);
        gbc.gridx = 1; inputPanel.add(heightField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; inputPanel.add(heightUnitLabel, gbc);
        gbc.gridx = 1; inputPanel.add(heightUnitBox, gbc);

        // --- Button and result section ---
        JButton calcButton = new JButton("Calculate BMI");
        calcButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        calcButton.addActionListener(this);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultLabel = new JLabel(" ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        imageLabel = new JLabel("", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(200, 150));

        // --- Assemble everything ---
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(calcButton, BorderLayout.CENTER);
        panel.add(resultLabel, BorderLayout.SOUTH);

        // --- Outer container for better alignment ---
        JPanel outerPanel = new JPanel(new BorderLayout(10, 10));
        outerPanel.add(panel, BorderLayout.NORTH);
        outerPanel.add(imageLabel, BorderLayout.CENTER);

        add(outerPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());

            // Convert to metric
            if (weightUnitBox.getSelectedItem().equals("lb")) weight *= 0.453592;
            if (heightUnitBox.getSelectedItem().equals("ft")) height *= 0.3048;

            double bmi = weight / (height * height);
            String category;
            String imagePath;

            if (bmi < 18.5) {
                category = "Underweight";
                imagePath = "images/underweight.png";
            } else if (bmi < 25) {
                category = "Healthy";
                imagePath = "images/healthy.png";
            } else {
                category = "Overweight";
                imagePath = "images/overweight.png";
            }

            resultLabel.setText(String.format("BMI: %.2f (%s)", bmi, category));

            // Scale image nicely
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImg = icon.getImage().getScaledInstance(180, 150, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImg));

        } catch (Exception ex) {
            resultLabel.setText("⚠ Please enter valid numeric values.");
            imageLabel.setIcon(null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BMIApp());
    }
}
