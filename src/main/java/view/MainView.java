package view;

import java.util.Scanner;

public class MainView {
    private final SkillView skillView = new SkillView();
    private final Scanner sc = new Scanner(System.in);

    public void mainMenu() {
        String choice = "";

        while (!choice.equals("0")) {
            System.out.println("""
                    \nHello. Enter a digit from the following:\s
                    1. to see all skills.\s
                    2. to add a skill.\s
                    3. to delete a skill.\s
                    0. to exit the program.
                    """);
            choice = sc.nextLine();
            switch (choice) {
                case "1" -> skillView.showAllSkills();
                case "2" -> skillView.createSkill();
                case "3" -> skillView.deleteSkill();
                case "0" -> {
                    System.out.println("Goodbye.");
                    System.exit(0);
                }
                default -> System.out.println("Please, enter a correct digit.");
            }
        }

    }


}
