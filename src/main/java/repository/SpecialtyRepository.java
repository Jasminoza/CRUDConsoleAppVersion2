package repository;

import model.Specialty;

import java.util.List;

public interface SpecialtyRepository extends GenericRepository<Specialty, Long> {
    List<Specialty> getAll();
    Specialty create(Specialty specialty);
    Specialty getById(Long id);
    Specialty update(Specialty specialty);
    void delete(Long id);
}
