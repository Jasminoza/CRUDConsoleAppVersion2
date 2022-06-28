package repository;

import model.Skill;

import java.sql.ResultSet;
import java.util.List;

public interface SkillRepository extends GenericRepository<Skill, Long> {
    List<Skill> getAll();
    Skill create(Skill skill);
    Skill getById(Long id);
    Skill update(Skill skill);
    void delete(Long id);
}
