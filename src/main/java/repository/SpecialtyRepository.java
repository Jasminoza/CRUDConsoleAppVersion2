package repository;

import model.Specialty;

import java.sql.ResultSet;

public interface SpecialtyRepository extends GenericRepository<Specialty, Long> {
    ResultSet getAll();
    ResultSet create(Specialty specialty);
    ResultSet getById(Long id);
    Specialty update(Specialty specialty);
    void delete(Long id);
}
