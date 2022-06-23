package repository.postgresql;

import model.Skill;
import repository.SkillRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static service.ConnectionToDB.connection;

public class PostgresQLSkillRepositoryImpl implements SkillRepository {
    private static Statement statement;
    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> allSkills = new ArrayList<>();
        ResultSet resultSet;

        String SQL = "SELECT * FROM skills";
        try {
             resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Skill skill = new Skill();
                skill.setId(resultSet.getLong("id"));
                skill.setName(resultSet.getString("name"));
                allSkills.add(skill);
            }
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
