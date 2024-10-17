package com.cinema.dao;

import java.util.List;

import com.cinema.model.Seat;

public abstract class SeatDAO extends AbstractDao<Seat>{
	public abstract List<Seat> getAllSeatsbyTheatreID(int theaterID);

}
