package repository.gson;

import model.Specialty;
import repository.SpecialtyRepository;

import java.util.List;

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {
    private final String SKILL_FILE_PATH = "src/main/resources/specialties.json";

    @Override
    public List<Specialty> getAll() {
        return null;
    }

    @Override
    public Specialty create(Specialty specialty) {
        return null;
    }

    @Override
    public Specialty getById(Long id) {
        return null;
    }

    @Override
    public Specialty update(Specialty specialty) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
