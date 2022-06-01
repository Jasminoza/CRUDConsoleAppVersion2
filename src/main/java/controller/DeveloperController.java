package controller;

import model.Developer;
import model.Skill;
import model.Specialty;
import model.Status;
import repository.DeveloperRepository;
import repository.gson.GsonDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {

    private final DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl();

    public Developer createDeveloper(String firstName, String lastName, List<Skill> skills, Specialty specialty,Status status) {
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setSkills(skills);
        developer.setSpecialty(specialty);
        developer.setStatus(Status.ACTIVE);
        return developerRepository.create(developer);
    }

    public List<Developer> getAllDevelopers() {
        return developerRepository.getAll();
    }

    public void deleteDeveloper(Long id) {
        developerRepository.delete(id);
    }

    public Developer updateDeveloper(Long id, String firstName, String lastName, List<Skill> skills, Specialty specialty) {
        Developer developer = new Developer(id, firstName, lastName, skills, specialty);
        return developerRepository.update(developer);
    }

    public Developer getById(Long id) {
        return developerRepository.getById(id);
    }
}
