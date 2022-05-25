import repository.GsonDeveloperRepositoryImpl;
import view.interfaces.GeneralView;

import java.util.Scanner;

public class Main implements GeneralView {
    public static void main(String[] args) {
        Main main = new Main();
        main.init();
    }

    public static void sayHello() {
        System.out.println("""
                \nHello, choose an action:
                1. Show all Developers.\s
                2. Show all Skills.\s
                3. Show all Specialties.\s
                4. Check connection to the file "developers.json."\s
                0. exit the program.
                """);
    }

    public static void chooseFirstAction() {
        boolean correctInput;
        String chooseFirstAction;
        Scanner sc = new Scanner(System.in);

        do {
            correctInput = true;
            chooseFirstAction = sc.nextLine();
            switch (chooseFirstAction) {
                case "1" -> System.out.println("Your choice is 1.");
                case "2" -> System.out.println("Your choice is 2.");
                case "3" -> System.out.println("Your choice is 3.");
                case "4" -> {
                    GsonDeveloperRepositoryImpl gsonDeveloperRepository = new GsonDeveloperRepositoryImpl();
                    gsonDeveloperRepository.checkConnectionToRepositoryFile();
                }
                case "0" -> System.exit(0);
                default -> {
                    System.out.println("Wrong input. Please, enter a digit from 0 to 4.");
                    correctInput = false;
                }
            }
        } while (!correctInput);
        sc.close();
    }

    @Override
    public void init() {
        sayHello();
        chooseFirstAction();
    }
}

