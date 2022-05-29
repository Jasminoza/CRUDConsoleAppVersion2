package repository;

import model.Developer;

import java.util.List;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {
    List<Developer> getAll();
    Developer create(Developer developer);
    Developer getById(Long id);
    Developer update(Developer developer);
    void delete(Long id);
}
