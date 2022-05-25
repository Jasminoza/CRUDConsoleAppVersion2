package repository;

import model.Developer;
import repository.interfaces.DeveloperRepository;

import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {
    private Path filepath;

    @Override
    public void create(Developer developer, Long aLong) {

    }

    @Override
    public Developer read(Long aLong) {
        return null;
    }

    @Override
    public void update(Developer developer, Long aLong) {

    }

    @Override
    public void delete(Developer developer, Long aLong) {

    }

    @Override
    public void checkConnectionToRepositoryFile() {

        try {
            setFilePath();
            if (fileExist()) {
                connectionSuccessful();
            } else {
                connectionFailed();
            }
        } catch (InvalidPathException e) {
            System.out.println("Invalid path: " + e);
        }
    }

    public void setFilePath() {
        filepath = Paths.get("src/main/resources/developers.json");
    }

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
            String choise = sc.next();
            if (choise.equalsIgnoreCase("y")) {
                Files.createFile(filepath);
                System.out.println("File developers.json created successfully.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
