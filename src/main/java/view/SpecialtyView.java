package view;

import controller.SpecialtyController;

import java.util.Scanner;

public class SpecialtyView {
    private final SpecialtyController specialtyController = new SpecialtyController();
    private final Scanner scanner = new Scanner(System.in);

    public void showAllSpecialties() {
        System.out.println("Specialties:\n===============================");
        specialtyController.getAllSpecialties()
                .forEach(skill -> System.out.println("id: " + skill.getId() + ", name: " + skill.getName()));
        System.out.println("===============================");
    }

    public void createSpecialty() {
        System.out.println("Please, enter name for a new specialty:");
        String name = scanner.nextLine();
        specialtyController.createSpecialty(name);
    }
}
