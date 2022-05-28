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
                forEach(a -> System.out.println(" id: " + a.getId() + ", name: " + a.getName()+ ";"));
        System.out.println("===============================");
    }

    public void deleteSkill() {
        System.out.println("Enter id number to delete skill from the list: ");
        skillController.deleteSkill(Long.parseLong(scanner.nextLine()));
    }
}
