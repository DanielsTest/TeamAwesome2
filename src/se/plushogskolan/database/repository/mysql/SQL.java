package se.plushogskolan.database.repository.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQL {
	private final String url;
	private String sql;
	private List<Object> parameterList = new ArrayList<>();

	public SQL(String url) {
		this.url = url;
	}

	public SQL query(String sql) {
		this.sql = sql;
		return this;
	}

	public SQL param(Object o) {
		parameterList.add(o);
		return this;
	}

	public PreparedStatement prepareStatement(Connection conn) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(sql);
		for (int i = 0; i < parameterList.size(); i++) {
			statement.setObject(i + 1, parameterList.get(i));
		}
		return statement;
	}

	public <T> List<T> many(Mapper<T> mapper) throws SQLException {
		try (Connection conn = DriverManager.getConnection(url, "awesome", "database");
				PreparedStatement statement = prepareStatement(conn);
				ResultSet result = statement.executeQuery()) {

			List<T> teamList = new ArrayList<>();
			while (result.next()) {
				teamList.add(mapper.map(result));
			}
			return teamList;
		}

	}

}
