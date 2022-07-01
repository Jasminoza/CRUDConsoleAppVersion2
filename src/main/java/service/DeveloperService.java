package service;

import model.Developer;
import repository.DeveloperRepository;
import repository.mysql.JDBCDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperService {
    private final DeveloperRepository developerRepository = new JDBCDeveloperRepositoryImpl();

    public List<Developer> getAll() {
        return developerRepository.getAll();
    }

    public Developer create(Developer developer) {
        return developerRepository.create(developer);
    }

    public Developer getById(Long id) {
        return developerRepository.getById(id);
    }

    public Developer update(Developer developer) {
        return developerRepository.update(developer);
    }

    public void delete(Long id) {
        developerRepository.delete(id);
    }
}
