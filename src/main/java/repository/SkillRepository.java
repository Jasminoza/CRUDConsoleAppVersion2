package repository;

import model.Skill;

import java.sql.ResultSet;

public interface SkillRepository extends GenericRepository<Skill, Long> {
    ResultSet getAll();
    ResultSet create(Skill skill);
    ResultSet getById(Long id);
    Skill update(Skill skill);
    void delete(Long id);
}
