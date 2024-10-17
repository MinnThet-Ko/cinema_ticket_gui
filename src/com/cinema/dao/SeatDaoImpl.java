package com.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Seat;
import com.cinema.model.Theatre;

public class SeatDaoImpl extends SeatDAO {
	
	private PgSqlConnectionFactory connectionFactory;
	private AbstractDao<Theatre> theatreDao;
	
	public SeatDaoImpl() {
		this.connectionFactory = new PgSqlConnectionFactory();
		this.theatreDao = new TheatreDao();
	}

	@Override
	public String getTableName() {
		return "seats";
	}

	@Override
	public Seat convertToObject(ResultSet resultSet) throws SQLException {
			Seat seat = new Seat();
			seat.setId(resultSet.getInt("id"));
			seat.setTitle(resultSet.getString("name"));
			int theatre_id = resultSet.getInt("theatre_id");
			Theatre theatre = this.theatreDao.findbyId(theatre_id);
			seat.setTheatre(theatre);
			return seat;
	}


	@Override
	public String getInsertValues() {
		return "(name, theatre_id) values (?, ?)";
	}


	@Override
	public void setParameters(PreparedStatement preparedStatement, Seat entity) throws SQLException {
		preparedStatement.setString(1, entity.getTitle());
		preparedStatement.setInt(2, entity.getTheatre().getId());
	}
	
 	public List<Seat> getAllSeatsbyTheatreID(int id){
 		String sql = "select * from seats\n"
 				+ "where theatre_id = ?";
 		try {
			Connection connection = this.connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			List<Seat> resultList = new ArrayList<>();
			while(result.next()) {
				resultList.add(convertToObject(result));
			}
			this.connectionFactory.closeConnection();
			return resultList;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
			
	
 		return null;
 	}
}
