package com.cinemaui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.cinema.dao.AbstractDao;
import com.cinema.dao.ScheduleDao;
import com.cinema.dao.SeatDAO;
import com.cinema.dao.SeatDaoImpl;
import com.cinema.model.Schedule;
import com.cinema.model.Seat;

public class SeatWindow {

	private JFrame seatFrame;
	private JTable seatTable;
	private JScrollPane seatScrollPane;
	private JButton bookingButton;
	private JList<Seat> seatList;
	private AbstractDao<Schedule> scheduleDAO;
	private SeatDAO seatDao;
	private Schedule schedule;
	private String[] columns = {"id","name"};
	
	public SeatWindow(int scheduleID) throws SQLException {
		this.seatDao = new SeatDaoImpl();
		this.scheduleDAO = new ScheduleDao();
		this.schedule = this.scheduleDAO.findbyId(scheduleID);
		initializeComponents();
	}
	
	
	public void initializeComponents() throws SQLException {
		
		
		
		
		this.seatFrame = new JFrame("Available Seats for "+ this.schedule.getMovie().getTitle() +" at "+ this.schedule.getThreatre().getCinema().getName());
		this.seatFrame.setSize(700, 500);
		this.seatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.seatFrame.setLayout(new BorderLayout());
		this.seatFrame.setLocation(100, 100);		
		
		this.seatTable = new JTable(getSeatData(), this.columns);
		this.seatScrollPane = new JScrollPane(this.seatTable);
		this.seatFrame.add(this.seatScrollPane, BorderLayout.CENTER);
		this.seatFrame.setVisible(true);
		
		this.bookingButton = new JButton("Book seat");
		this.seatFrame.add(bookingButton, BorderLayout.SOUTH);
		addAction();
	}
	
	public void addAction() {
		this.bookingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			int selectedSeat = seatTable.getSelectedRow();
				if(selectedSeat!=-1) {
					System.out.println("Selectd seat: "+selectedSeat);
				}else {
					JOptionPane.showMessageDialog(seatFrame, "Please select a seat.");
				}
			}
		});
	}
	public String[][] getSeatData(){
		
		List<Seat> seatList = this.seatDao.getAllSeatsbyTheatreID(this.schedule.getThreatre().getId());
		System.out.println(seatList);
		String[][] allSeatData = new String[seatList.size()][columns.length];
		int rowCount = 0;
		for (Seat s : seatList) {
			
			for (int i = 0; i < columns.length; i++) {
				allSeatData[rowCount][i] = s.toArray()[i];
				
			}
			rowCount++;
		}
		System.out.println(allSeatData.length);
		return allSeatData;
	}
}
