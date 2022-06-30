package repository.mysql;

import model.Developer;
import model.Skill;
import model.Status;
import repository.DeveloperRepository;
import repository.SkillRepository;
import utils.ConnectionToMySQL;
import utils.ResultSetConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDeveloperRepositoryImpl implements DeveloperRepository {
    private static final Connection connection = ConnectionToMySQL.getConnection();
    private static final String tableName = "developers";

    @Override
    public List<Developer> getAll() {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM " + tableName)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return ResultSetConverter.convertToDevelopersList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Developer create(Developer developer) {
        try (PreparedStatement psInsertDeveloper =
                     connection.prepareStatement("INSERT INTO " + tableName +
                             "(firstName, lastName, specialty, status) " +
                             "VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            psInsertDeveloper.setString(1, developer.getFirstName());
            psInsertDeveloper.setString(2, developer.getLastName());
            psInsertDeveloper.setLong(3, (developer.getSpecialty().getId()));
            psInsertDeveloper.setLong(4, Status.ACTIVE.getId());
            psInsertDeveloper.executeUpdate();
            try (ResultSet keys = psInsertDeveloper.getGeneratedKeys()) {
                while(keys.next()) {
                    developer.setId((long) keys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        try(PreparedStatement psInsertDevelopersSkills =
                    connection.prepareStatement("INSERT INTO developersSkills" +
                            "(developer_ID, skill_ID) VALUES(?, ?)")) {
            long developerId = developer.getId();
            for (int i = 0; i < developer.getSkills().size(); i++) {
                psInsertDevelopersSkills.setLong(1, developerId);
                psInsertDevelopersSkills.setLong(2, developer.getSkills().get(i).getId());
                psInsertDevelopersSkills.executeUpdate();
                psInsertDevelopersSkills.clearParameters();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


        return developer;
    }

    @Override
    public Developer getById(Long id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return ResultSetConverter.convertToDeveloper(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Developer update(Developer developer) {
        return null;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE " + tableName + "SET status=? WHERE id=?")) {
            preparedStatement.setLong(1, Status.DELETED.getId());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
