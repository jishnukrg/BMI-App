/*
 * File: BMIApp.java
 * Author: Jishnu Ganesh
 * Course: CSC 7435E - Secure DevOps Practices
 * Assignment: Module 4 - Assignment #1
 *
 * Description:
 * --------------
 * This is a standalone, user-friendly Java Swing application that calculates
 * a user's Body Mass Index (BMI). The app allows users to input their
 * weight and height, choose between metric (kg/m) or imperial (lb/ft) units,
 * and then displays the BMI value along with a corresponding image that
 * visually represents whether the user is Underweight, Healthy, or Overweight.
 *
 * The program uses a clean graphical interface built with Java Swing,
 * organized with layout managers for a structured look. It is completely
 * offline (no dependencies) and suitable for future CI/CD integration.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * BMIApp class
 * Extends JFrame to create a desktop window and implements ActionListener
 * to handle button click events. This is the main class that builds
 * the GUI and contains the logic for BMI calculation.
 */
public class BMIApp extends JFrame implements ActionListener {

    // --- Declare GUI components globally so they can be accessed across methods ---
    JTextField weightField, heightField;       // Input boxes for weight and height
    JComboBox<String> weightUnitBox, heightUnitBox; // Dropdowns to select units
    JLabel resultLabel, imageLabel;             // Labels to display BMI result and image

    /**
     * Constructor - sets up the window, layout, and all interface elements
     */
    public BMIApp() {
        // --- Window setup ---
        setTitle("BMI Calculator");          // Title bar text
        setSize(450, 500);                   // Window dimensions
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // Close program when window is closed
        setLocationRelativeTo(null);         // Center window on screen

        // --- Create the main panel with padding and layout ---
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));   // BorderLayout with gaps between components
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Adds margin inside the frame

        // --- Input section: weight + height fields with labels ---
        JPanel inputPanel = new JPanel(new GridBagLayout()); // Flexible layout for aligning labels/fields
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Add padding around each input field
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left

        // Create all labels for input fields and dropdowns
        JLabel weightLabel = new JLabel("Weight:");
        JLabel heightLabel = new JLabel("Height:");
        JLabel weightUnitLabel = new JLabel("Select weight unit:");
        JLabel heightUnitLabel = new JLabel("Select height unit:");

        // Text fields for user input
        weightField = new JTextField(10);
        heightField = new JTextField(10);

        // Dropdown menus for choosing units
        weightUnitBox = new JComboBox<>(new String[]{"kg", "lb"});
        heightUnitBox = new JComboBox<>(new String[]{"m", "ft"});

        // --- Add components row by row using GridBagLayout positioning ---
        gbc.gridx = 0; gbc.gridy = 0; inputPanel.add(weightLabel, gbc);
        gbc.gridx = 1; inputPanel.add(weightField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; inputPanel.add(weightUnitLabel, gbc);
        gbc.gridx = 1; inputPanel.add(weightUnitBox, gbc);

        gbc.gridx = 0; gbc.gridy = 2; inputPanel.add(heightLabel, gbc);
        gbc.gridx = 1; inputPanel.add(heightField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; inputPanel.add(heightUnitLabel, gbc);
        gbc.gridx = 1; inputPanel.add(heightUnitBox, gbc);

        // --- Button for calculating BMI ---
        JButton calcButton = new JButton("Calculate BMI");
        calcButton.setFont(new Font("Segoe UI", Font.BOLD, 13));  // Set font for better appearance
        calcButton.addActionListener(this); // Attach event listener (this class handles the click)

        // --- Labels for showing results and images ---
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultLabel = new JLabel(" ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        imageLabel = new JLabel("", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(200, 150)); // Reserve space for the image

        // --- Add input and result sections to main panel ---
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(calcButton, BorderLayout.CENTER);
        panel.add(resultLabel, BorderLayout.SOUTH);

        // --- Add main panel + image label to outer container ---
        JPanel outerPanel = new JPanel(new BorderLayout(10, 10));
        outerPanel.add(panel, BorderLayout.NORTH);
        outerPanel.add(imageLabel, BorderLayout.CENTER);

        // Add everything to the window and make it visible
        add(outerPanel);
        setVisible(true);
    }

    /**
     * Event handler for the "Calculate BMI" button.
     * This method performs the core logic:
     * - Reads input values
     * - Converts imperial units to metric
     * - Calculates BMI
     * - Determines BMI category
     * - Displays result and image
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Parse numeric input values from text fields
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());

            // --- Convert lb → kg and ft → m if necessary ---
            if (weightUnitBox.getSelectedItem().equals("lb")) weight *= 0.453592;
            if (heightUnitBox.getSelectedItem().equals("ft")) height *= 0.3048;

            // --- BMI formula ---
            double bmi = weight / (height * height);

            String category;
            String imagePath;

            // --- Determine BMI category based on standard ranges ---
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

            // --- Display result text in the label ---
            resultLabel.setText(String.format("BMI: %.2f (%s)", bmi, category));

            // --- Display and resize image based on category ---
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImg = icon.getImage().getScaledInstance(180, 150, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImg));

        } catch (Exception ex) {
            // --- Handle invalid input (non-numeric or empty fields) ---
            resultLabel.setText("⚠ Please enter valid numeric values.");
            imageLabel.setIcon(null); // Remove image if error occurs
        }
    }

    /**
     * Main method - program entry point
     * Uses SwingUtilities.invokeLater to ensure GUI is built on the
     * Event Dispatch Thread (recommended practice in Swing apps).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BMIApp());
    }
}
