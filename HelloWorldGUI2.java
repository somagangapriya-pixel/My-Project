import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HelloWorldGUI2 {
    public static void main(String[] args) {
        // Create a new JFrame (window)
        JFrame frame = new JFrame("Hello World GUI with Background");
        frame.setSize(500, 300); // Bigger window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Custom JPanel with gradient background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                // Gradient from blue → purple
                GradientPaint gradient = new GradientPaint(0, 0, Color.CYAN, getWidth(), getHeight(), Color.MAGENTA);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null); // Absolute positioning
        frame.setContentPane(panel); // Set panel as background

        // Create a JLabel
        JLabel label = new JLabel("Hello, World!");
        label.setBounds(180, 30, 200, 30);
        label.setForeground(Color.WHITE); // White text looks nice on gradient
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label);

        // Create a JTextField
        JTextField textField = new JTextField("Type something...");
        textField.setBounds(150, 80, 200, 30);
        panel.add(textField);

        // Create a JButton (Change Label)
        JButton buttonChange = new JButton("Set Text");
        buttonChange.setBounds(150, 130, 100, 30);
        panel.add(buttonChange);

        // Create another JButton (Clear/Reset)
        JButton buttonClear = new JButton("Clear");
        buttonClear.setBounds(260, 130, 100, 30);
        panel.add(buttonClear);

        // Action for "Set Text" button
        buttonChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                if (input.trim().isEmpty()) {
                    label.setText("Please type something!");
                } else {
                    label.setText(input); // Change label to user input
                }
            }
        });

        // Action for "Clear" button
        buttonClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                label.setText("Hello, World!"); // Reset label
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}