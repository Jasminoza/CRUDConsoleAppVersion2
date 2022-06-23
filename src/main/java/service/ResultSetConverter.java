package service;

import model.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetConverter {
    public static List<Skill> convertToSkills(ResultSet resultSet) throws SQLException {
        List<Skill> allSkills = new ArrayList<>();

        while (resultSet.next()) {
            Skill skill = new Skill();
            skill.setId(resultSet.getLong("id"));
            skill.setName(resultSet.getString("name"));
            allSkills.add(skill);
        }

        return allSkills;
    }
}
