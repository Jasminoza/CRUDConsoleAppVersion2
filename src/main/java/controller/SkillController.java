package controller;

import model.Skill;
import repository.SkillRepository;
//import repository.gson.GsonSkillRepositoryImpl;
import repository.mysql.MySQLSkillRepository;
import utils.ResultSetConverter;

import java.sql.SQLException;
import java.util.List;

public class SkillController {
    private final SkillRepository skillRepository = new MySQLSkillRepository();


    public Skill createSkill(String name) {
        Skill skill = new Skill();
        skill.setName(name);
        try {
            return ResultSetConverter.convertToSkill(skillRepository.create(skill));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Skill> getAllSkills() {
        try {
            return ResultSetConverter.convertToSkillsList(skillRepository.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSkill(Long id) {
        skillRepository.delete(id);
    }

    public Skill updateSkill(Long id, String name) {
        Skill skill = new Skill(id, name);
        return skillRepository.update(skill);
    }

    public Skill getById(Long id) {
        try {
            return ResultSetConverter.convertToSkill(skillRepository.getById(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
