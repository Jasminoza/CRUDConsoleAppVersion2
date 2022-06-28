//package controller;
//
//import model.Specialty;
//import repository.SpecialtyRepository;
//import repository.gson.GsonSpecialtyRepositoryImpl;
//import utils.ResultSetConverter;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class SpecialtyController {
//    private final SpecialtyRepository specialtyRepository = new GsonSpecialtyRepositoryImpl();
//
//    public Specialty createSpecialty(String name) {
//        Specialty specialty = new Specialty();
//        specialty.setName(name);
//        return specialtyRepository.create(specialty);
//    }
//
//    public List<Specialty> getAllSpecialties() {
//        try {
//            return ResultSetConverter.convertToSpecialtiesList(specialtyRepository.getAll());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void deleteSpecialty(Long id) {
//        specialtyRepository.delete(id);
//    }
//
//    public Specialty updateSpecialty(Long id, String name) {
//        Specialty specialty = new Specialty(id, name);
//        return specialtyRepository.update(specialty);
//    }
//
//    public Specialty getById(Long id) {
//        return specialtyRepository.getById(id);
//    }
//
//}
