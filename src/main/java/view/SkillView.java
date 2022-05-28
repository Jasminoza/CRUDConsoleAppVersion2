package view;

import controller.SkillController;
import model.Skill;

import java.util.Scanner;

public class SkillView {
    private final Scanner scanner = new Scanner(System.in);

    private final SkillController skillController = new SkillController();

    public void createSkill() {
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        Skill skill = skillController.createSkill(name);
        System.out.println("Created skill: " + skill);
    }
}
