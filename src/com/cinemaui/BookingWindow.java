package com.cinemaui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.cinema.dao.AbstractDao;
import com.cinema.dao.ScheduleDao;
import com.cinema.model.Schedule;

public class BookingWindow {
	private JFrame bookingFrame;
	private JTable movieTable;
	private JScrollPane bookingScrollPane;
	private JButton bookingButton;

	private String[] columns = { "ID", "Movie Title", "Cinema Name", "Theatre Name", "Start Time", "End Time", "Publish Date",
			"Duration" };
	private AbstractDao<Schedule> scheduleDAO;

	public BookingWindow() throws SQLException {
		this.scheduleDAO = new ScheduleDao();
		initializeComponents();
		
	}
	
	public void initializeComponents() throws SQLException {
		this.bookingFrame = new JFrame("Movie Booking");
		this.bookingFrame.setSize(600, 500);
		this.bookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.bookingFrame.setLayout(new BorderLayout());
		this.movieTable = new JTable(getMovieData(), this.columns);
		this.bookingScrollPane = new JScrollPane(this.movieTable);
		this.bookingFrame.add(bookingScrollPane, BorderLayout.CENTER);
		
		this.bookingButton = new JButton("Select movie and book seat");
		this.bookingFrame.add(this.bookingButton, BorderLayout.SOUTH);
		
		this.bookingFrame.setLocationRelativeTo(null);
		this.bookingFrame.setVisible(true);
		selectMovieBookingAction();
	}

	private String[][] getMovieData() throws SQLException {
		List<Schedule> scheduleList = this.scheduleDAO.getAll();
		String[][] allMovieData = new String[scheduleList.size()][columns.length];
		int rowCount = 0;
		for (Schedule s : scheduleList) {
			
			for (int i = 0; i < columns.length; i++) {
				allMovieData[rowCount][i] = s.toArray()[i];
				
			}
			rowCount++;
		}
		System.out.println(allMovieData);
		return allMovieData;
	}
	
	private void selectMovieBookingAction() {
		this.bookingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = movieTable.getSelectedRow();
				if(selectedRow!= -1) {
				try {
					int scheduleID = Integer.parseInt(getMovieData()[selectedRow][0]);
					SeatWindow seatWindow = new SeatWindow(scheduleID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}else {
					JOptionPane.showMessageDialog(bookingFrame, "Please select a movie");
				}
				
			}
		});
	}
}
