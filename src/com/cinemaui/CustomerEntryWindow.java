package com.cinemaui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.cinema.dao.AbstractDao;
import com.cinema.dao.CustomerDao;
import com.cinema.dao.UpdateBehavior;
import com.cinema.model.Customer;

public class CustomerEntryWindow {
	private JFrame customerFrame;
	private JLabel nameLabel;
	private JTextField nameInput;
	private JLabel emailLabel;
	private JTextField emailInput;
	private JLabel addressLabel;
	private JTextField addressInput;
	private JButton submitButton;
	private UpdateBehavior<Customer> customerDAO;
	private String entryMode;
	private Customer selectedCustomer;

	public CustomerEntryWindow() {

		this.customerDAO = new CustomerDao();
		this.entryMode = "INSERT";
		initializeComponents();
	}
	
	public CustomerEntryWindow(Customer customer) {
		this.selectedCustomer = customer;
		this.customerDAO = new CustomerDao();
		this.entryMode = "UPDATE";
		initializeComponents();
		this.addressInput.setText(customer.getAddress());
		this.nameInput.setText(customer.getName());
		this.emailInput.setText(customer.getEmail());
		this.submitButton.setText("Update");
	}

	public void initializeComponents() {
		this.customerFrame = new JFrame();

		this.nameLabel = new JLabel("Name:");
		this.nameInput = new JTextField(20);

		this.emailLabel = new JLabel("Email:");
		this.emailInput = new JTextField(20);

		this.addressLabel = new JLabel("Address:");
		this.addressInput = new JTextField(20);

		this.submitButton = new JButton("Submit");

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		this.customerFrame.setLayout(layout);
		constraints.fill = GridBagConstraints.HORIZONTAL;

		// Set name label
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.customerFrame.add(this.nameLabel, constraints);

		// Set name input
		constraints.gridx = 1;
		this.customerFrame.add(this.nameInput, constraints);

		// Set email label
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.customerFrame.add(this.emailLabel, constraints);

		// Set name input
		constraints.gridx = 1;
		this.customerFrame.add(this.emailInput, constraints);

		// Set email label
		constraints.gridx = 0;
		constraints.gridy = 2;
		this.customerFrame.add(this.addressLabel, constraints);

		// Set name input
		constraints.gridx = 1;
		this.customerFrame.add(this.addressInput, constraints);

		// Set submit button
		constraints.gridx = 0;
		constraints.gridy = 3;
		this.customerFrame.add(this.submitButton, constraints);

		this.customerFrame.setSize(500, 500);
		this.customerFrame.setVisible(true);
		this.customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		handleSubmitEvent();
	}

	public void clearInput() {
		this.nameInput.setText("");
		this.emailInput.setText("");
		this.addressInput.setText("");
	}

	public void handleSubmitEvent() {
		this.submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Customer customer = new Customer();
				customer.setName(nameInput.getText());
				customer.setEmail(emailInput.getText());
				customer.setAddress(addressInput.getText());
				try {
					if(entryMode.equals("INSERT")) {
					customerDAO.create(customer);
					}else  if(entryMode.equals("UPDATE")) {
						customer.setId(selectedCustomer.getId());
						customerDAO.updateEntity(customer);
					}
					JOptionPane.showMessageDialog(customerFrame, "Successfully created customer.");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(customerFrame, "Cannot create customer.");
				} finally {
					clearInput();
				}

			}
		});
	}
}
