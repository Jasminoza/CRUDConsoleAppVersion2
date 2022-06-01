package repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Specialty;
import repository.SpecialtyRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {
    private final String SPECIALTY_FILE_PATH = "src/main/resources/specialties.json";

    private List<Specialty> getAllSpecialties() {
        String json = getJsonCodeFromFile(SPECIALTY_FILE_PATH);
        Type targetClassType = new TypeToken<ArrayList<Specialty>>() {
        }.getType();
        return new Gson().fromJson(json, targetClassType);
    }

    private void writeSpecialtiesToFile(List<Specialty> allSpecialties) {
        String json = new Gson().toJson(allSpecialties);
        try {
            Files.writeString(Paths.get(SPECIALTY_FILE_PATH), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Long generateNewMaxId(List<Specialty> allSpecialties) {
        Specialty maxIdSpecialty;
        try {
            maxIdSpecialty = allSpecialties.stream().max(Comparator.comparing(Specialty::getId)).orElse(null);
        } catch (NullPointerException e) {
            return 1L;
        }
        return (Objects.nonNull(maxIdSpecialty) ? maxIdSpecialty.getId() + 1 : 1L);
    }

    public List<Specialty> getAll() {
        return getAllSpecialties();
    }

    public Specialty create(Specialty specialty) {
        List<Specialty> allSpecialties = getAllSpecialties();
        specialty.setId(generateNewMaxId(allSpecialties));
        if (Objects.nonNull(allSpecialties)) {
            allSpecialties.add(specialty);
        } else {
            allSpecialties = new ArrayList<>(1);
            allSpecialties.add(specialty);
        }
        writeSpecialtiesToFile(allSpecialties);
        return specialty;
    }

    public Specialty getById(Long id) {
        return getAllSpecialties().stream().filter(sp -> sp.getId().equals(id)).findFirst().orElse(null);
    }

    public Specialty update(Specialty specialty) {
        List<Specialty> allSpecialties = getAllSpecialties();

        allSpecialties.stream()
                .filter(s -> s.getId().equals(specialty.getId()))
                .forEach(s -> s.setName((specialty.getName())));
        writeSpecialtiesToFile(allSpecialties);
        return specialty;
    }

    public void delete(Long id) {
        List<Specialty> allSpecialties = getAllSpecialties();
        allSpecialties.removeIf(specialty -> specialty.getId().equals(id));
        writeSpecialtiesToFile(allSpecialties);
    }

    private String getJsonCodeFromFile(String SPECIALTY_FILE_PATH) {
        String jsonCode;
        try {
            jsonCode = Files.readString(Paths.get(SPECIALTY_FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonCode;
    }
}
