package repository.mysql;

import model.Developer;
import model.Skill;
import repository.DeveloperRepository;
import utils.ConnectionToMySQL;
import utils.ResultSetConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLDeveloperRepositoryImpl implements DeveloperRepository {
    private static final Connection connection = ConnectionToMySQL.getConnection();
    private static final String tableName = "developers";
    @Override
    public List<Developer> getAll() {
        String SQL = "SELECT * FROM " + tableName;
        try (ResultSet resultSet = connection.createStatement().executeQuery(SQL)){
            return ResultSetConverter.convertToDevelopersList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Developer create(Developer developer) {
//        insertDeveloper(developer);
//        return getById(developer.getId());
        return null;
    }

    private static void insertDeveloper(Developer developer) {
//        try {
//            //todo PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + tableName + "(firstName, lastName, specialty, status) VALUES('?', '?', '?', '?')");
//            preparedStatement.setString(1, developer.getFirstName());
//            preparedStatement.setString(1, developer.getLastName());
//            preparedStatement.setString(1, developer.getSpecialty());
//            preparedStatement.setString(1, 1); //Status.ACTIVE;
//            preparedStatement.executeUpdate();
//            //foreach: todo PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO developersSkills (developer_Id, skill_Id) VALUES('?', '?')");

//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }


    @Override
    public Developer getById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id='?'")) {
            preparedStatement.setLong(1, id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Developer update(Developer developer) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
