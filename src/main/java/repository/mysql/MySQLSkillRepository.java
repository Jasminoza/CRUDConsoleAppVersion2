package repository.mysql;

import model.Skill;
import repository.SkillRepository;
import service.ConnectionToMySQL;
import service.ResultSetConverter;

import java.sql.*;
import java.util.List;

public class MySQLSkillRepository implements SkillRepository {
    private static final Connection connection;
    private static final Statement statement;
    private static final String tableName = "skills";

    static {
        try {
            connection = ConnectionToMySQL.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getAll() {
        String SQL = "SELECT * FROM " + tableName;
        return sendQueryToDB(SQL);
    }

    private static ResultSet sendQueryToDB(String SQL) {
        try {
            return statement.executeQuery(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet create(Skill skill) {
        insertSkill(skill);
        return insertedSkill(skill);
    }

    private static void insertSkill(Skill skill) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + tableName + "(name) VALUES(?)");
            preparedStatement.setString(1, skill.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ResultSet insertedSkill(Skill skill) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE name=?");
            preparedStatement.setString(1, skill.getName());
            return preparedStatement.executeQuery();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ResultSet getById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id=?");
            preparedStatement.setLong(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Skill update(Skill skill) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + tableName + " SET name=? WHERE id=?");
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setLong(2, skill.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skill;
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
