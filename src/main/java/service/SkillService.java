package service;

import model.Skill;
import repository.SkillRepository;
import repository.mysql.JDBCSkillRepositoryImpl;

import java.util.List;

public class SkillService {
    private final SkillRepository skillRepository = new JDBCSkillRepositoryImpl();

    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    public Skill create(Skill skill) {
        return skillRepository.create(skill);
    }

    public Skill getById(Long id) {
        return skillRepository.getById(id);
    }

    public Skill update(Skill skill) {
        return skillRepository.update(skill);
    }

    public void delete(Long id) {
        skillRepository.delete(id);
    }

}
