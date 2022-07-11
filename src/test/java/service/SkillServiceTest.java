package service;

import model.Skill;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.SkillRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

public class SkillServiceTest {
    @Mock
    private SkillRepository skillRepository;
    private SkillService skillService;
    public SkillServiceTest() {
        MockitoAnnotations.openMocks(this);
        this.skillService = new SkillService(skillRepository);
    }

    @Test
    public void checkGetAllReturnsNotNull() {
        given(skillRepository.getAll()).willReturn(Arrays.asList(
                new Skill("write code"),
                new Skill("drink cofee"),
                new Skill("sleep"),
                new Skill("code review"))
        );
        List<Skill> skills = skillService.getAll();
        assertNotNull(skills);
        assertEquals(4, skills.size());
    }

    @Test
    public void checkGetAllReturnsNull() {
        given(skillRepository.getAll()).willReturn(null);
        List<Skill> skills = skillService.getAll();
        assertNull(skills);
    }

    @Test
    public void checkCreateReturnsNotNull() {
        Skill skill = new Skill(5L, "Abrakadabra");

        given(skillRepository.create(skill)).willReturn(skill);
        given(skillRepository.getById(5L)).willReturn(skill);

        Skill abrakadabra = skillService.create(new Skill(5L, "Abrakadabra"));

        assertNotNull(abrakadabra);
        assertEquals(Optional.of(5L), Optional.of(skillRepository.getById(abrakadabra.getId()).getId()));

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