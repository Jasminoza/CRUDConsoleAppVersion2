package utils;

import model.Developer;
import model.Skill;
import model.Specialty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetConverter {

    public static Skill convertToSkill(ResultSet resultSet) throws SQLException{
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


    public static List<Specialty> convertToSpecialtiesList(ResultSet resultSet) throws SQLException {
        List<Specialty> allSpecialties = new ArrayList<>();

        while (resultSet.next()) {
            Specialty specialty = new Specialty();
            specialty.setId(resultSet.getLong("id"));
            specialty.setName(resultSet.getString("name"));
            allSpecialties.add(specialty);
        }

        return allSpecialties;
    }

    public static List<Developer> convertToDevelopersList(ResultSet resultSet) throws SQLException {
        List<Developer> allDevelopers = new ArrayList<>();
        List<Skill> skills = new ArrayList<>();

        while (resultSet.next()) {
            Developer developer = new Developer();
            developer.setId(resultSet.getLong("id"));
            developer.setFirstName(resultSet.getString("firstName"));
            developer.setLastName(resultSet.getString("lastName"));
            //TODO: getByID
            int cols = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < cols; i++) {
                skills.add((Skill) resultSet.getObject(i + 1));
            }

            //developer.setSkills(); //TODO
            //developer.setSpecialty(); //TODO
            //developer.setStatus(); //TODO
            allDevelopers.add(developer);
        }

        return allDevelopers;
    }


}
