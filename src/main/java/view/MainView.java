package view;

import java.util.Scanner;

public class MainView {
    private final SkillView skillView = new SkillView();
    private final SpecialtyView specialtyView = new SpecialtyView();
    private final Scanner sc = new Scanner(System.in);

    public void mainMenu() {
        String choice = "";

        while (!choice.equals("0")) {
            System.out.println("""
                    \nHello. Enter a digit from the following:\s
                    1. to see all skills.\s
                    2. to show skill by its id.\s
                    3. to add a skill.\s
                    4. to delete a skill.\s
                    5. to update a skill.\s
                    0. to exit the program.
                    """);
            choice = sc.nextLine();
            switch (choice) {
                case "1" -> skillView.showAllSkills();
                case "2" -> skillView.getById();
                case "3" -> skillView.createSkill();
                case "4" -> skillView.deleteSkill();
                case "5" -> skillView.updateSkill();
                case "6" -> specialtyView.showAllSpecialties();
                case "0" -> {
                    System.out.println("Goodbye.");
                    System.exit(0);
                }
                default -> System.out.println("Please, enter a correct digit.");
            }
        }

    }


}
