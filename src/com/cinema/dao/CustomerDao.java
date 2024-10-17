package com.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Customer;

public class CustomerDao extends UpdateBehavior<Customer> {

	private PgSqlConnectionFactory connectionFactory;

	public CustomerDao() {
		this.connectionFactory = new PgSqlConnectionFactory();
	}

	@Override
	public String getTableName() {
		return "customers";
	}

	@Override
	public Customer convertToObject(ResultSet resultSet) throws SQLException {
		Customer customer = new Customer();
		customer.setId(resultSet.getInt("id"));
		customer.setName(resultSet.getString("name"));
		customer.setEmail(resultSet.getString("email"));
		customer.setAddress(resultSet.getString("address"));
		return customer;

	}

	@Override
	public String getInsertValues() {
		return "(name, email, address) values (?,?,?)";
	}

	@Override
	public void setParameters(PreparedStatement preparedStatement, Customer entity) throws SQLException {
		preparedStatement.setString(1, entity.getName());
		preparedStatement.setString(2, entity.getEmail());
		preparedStatement.setString(3, entity.getAddress());
	}

	@Override
	public void updateEntity(Customer entity) {

		try {
			String query = "update customers set  name = ?, address = ? , email = ? where id = ?";
			Connection connection = this.connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, entity.getName());
			preparedStatement.setString(2, entity.getAddress());
			preparedStatement.setString(3, entity.getEmail());
			preparedStatement.setInt(4, entity.getId());
			preparedStatement.executeUpdate();
			this.connectionFactory.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

}
