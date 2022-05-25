package model;

import java.io.Serializable;

public class Skill implements Serializable {

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    private String skillName;
    public Skill() {
        skillName = "default";
    }
}
