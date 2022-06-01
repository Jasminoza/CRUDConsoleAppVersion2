package view;

import controller.DeveloperController;
import controller.SkillController;
import controller.SpecialtyController;
import model.Skill;
import model.Specialty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {

    private final DeveloperController developerController = new DeveloperController();
    private final SkillController skillController = new SkillController();
    private final SpecialtyController specialtyController = new SpecialtyController();
    private final Scanner sc = new Scanner(System.in);

    public void createDeveloper() {
        System.out.println("Enter developer's first name: ");
        String firstname = sc.nextLine();
        System.out.println("Enter developer's last name: ");
        String lastname = sc.nextLine();
        System.out.println("Enter id of skill you want to add: ");
        List<Skill> skills = addSkillsToList();
        System.out.println("Enter id of specialty you want to add: ");
        Specialty specialty = chooseSpecialty();
        developerController.createDeveloper(firstname, lastname, skills, specialty);
    }

    private void showAllSkills() {
        System.out.println("Skills:\n===============================");
        skillController.getAllSkills().
                forEach(a -> System.out.println(" id: " + a.getId() + ", name: " + a.getName() + ";"));
        System.out.println("===============================");
    }

    private List<Skill> addSkillsToList() {
        boolean choiceIsOver = false;
        boolean idIsCorrect;
        Long id;
        ArrayList<Skill> chosenSkills = new ArrayList<>();
        showAllSkills();

        while (!choiceIsOver) {
            idIsCorrect = false;
            System.out.println("Please, enter id number of skill you want to add: ");
            while (!idIsCorrect)
                try {
                    id = Long.parseLong(sc.nextLine());
                    final Long finalId = id;
                    if (skillController.getAllSkills().stream().anyMatch(s -> s.getId().equals(finalId))) {
                        idIsCorrect = true;

                        if (!chosenSkills.contains(skillController.getById(id))) {
                            chosenSkills.add(skillController.getById(id));
                        } else {
                            System.out.println("Chosen skill is already selected.");
                        }
                        System.out.println("Do you want to add another skill? (y/n)");
                        String answer = sc.nextLine();
                        if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
                            choiceIsOver = true;
                        }
                    } else {
                        System.out.println("There is no skill with such id. Please, try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please, enter correct id.");
                }
        }
        return chosenSkills.subList(0, chosenSkills.size() - 1);
    }

    private Specialty chooseSpecialty() {
        boolean idIsCorrect = false;
        Long id;

        showAllSpecialties();
        System.out.println("Please, enter id number of specialty you want to choose: ");
        while (!idIsCorrect) {
            try {
                id = Long.parseLong(sc.nextLine());
                final Long finalId = id;
                if (specialtyController.getAllSpecialties().stream().anyMatch(s -> s.getId().equals(finalId))) {
                    idIsCorrect = true;
                    return specialtyController.getById(id);
                } else {
                    System.out.println("There is no specialty with such id. Please, try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please, enter correct id.");
            }
        }
        return null;
    }

    public void showAllSpecialties() {
        System.out.println("Specialties:\n===============================");
        specialtyController.getAllSpecialties()
                .forEach(skill -> System.out.println("id: " + skill.getId() + ", name: " + skill.getName()));
        System.out.println("===============================");
    }

    public void showAllDevelopers() {
        System.out.println("Developers:\n===============================");
        developerController.getAllDevelopers()
                .forEach(dev -> System.out.println("id: " + dev.getId() + ", first name: " + dev.getFirstName()
                + " last name: " + dev.getLastName()));
        System.out.println("===============================");
    }
}

