package com.cinemaui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import com.cinema.dao.CustomerDao;
import com.cinema.model.Customer;

public class CustomerListWindow {
	private JFrame customerListFrame;
	private JScrollPane scrollPane;
	private JTable customerTable;
	private JPanel buttonPanel;
	private JButton updateButton;
	private JButton deleteButton;

	private final String[] CUSTOMER_TABLE_COLUMNS = { "ID", "Name", "Email", "Address" };
	private String[][] customerData;
	private CustomerDao customerDAO;

	public CustomerListWindow() {
		this.customerDAO = new CustomerDao();

		initializeComponents();
	}

	public void initializeComponents() {
		getCustomerData();
		this.customerListFrame = new JFrame();
		this.customerTable = new JTable(this.customerData, CUSTOMER_TABLE_COLUMNS);
		this.scrollPane = new JScrollPane(customerTable);
		
		this.buttonPanel = new JPanel(new BorderLayout());
		this.updateButton = new JButton("Update");
		handleUpdateEvent();
		this.deleteButton = new JButton("Delete");
		handleDeleteEvent();

		this.customerListFrame.setLayout(new BorderLayout());
		this.customerListFrame.add(this.scrollPane, BorderLayout.CENTER);
		this.buttonPanel.add(this.updateButton, BorderLayout.LINE_START);
		this.buttonPanel.add(this.deleteButton, BorderLayout.LINE_END);
		this.customerListFrame.add(this.buttonPanel, BorderLayout.SOUTH);
		this.customerListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.customerListFrame.setSize(700, 400);
		this.customerListFrame.setVisible(true);
	}

	public void getCustomerData() {
		try {

			List<Customer> customerList = this.customerDAO.getAll();
			this.customerData = new String[customerList.size()][this.CUSTOMER_TABLE_COLUMNS.length];
			int rowCount = 0;
			for (Customer c : customerList) {
				for (int i = 0; i < CUSTOMER_TABLE_COLUMNS.length; i++) {
					this.customerData[rowCount][i] = c.toArray()[i];
				}
				rowCount++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void handleUpdateEvent() {
		this.updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = customerTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(customerListFrame, "Please select a customer.");
				} else {
					int customerID = Integer.parseInt(customerData[selectedRow][0]);

					try {
						Customer selectedCustomer = customerDAO.findbyId(customerID);
						CustomerEntryWindow enteryWindow = new CustomerEntryWindow(selectedCustomer);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
	}

	public void handleDeleteEvent() {
		this.deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = customerTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(customerListFrame, "Please select a customer.");
				} else {
					int isDeleteConfirmed = JOptionPane.showConfirmDialog(customerListFrame,
							"Do you want to delete this customer?", "Delete Customer", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (isDeleteConfirmed == JOptionPane.YES_OPTION) {

						int customerID = Integer.parseInt(customerData[selectedRow][0]);
						try {
							customerDAO.delete(customerID);
							JOptionPane.showMessageDialog(customerListFrame, "Delete successful");
							getCustomerData();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
	}
}
