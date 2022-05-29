package controller;

import model.Specialty;
import repository.SpecialtyRepository;
import repository.gson.GsonSpecialtyRepositoryImpl;

import java.util.List;

public class SpecialtyController {

    private final SpecialtyRepository specialtyRepository = new GsonSpecialtyRepositoryImpl();

    public List<Specialty> getAllSpecialties(){
        return specialtyRepository.getAll();
    }

    public Specialty createSpecialty(String name) {
        Specialty specialty = new Specialty();
        specialty.setName(name);
        return specialtyRepository.create(specialty);
    }

    public void deleteSpecialty(Long id) {
        specialtyRepository.delete(id);
    }

    public Specialty updateSpecialty(Long id) {
        return specialtyRepository.update(specialtyRepository.getById(id));
    }

    public void getSpecialtyById(Long id) {
        System.out.println("id: " + specialtyRepository.getById(id).getId() + ", name: " + specialtyRepository.getById(id).getName() + ".");
    }

}
