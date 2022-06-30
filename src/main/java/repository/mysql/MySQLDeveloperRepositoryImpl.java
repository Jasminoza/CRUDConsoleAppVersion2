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
    private static final SkillRepository skillRepository = new MySQLSkillRepositoryImpl();
    private static final String tableName = "developers";

    @Override
    public List<Developer> getAll() {
        List<Developer> developerList;
        try (PreparedStatement psDevelopersListWithoutSkills =
                     connection.prepareStatement("SELECT * FROM " + tableName)) {
            ResultSet rsDevelopersWithoutSkills = psDevelopersListWithoutSkills.executeQuery();
            developerList = ResultSetConverter.convertToDevelopersListWithoutSkills(rsDevelopersWithoutSkills);

            PreparedStatement psSkillsForEachDeveloper =
                    connection.prepareStatement("SELECT * FROM developers_Skills");
            ResultSet rsSkillsForEachDeveloper = psSkillsForEachDeveloper.executeQuery();

            insertSkillsForEachDeveloper(developerList, rsSkillsForEachDeveloper);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return developerList;
    }

    private static List<Developer> insertSkillsForEachDeveloper(List<Developer> developerList, ResultSet rsSkillsForEachDeveloper) throws SQLException {
        while (rsSkillsForEachDeveloper.next()) {
            Long developerID = rsSkillsForEachDeveloper.getLong("developer_ID");
            Long skillID = rsSkillsForEachDeveloper.getLong("skill_ID");
            for (Developer developer : developerList) {
                if (developer.getId().equals(developerID)) {
                    if (developer.getSkills() != null) {
                        developer.getSkills().add(skillRepository.getById(skillID));
                    } else {
                        developer.setSkills(new ArrayList<>());
                        developer.getSkills().add(skillRepository.getById(skillID));
                    }
                }
            }
        }
        return developerList;
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
                while (keys.next()) {
                    developer.setId((long) keys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        try (PreparedStatement psInsertDevelopersSkills =
                     connection.prepareStatement("INSERT INTO developers_Skills" +
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
