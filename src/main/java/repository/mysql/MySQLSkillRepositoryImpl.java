package repository.mysql;

import model.Skill;
import repository.SkillRepository;
import utils.ConnectionToMySQL;
import utils.ResultSetConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLSkillRepositoryImpl implements SkillRepository {
    private static final Connection connection;
    private static final String tableName = "skills";

    static {
        connection = ConnectionToMySQL.getConnection();
    }

    @Override
    public List<Skill> getAll() {
        try {
            String SQL = "SELECT * FROM " + tableName;
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            return ResultSetConverter.convertToSkillsList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Skill create(Skill skill) {
        insertSkill(skill);
        return getByName(skill.getName());
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

    private static Skill getByName(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE name=?");
            preparedStatement.setString(1, name);
            return ResultSetConverter.convertToSkill(preparedStatement.executeQuery());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Skill getById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id=?");
            preparedStatement.setLong(1, id);
            return ResultSetConverter.convertToSkill(preparedStatement.executeQuery());
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
        return getByName(skill.getName());
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
