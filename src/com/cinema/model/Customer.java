package com.cinema.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private int id;
	private String name;
	private String email;
	private String address;
	private List<Ticket> tickets = new ArrayList<Ticket>();

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}
	
	public String[] toArray() {
		String[] dataArray =  new String[4];
		dataArray[0] = String.valueOf(this.id);
		dataArray[1] = this.name;
		dataArray[2] = this.email;
		dataArray[3] = this.address;
		return dataArray;
	}
}
