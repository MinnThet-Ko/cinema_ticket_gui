package com.cinemaui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow {

	private JFrame jFrame;
	private JLabel userLabel;
	private JTextField userTextField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton resetButton;

	public LoginWindow() {
		initializeComponent();
		setupLayout();
		this.jFrame.setVisible(true);
		this.jFrame.setLocationRelativeTo(null);
		setupLoginButtonAction();
	}

	private void setupLoginButtonAction() {
		this.loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = userTextField.getText();
				String password = new String(passwordField.getPassword());
				if (username.equals("admin") && password.equals("password")) {
					jFrame.dispose();
					HomeWindow homeWindow = new HomeWindow();
					
				}else {
					JOptionPane.showMessageDialog(jFrame, "Invalid username and password!");
				}
			}
		});
	}

	private void initializeComponent() {
		this.jFrame = new JFrame("Login");
		this.userLabel = new JLabel("Username");
		this.userTextField = new JTextField(15);
		this.passwordLabel = new JLabel("Password");
		this.passwordField = new JPasswordField(15);
		this.loginButton = new JButton("Login");
		this.resetButton = new JButton("Reset");
	}

	private void setupLayout() {
		this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jFrame.setSize(350, 200);
		this.jFrame.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 0;
		constraints.gridy = 0;

		this.jFrame.add(this.userLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 0;

		this.jFrame.add(this.userTextField, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;

		this.jFrame.add(this.passwordLabel, constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;

		this.jFrame.add(this.passwordField, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;

		this.jFrame.add(this.loginButton, constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;

		this.jFrame.add(this.resetButton, constraints);
	}
}
