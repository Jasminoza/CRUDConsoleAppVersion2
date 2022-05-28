package view;

import java.util.Scanner;

public class MainView {
    private final SkillView skillView = new SkillView();
    private final Scanner sc = new Scanner(System.in);

    public void mainMenu() {
        System.out.println("Hello. Enter 1 to see all skills.");
        String choise = sc.nextLine();
        if (choise.equals("1")) {
            skillView.showAllSkills();
        }
    }


}
