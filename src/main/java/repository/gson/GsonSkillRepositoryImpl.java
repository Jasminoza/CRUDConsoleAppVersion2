package repository.gson;

import model.Skill;
import repository.SkillRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private final String SKILL_FILE_PATH = "";

    private List<Skill> getAllSkills() {
        return new ArrayList<>();
    }

    private void writeSkillsToFile(List<Skill> skills) {

    }

    private Long generateNewMaxId(List<Skill> skills) {
        Skill maxSkill = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(maxSkill) ? maxSkill.getId() + 1 : 1L;
    }

    @Override
    public List<Skill> getAll() {
        return getAllSkills();
    }

    @Override
    public Skill create(Skill skill) {
        List<Skill> currentSkills = getAllSkills();
        skill.setId(generateNewMaxId(currentSkills));
        currentSkills.add(skill);
        writeSkillsToFile(currentSkills);
        return skill;
    }

    @Override
    public Skill getById(Long id) {
        return getAllSkills().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Skill update(Skill skill) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
