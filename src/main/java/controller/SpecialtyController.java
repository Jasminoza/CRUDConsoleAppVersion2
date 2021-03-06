package controller;

import model.Specialty;
import service.SpecialtyService;

import java.util.List;

public class SpecialtyController {
    private final SpecialtyService specialtyService = new SpecialtyService();

    public Specialty createSpecialty(String name) {
        Specialty specialty = new Specialty();
        specialty.setName(name);
        return specialtyService.create(specialty);
    }

    public List<Specialty> getAllSpecialties() {
        return specialtyService.getAll();
    }

    public void deleteSpecialty(Long id) {
        specialtyService.delete(id);
    }

    public Specialty updateSpecialty(Long id, String name) {
        Specialty specialty = new Specialty(id, name);
        return specialtyService.update(specialty);
    }

    public Specialty getById(Long id) {
        return specialtyService.getById(id);
    }

}
