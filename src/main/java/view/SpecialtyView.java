package view;

import controller.SpecialtyController;
import model.Specialty;

import java.util.List;

public class SpecialtyView {
    private final SpecialtyController specialtyController = new SpecialtyController();

    public void showAllSpecialties() {
        System.out.println("Specialties:\n===============================");
        specialtyController.getAllSpecialties().stream()
                .forEach(skill -> System.out.println("id: " + skill.getId() + ", name: " + skill.getName()));
        System.out.println("===============================");

    }
}
