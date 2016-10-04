package se.plushogskolan.database.repository.mysql;

import java.util.List;

import se.plushogskolan.database.model.Team;

import se.plushogskolan.database.repository.RepositoryException;
import se.plushogskolan.database.repository.TeamRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class MySQLTeamRepository implements TeamRepository {

	private final String url = "jdbc:mysql://localhost:3306/DatabaseProject?useSSL=false";
	PreparedStatement prepstatement = null;
	private final String DB_USER = "awesome";
	private final String DB_PASSWORD = "database";
	private static final Mapper<Team> mapper = (r -> new Team(r.getString("teamname"), r.getString("teamstatus")));

	@Override
	public void addTeam(Team team) throws RepositoryException {
		try {
			String insert = "INSERT INTO Team ( id, teamname, teamstatus) VALUES (?,?,?)";
			try (Connection connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
					PreparedStatement statement = connection.prepareStatement(insert)) {

				statement.setString(1, team.getId());
				statement.setString(2, team.getName());
				statement.setString(3, team.getStatus());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RepositoryException("Can not add team:", e);
		}
	}

	@Override
	public void updateTeam(String oldName, String newName) throws RepositoryException {
		try {
			String update = "UPDATE Team SET teamname = ? WHERE teamname = ?";
			try (Connection connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
					PreparedStatement statement = connection.prepareStatement(update)) {
				statement.setString(1, newName);
				statement.setString(2, oldName);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RepositoryException("Can not update team " + oldName, e);
		}
	}

	@Override
	public void deactivateTeam(String name) throws RepositoryException {
		try {
			String deactivate = "UPDATE Team SET teamstatus = ? WHERE teamname = ?";
			try (Connection connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
					PreparedStatement statement = connection.prepareStatement(deactivate)) {

				statement.setString(1, "deactivated");
				statement.setString(2, name);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RepositoryException("Can not deactivate team:", e);
		}
	}

	@Override
	public List<Team> getAllTeams() throws RepositoryException {
		try {
			List<Team> teamList = new SQL(url).query("select * from Team").many(mapper);
			return teamList;
		} catch (SQLException e) {

			throw new RepositoryException("Can not get all teams:", e);
		}
	}

	@Override
	public void addUserToTeam(String userid, String teamid) throws RepositoryException {
		try {
			String addUserToTeam = "UPDATE User SET teamid = ? WHERE id = ?";
			try (Connection connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
					PreparedStatement statement = connection.prepareStatement(addUserToTeam)) {
				statement.setString(1, teamid);
				statement.setString(2, userid);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RepositoryException("Can not add user " + userid + " to  team: " + teamid, e);
		}
	}

	@Override
	public boolean exists(String teamname) throws RepositoryException {
		try {
			String checkIfTeamExists = "select * from Team where teamname=?";
			try (Connection connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
					PreparedStatement statement = connection.prepareStatement(checkIfTeamExists)) {
				statement.setString(1, teamname);
				ResultSet result = statement.executeQuery();
				return result.next();

			}
		} catch (SQLException e) {
			throw new RepositoryException("Can not get  " + teamname + " because it not  exists", e);
		}

	}

	@Override
	public Team getTeamById(String teamId) throws RepositoryException {
		try {
			String checkIfTeamExists = "select * from Team where id=?";
			try (Connection connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
					PreparedStatement statement = connection.prepareStatement(checkIfTeamExists)) {
				statement.setString(1, teamId);
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					String id = result.getString("id");
					String teamName = result.getString("teamname");
					String status = result.getString("teamstatus");
					Team team = new Team(id, teamName, status);
					return team;
				}

			}
		} catch (SQLException e) {
			throw new RepositoryException("Can not get  " + teamId, e);
		}
		return null;
	}

}
