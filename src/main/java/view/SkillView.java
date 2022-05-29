package view;

import controller.SkillController;
import model.Skill;

import java.util.Scanner;

public class SkillView {
    private final Scanner scanner = new Scanner(System.in);

    private final SkillController skillController = new SkillController();

    public void createSkill() {
        System.out.println("Enter skill name: ");
        String name = scanner.nextLine();
        Skill skill = skillController.createSkill(name);
        System.out.println("Created skill: " + skill);
        System.out.println("id: " + skill.getId() + ", name: " + skill.getName());
    }

    public void showAllSkills() {
        System.out.println("Skills:\n===============================");
        skillController.getAllSkills().
                forEach(a -> System.out.println(" id: " + a.getId() + ", name: " + a.getName() + ";"));
        System.out.println("===============================");
    }

    public void deleteSkill() {
        System.out.println("Enter id number to delete skill from the list: ");
        skillController.deleteSkill(Long.parseLong(scanner.nextLine()));
    }

    public void updateSkill() {
        boolean idIsCorrect = false;
        Long id;

        showAllSkills();
        System.out.println("Please, enter number of skill you want to update: ");

        while (!idIsCorrect) {
            try {
                id = Long.parseLong(scanner.nextLine());
                final Long finalId = id;
                if (skillController.getAllSkills().stream().anyMatch(s -> s.getId().equals(finalId))) {
                    idIsCorrect = true;
                    Skill skill = skillController.getAllSkills().stream()
                            .filter(s -> s.getId().equals(finalId))
                            .findFirst().orElse(null);
                    System.out.println("Please, enter new name: ");
                    String name = scanner.nextLine();
                    skill.setName(name);

                    skillController.updateSkill(skill);
                } else {
                    System.out.println("There is no skill with such id. Please, try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please, enter correct id.");
            }
        }
    }

    public void getById() {
        System.out.println("Please, enter number of skill you want to see: ");
        Long id = scanner.nextLong();
        skillController.getById(id);
    }
}
