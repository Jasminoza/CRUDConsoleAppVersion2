package controller;

import model.Skill;
import repository.SkillRepository;
import repository.gson.GsonSkillRepositoryImpl;
import repository.postgresql.PostgresQLSkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private final SkillRepository skillRepository = new PostgresQLSkillRepositoryImpl();

    public Skill createSkill(String name) {
        Skill skill = new Skill();
        skill.setName(name);
        return skillRepository.create(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.getAll();
    }

    public void deleteSkill(Long id) {
        skillRepository.delete(id);
    }

    public Skill updateSkill(Long id, String name) {
        Skill skill = new Skill(id, name);
        return skillRepository.update(skill);
    }

    public Skill getById(Long id) {
        return skillRepository.getById(id);
    }
}
