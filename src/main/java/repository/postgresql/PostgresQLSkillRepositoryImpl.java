package repository.postgresql;

import model.Skill;
import repository.SkillRepository;
import service.ConnectionToPostgreSQL;
import service.ResultSetConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PostgresQLSkillRepositoryImpl implements SkillRepository {
    private static Statement statement;
    static {
        try {
            statement = ConnectionToPostgreSQL.getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> allSkills;

        try {
            String SQL = "SELECT * FROM skills";
            ResultSet resultSet = statement.executeQuery(SQL);
            allSkills = ResultSetConverter.convertToSkills(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allSkills;
    }

    @Override
    public Skill create(Skill skill) {
        return null;
    }

    @Override
    public Skill getById(Long id) {
        return null;
    }

    @Override
    public Skill update(Skill skill) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
