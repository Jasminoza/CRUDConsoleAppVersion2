package repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Skill;
import repository.SkillRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private final String SKILL_FILE_PATH = "src/main/resources/skills.json";

    private List<Skill> getAllSkills() {
        String json = getJsonCodeFromFile(SKILL_FILE_PATH);
        Type targetClassType = new TypeToken<ArrayList<Skill>>() {
        }.getType();
        return new Gson().fromJson(json, targetClassType);
    }

    private void writeSkillsToFile(List<Skill> skills) {
        String json = new Gson().toJson(skills);
        try {
            Files.writeString(Paths.get(SKILL_FILE_PATH), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Long generateNewMaxId(List<Skill> skills) {
        Skill maxSkill = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(maxSkill) ? maxSkill.getId() + 1 : 1L;
    }

    public List<Skill> getAll() {
        return getAllSkills();
    }

    public Skill create(Skill skill) {
        List<Skill> currentSkills = getAllSkills();
        skill.setId(generateNewMaxId(currentSkills));
        currentSkills.add(skill);
        writeSkillsToFile(currentSkills);
        return skill;
    }

    public Skill getById(Long id) {
        return getAllSkills().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    public Skill update(Skill skill) {
        List<Skill> allSkills = getAllSkills();

        allSkills.stream()
                .filter(s -> s.getId().equals(skill.getId()))
                .forEach(s -> s.setName((skill.getName())));
        writeSkillsToFile(allSkills);

        return skill;
    }

    public void delete(Long id) {
        List<Skill> allSkills = getAllSkills();
        allSkills.removeIf(skill -> skill.getId().equals(id));
        writeSkillsToFile(allSkills);
    }

    public String getJsonCodeFromFile(String SKILL_FILE_PATH) {
        String jsonCode;
        try {
            jsonCode = Files.readString(Paths.get(SKILL_FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(jsonCode);
        return jsonCode;
    }
}
