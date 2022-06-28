package repository.mysql;

import model.Developer;
import repository.DeveloperRepository;
import utils.ConnectionToMySQL;

import java.sql.Connection;
import java.util.List;

public class MySQLDeveloperRepositoryImpl implements DeveloperRepository {
    private static final Connection connection = ConnectionToMySQL.getConnection();
    private static final String tableName = "developers";
    @Override
    public List<Developer> getAll() {
        return null;
    }

    @Override
    public Developer create(Developer developer) {
        return null;
    }

    @Override
    public Developer getById(Long aLong) {
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
