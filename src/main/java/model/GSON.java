package model;

import com.google.gson.Gson;

import java.util.List;

public class GSON {
    public static void main(String[] args) {
        Developer developer = new Developer();
        Gson gson = new Gson();
        String json = gson.toJson(developer);
        System.out.println(json);

        Developer first = gson.fromJson(json, Developer.class);
        System.out.println(first.getId());
        System.out.println(first.getFirstName());
        System.out.println(first.getLastName());
        System.out.println(first.getSkills());
        System.out.println(first.getSpecialty());
        System.out.println();


        Developer dev2 = new Developer(123L, "Semen", "Slepakov", List.of(new Skill[]{new Skill(), new Skill()}), new Specialty("Car driver"));
        json = gson.toJson(dev2);
        System.out.println(json);

        Developer second = gson.fromJson(json, Developer.class);
        System.out.println(second.getId());
        System.out.println(second.getFirstName());
        System.out.println(second.getLastName());
        second.getSkills().stream().forEach(n -> System.out.println(n.getSkillName()));
        System.out.println(second.getSpecialty().specialtyName);
    }

}
