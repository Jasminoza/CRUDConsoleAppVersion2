package service;

import model.Skill;
import org.junit.Test;
import repository.SkillRepository;
import repository.mysql.JDBCSkillRepositoryImpl;

import static org.junit.Assert.*;

public class SkillServiceTest {
    private final SkillRepository skillRepository = new JDBCSkillRepositoryImpl();

    @Test
    public void checkGetAllReturnsNotNull() {
        assertNotNull(skillRepository.getAll());
    }


    @Test
    public void checkCreateReturnsNotNull() {
        Skill skill = new Skill("Abrakadabra");
        assertNotNull(skill = skillRepository.create(skill));
        skillRepository.delete(skill.getId());
    }

    @Test
    public void checkCreateAddsARecordToDB() {
        Skill skill = new Skill("Abrakadabra");
        int skillsCountBeforeAdd = skillRepository.getAll().size();
        skill = skillRepository.create(skill);
        int skillsCountAfterAdd = skillRepository.getAll().size();
        assertEquals(skillsCountBeforeAdd + 1, skillsCountAfterAdd);
        skillRepository.delete(skill.getId());
    }

    @Test
    public void getById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}