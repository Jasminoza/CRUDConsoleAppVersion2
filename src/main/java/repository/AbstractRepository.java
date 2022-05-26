package repository;

import repository.interfaces.GenericRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public abstract class AbstractRepository implements GenericRepository {
    private Path filepath;

    public abstract void setFilePath();

    public boolean fileExist() {
        return Files.exists(filepath);
    }

    public void connectionSuccessful() {
        System.out.print("Connection to file developers.json is successful.\nLast modification: ");
        try {
            System.out.println(Files.getLastModifiedTime(filepath, LinkOption.NOFOLLOW_LINKS));
        } catch (IOException e) {
            System.out.println("Input-output exception: " + e);
        }
    }

    public void connectionFailed() {
        System.out.println("File developers.json does not exist. Do you want to create it? (y/n)");

        try (Scanner sc = new Scanner(System.in)) {
            String choice = sc.next();
            if (choice.equalsIgnoreCase("y")) {
                Files.createFile(filepath);
                System.out.println("File developers.json created successfully.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
