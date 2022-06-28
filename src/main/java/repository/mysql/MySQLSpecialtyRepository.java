package repository.mysql;

import model.Specialty;
import repository.SpecialtyRepository;
import utils.ConnectionToMySQL;
import utils.ResultSetConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLSpecialtyRepository implements SpecialtyRepository {

    private static final Connection connection = ConnectionToMySQL.getConnection();
    private static final String tableName = "specialties";

    @Override
    public List<Specialty> getAll() {
        try {
            String SQL = "SELECT * FROM " + tableName;
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            return ResultSetConverter.convertToSpecialtiesList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Specialty create(Specialty specialty) {
        insertSpecialty(specialty);
        return getByName(specialty.getName());
    }

    private static void insertSpecialty(Specialty specialty) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + tableName + "(name) VALUES(?)");
            preparedStatement.setString(1, specialty.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Specialty getByName(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE name=?");
            preparedStatement.setString(1, name);
            return ResultSetConverter.convertToSpecialty(preparedStatement.executeQuery());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public Specialty getById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id=?");
            preparedStatement.setLong(1, id);
            return ResultSetConverter.convertToSpecialty(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Specialty update(Specialty specialty) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + tableName + " SET name=? WHERE id=?");
            preparedStatement.setString(1, specialty.getName());
            preparedStatement.setLong(2, specialty.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getByName(specialty.getName());
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
