package controller;

import model.Specialty;
import repository.SpecialtyRepository;
import repository.mysql.MySQLSpecialtyRepositoryImpl;

import java.util.List;

public class SpecialtyController {
    private final SpecialtyRepository specialtyRepository = new MySQLSpecialtyRepositoryImpl();

    public Specialty createSpecialty(String name) {
        Specialty specialty = new Specialty();
        specialty.setName(name);
        return specialtyRepository.create(specialty);
    }

    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.getAll();
    }

    public void deleteSpecialty(Long id) {
        specialtyRepository.delete(id);
    }

    public Specialty updateSpecialty(Long id, String name) {
        Specialty specialty = new Specialty(id, name);
        return specialtyRepository.update(specialty);
    }

    public Specialty getById(Long id) {
        return specialtyRepository.getById(id);
    }

}
