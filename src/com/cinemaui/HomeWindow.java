package com.cinemaui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class HomeWindow {

	private JFrame homeFrame;
	private JMenuBar homeMenuBar;
	private JMenu bookingMenu;
	private JMenu movieMenu;
	private JMenu cinemaMenu;
	private JMenu customerMenu;
	private JMenuItem ticketItem;
	private JMenuItem cancelTicketItem;
	private JMenuItem addCustomerItem;
	private JMenuItem showCustomerListItem;

	public HomeWindow() {
		initializeComponents();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = screenSize.width / 2;
		int centerY = screenSize.height / 2;
		this.homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.homeFrame.setLocation(centerX, centerY);
		this.homeFrame.setSize(700, 500);
		this.homeFrame.setVisible(true);
		addTicketBookingAction();
		addCustomerAction();
		addCustomerListAction();
	}

	private void initializeComponents() {
		this.homeFrame = new JFrame();
		this.homeMenuBar = new JMenuBar();

		this.bookingMenu = new JMenu("Booking");
		this.movieMenu = new JMenu("Movie");
		this.cinemaMenu = new JMenu("Cinema");
		this.customerMenu = new JMenu("Customer");

		this.ticketItem = new JMenuItem("Ticket Booking");
		this.cancelTicketItem = new JMenuItem("Cancel Booking");

		this.bookingMenu.add(ticketItem);
		this.bookingMenu.add(cancelTicketItem);

		this.addCustomerItem = new JMenuItem("Add customer");
		this.showCustomerListItem = new JMenuItem("Show customer list");
		this.customerMenu.add(addCustomerItem);
		this.customerMenu.add(showCustomerListItem);

		this.homeMenuBar.add(this.bookingMenu);
		this.homeMenuBar.add(this.movieMenu);
		this.homeMenuBar.add(this.cinemaMenu);
		this.homeMenuBar.add(this.customerMenu);

		this.homeFrame.setJMenuBar(this.homeMenuBar);
	}

	private void addTicketBookingAction() {
		this.ticketItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BookingWindow bookingWindow = new BookingWindow();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}

	private void addCustomerAction() {
		this.addCustomerItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				CustomerEntryWindow customerEntryWindow = new CustomerEntryWindow();

			}
		});
	}

	private void addCustomerListAction() {
		this.showCustomerListItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				CustomerListWindow customerListWindow = new CustomerListWindow();

			}
		});
	}
}
