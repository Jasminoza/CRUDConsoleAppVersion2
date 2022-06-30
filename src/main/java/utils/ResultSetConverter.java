package utils;

import controller.SpecialtyController;
import model.Developer;
import model.Skill;
import model.Specialty;
import model.Status;
import repository.DeveloperRepository;
import repository.SkillRepository;
import repository.SpecialtyRepository;
import repository.mysql.MySQLDeveloperRepositoryImpl;
import repository.mysql.MySQLSkillRepositoryImpl;
import repository.mysql.MySQLSpecialtyRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetConverter {

    private static final DeveloperRepository developerRepository = new MySQLDeveloperRepositoryImpl();
    private static final SpecialtyRepository specialtyRepository = new MySQLSpecialtyRepositoryImpl();
    private static final SkillRepository skillRepository = new MySQLSkillRepositoryImpl();

    public static Skill convertToSkill(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) {
            resultSet.next();
        }
        Skill skill = new Skill();
        skill.setId(resultSet.getLong("id"));
        skill.setName(resultSet.getString("name"));
        return skill;
    }

    public static List<Skill> convertToSkillsList(ResultSet resultSet) throws SQLException {
        List<Skill> allSkills = new ArrayList<>();

        while (resultSet.next()) {
            Skill skill = convertToSkill(resultSet);
            allSkills.add(skill);
        }

        return allSkills;
    }

    public static Specialty convertToSpecialty(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) {
            resultSet.next();
        }
        Specialty specialty = new Specialty();
        specialty.setId(resultSet.getLong("id"));
        specialty.setName(resultSet.getString("name"));
        return specialty;
    }

    public static List<Specialty> convertToSpecialtiesList(ResultSet resultSet) throws SQLException {
        List<Specialty> allSpecialties = new ArrayList<>();

        while (resultSet.next()) {
            Specialty specialty = convertToSpecialty(resultSet);
            allSpecialties.add(specialty);
        }

        return allSpecialties;
    }

    public static Developer convertToDeveloper(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) {
            resultSet.next();
        }

        Developer developer = new Developer();
        developer.setId(resultSet.getLong("id"));
        developer.setFirstName(resultSet.getString("firstName"));
        developer.setLastName(resultSet.getString("lastName"));
        developer.setSpecialty(specialtyRepository.getById(resultSet.getLong("id")));
        developer.setStatus(ResultSetConverter.convertToStatus(resultSet));
        developer.setSkills(getSkillsListByDeveloper(developer));
        return developer;
    }

    public static List<Developer> convertToDevelopersList(ResultSet resultSet) throws SQLException {
        List<Developer> allDevelopers = new ArrayList<>();
        while (resultSet.next()) {
            Developer developer = convertToDeveloper(resultSet);
            allDevelopers.add(developer);
        }
        return allDevelopers;
    }

    public static Status convertToStatus(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) {
            resultSet.next();
        }
        return Status.getStatusById(resultSet.getLong("id"));
    }

    public static List<Skill> getSkillsListByDeveloper(Developer developer) {
        List<Skill> developersSkills = new ArrayList<>();

        try (Connection connection = ConnectionToMySQL.getConnection();
                PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM developersSkills WHERE skill_ID=?")) {
            preparedStatement.setLong(1, developer.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.last();
            int resultSetSize = resultSet.getRow();
            resultSet.first();

            for (int i = 0; i < resultSetSize; i++) {
                developersSkills.add(skillRepository.getById(resultSet.getLong("id")));
            }

            return developersSkills;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
