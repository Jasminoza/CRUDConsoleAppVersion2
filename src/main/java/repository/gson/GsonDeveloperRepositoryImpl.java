package repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Developer;
import model.Status;
import repository.DeveloperRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {
    private final String DEVELOPER_FILE_PATH = "src/main/resources/developers.json";

    private List<Developer> getAllDevelopers() {
        String json = getJsonCodeFromFile(DEVELOPER_FILE_PATH);
        Type targetClassType = new TypeToken<ArrayList<Developer>>() {
        }.getType();
        return new Gson().fromJson(json, targetClassType);
    }

    private void writeDevelopersToFile(List<Developer> allDevelopers) {
        String json = new Gson().toJson(allDevelopers);
        try {
            Files.writeString(Paths.get(DEVELOPER_FILE_PATH), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Long generateNewMaxId(List<Developer> allDevelopers) {
        Developer maxDeveloperId;
        try {
            maxDeveloperId = allDevelopers.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        } catch (NullPointerException e) {
            return 1L;
        }
        return (Objects.nonNull(maxDeveloperId) ? maxDeveloperId.getId() + 1 : 1L);
    }

    public List<Developer> getAll() {
        return getAllDevelopers();
    }

    public Developer create(Developer developer) {
        List<Developer> allDevelopers = getAllDevelopers();
        developer.setId(generateNewMaxId(allDevelopers));
        if (Objects.nonNull(allDevelopers)) {
            allDevelopers.add(developer);
        } else {
            allDevelopers = new ArrayList<>(1);
            allDevelopers.add(developer);
        }
        writeDevelopersToFile(allDevelopers);
        return developer;
    }

    public Developer getById(Long id) {
        return getAllDevelopers().stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    public Developer update(Developer developer) {
        List<Developer> allDevelopers = getAllDevelopers();

        allDevelopers.stream()
                .filter(d -> d.getId().equals(developer.getId()))
                .forEach(d -> {
                    d.setId(developer.getId());
                    d.setFirstName(developer.getFirstName());
                    d.setLastName(developer.getLastName());
                    d.setSkills(developer.getSkills());
                    d.setSpecialty(developer.getSpecialty());
                });
        writeDevelopersToFile(allDevelopers);
        return developer;
    }

    public void delete(Long id) {
        List<Developer> allDevelopers = getAllDevelopers();
        allDevelopers.stream()
                .filter(d -> d.getId().equals(id))
                .forEach(d -> d.setStatus(Status.DELETED));
        writeDevelopersToFile(allDevelopers);
    }

    private String getJsonCodeFromFile(String DEVELOPER_FILE_PATH) {
        String jsonCode;
        try {
            jsonCode = Files.readString(Paths.get(DEVELOPER_FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonCode;
    }
}
