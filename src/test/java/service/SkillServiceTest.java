package service;

import model.Skill;
import org.junit.Test;
import repository.SkillRepository;
import repository.mysql.JDBCSkillRepositoryImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    public void checkGetById() {
        Skill skill = new Skill("Abrakadabra");
        skill = skillRepository.create(skill);
        Skill skillFromDB = skillRepository.getById(skill.getId());
        assertEquals(skill, skillFromDB);
        skillRepository.delete(skill.getId());
    }

    @Test
    public void checkUpdate() {
        Skill skill = new Skill("Abrakadabra");
        skill = skillRepository.create(skill);
        Skill skillFromDB = skillRepository.getById(skill.getId());
        assertEquals(skill, skillFromDB);
        skill.setName("Ahalaimahalai");
        skillRepository.update(skill);
        skillFromDB = skillRepository.getById(skill.getId());
        assertEquals(skill, skillFromDB);
    }

    @Test(expected = RuntimeException.class)
    public void checkDeleteFromDB() {
        Skill skill = new Skill("Abrakadabra");
        skill = skillRepository.create(skill);
        int skillsCountBeforeDeletion = skillRepository.getAll().size();
        skillRepository.delete(skill.getId());
        int skillsCountAfterDeletion = skillRepository.getAll().size();
        assertEquals(skillsCountBeforeDeletion - 1, skillsCountAfterDeletion);
        skillRepository.getById(skill.getId());
    }
}